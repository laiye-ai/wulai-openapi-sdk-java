import com.alibaba.fastjson.JSONObject;
import exceptions.ClientException;
import exceptions.ClientExceptionConstant;
import exceptions.ServerException;
import org.apache.http.*;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import util.Log;
import util.ParamsCheck;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Laiye Wulai SDK for Java Programming Language
 */
public class WulaiClient {
    private final static String CONTENT_TYPE = "application/json";
    private static final int DEFAULT_TIME_OUT = 15000;
    private static MessageDigest md = null;

    static {
        try {
            md = MessageDigest.getInstance("SHA");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private PoolingHttpClientConnectionManager cm = null;
    private CloseableHttpClient httpClient = null;
    private URI endpoint = URI.create("https://openapi.wul.ai/");
    private HashMap<String, Integer> opts = null;
    private String PUBKEY = null;
    private String SECRET = null;
    private String ApiVersion = null;
    private Log log;
    private WulaiClient() {
    }


    /**
     * 初始化SDK对象，需要传入公钥密钥信息及对应的SDK版本和是否开启debug模式
     *
     * @param pubkey     每个开放平台渠道有一组 Pubkey 和 Secret，可从吾来平台-渠道设置-开放平台新版(v2)页面上查询。
     * @param secret     每个开放平台渠道有一组 Pubkey 和 Secret，可从吾来平台-渠道设置-开放平台新版(v2)页面上查询。
     * @param apiVersion 当前支持v2模式
     * @param debug      是否开启debug模式,debug模式下会在console中打印相关运行信息
     * @throws ClientException 客户端错误
     */
    public WulaiClient(String pubkey, String secret, String apiVersion, boolean debug) throws ClientException {
        try {
            assert null != pubkey;
            assert null != secret;
        } catch (AssertionError error) {
            throw new ClientException(ClientExceptionConstant.SDK_NOT_SUPPORT,
                    "pubkey or secret can not be null ,Please check !");
        }

        ParamsCheck.checkApiVersion(apiVersion);
        this.PUBKEY = pubkey;
        this.SECRET = secret;
        this.ApiVersion = apiVersion;
        log = new Log();
        log.setDEBUG(debug);
    }

    /**
     * 验签函数
     */
    private static String getSign(String nonce, Long timeStamp, String secret) throws ClientException {
        String source = nonce + timeStamp + secret;
        StringBuilder buffer = new StringBuilder();
        try {
            MessageDigest sha = (MessageDigest) md.clone();
            sha.update(source.getBytes());
            for (byte b : sha.digest()) {
                buffer.append(String.format("%02X", b));
            }
            return buffer.toString().toLowerCase();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            throw new ClientException(ClientExceptionConstant.SDK_INVALID_CREDENTIAL, "getSign方法错误");
        }
    }

    /**
     * 根据指定类型设置http连接重试策略
     *
     * @param tryTimes
     * @return
     */
    private static HttpRequestRetryHandler retryHandler(final int tryTimes) {
        HttpRequestRetryHandler httpRequestRetryHandler = new HttpRequestRetryHandler() {
            public boolean retryRequest(IOException e, int executionCount, HttpContext httpContext) {
                if (executionCount > tryTimes) {
                    return false;
                }
                if (e instanceof NoHttpResponseException) {
                    return true;
                }
                if (e instanceof SSLHandshakeException) {
                    return false;
                }
                if (e instanceof InterruptedIOException) {
                    return false;
                }
                if (e instanceof UnknownHostException) {
                    return true;
                }
                if (e instanceof ConnectTimeoutException) {
                    return false;
                }
                if (e instanceof SSLException) {
                    return false;
                }
                HttpClientContext clientContext = HttpClientContext.adapt(httpContext);
                HttpRequest request = clientContext.getRequest();
                if (!(request instanceof HttpEntityEnclosingRequest)) {
                    return true;
                }
                return false;
            }
        };
        return httpRequestRetryHandler;
    }

    public void setEndpoint(URI endpoint) {
        this.endpoint = endpoint;
    }

    /**
     * 初始化http连接池
     */
    public synchronized void initPools() {
        if (httpClient == null) {
            cm = new PoolingHttpClientConnectionManager();
            int count = 20;
            cm.setDefaultMaxPerRoute(count);
            int totalCount = 20;
            cm.setMaxTotal(totalCount);
            httpClient = HttpClients.custom().
                    setConnectionManager(cm).
                    setRetryHandler(retryHandler(5)).
                    build();
        }
    }

    public void setPools(PoolingHttpClientConnectionManager cm) {
        this.cm = cm;
        httpClient = HttpClients.custom().setConnectionManager(cm).setRetryHandler(retryHandler(5)).build();
    }

    public void setPools(PoolingHttpClientConnectionManager cm, int retryTimes) {
        this.cm = cm;
        httpClient = HttpClients.custom().setConnectionManager(cm).setRetryHandler(retryHandler(retryTimes)).build();
    }

    private HttpRequestBase getRequest(String uri, int timeout) throws ClientException {
        if (httpClient == null) {
            initPools();
        }
        HttpRequestBase request = null;
        if (timeout <= 0) {
            timeout = DEFAULT_TIME_OUT;
        }
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(timeout * 1000)
                .setConnectTimeout(timeout * 1000).setConnectionRequestTimeout(timeout * 1000)
                .setExpectContinueEnabled(false).build();


        // 根据endpoint拼接生成的request的uri信息
        request = new HttpPost(endpoint.resolve(ApiVersion + uri));

        request.setConfig(requestConfig);
        return request;
    }

    /**
     * 将验证信息放入request header中
     */
    private HttpEntityEnclosingRequestBase setRequestParams(HttpEntityEnclosingRequestBase request)
            throws ClientException {
        String nonce = UUID.randomUUID().toString().replace("-", "");
        Long timestamp = System.currentTimeMillis() / 1000;
        request.setHeader("Api-Auth-pubkey", PUBKEY);
        request.setHeader("Api-Auth-nonce", nonce);
        request.setHeader("Api-Auth-timestamp", String.valueOf(timestamp));
        request.setHeader("Api-Auth-sign", getSign(nonce, timestamp, SECRET));
        request.setHeader("Content-Type", CONTENT_TYPE);
        if (log.getDEBUG()) {
            for (Header header : request.getAllHeaders()) {
                log.info("{0}:{1}", header.getName(), header.getValue());
            }
        }
        log.debug("url:{0}", request.getURI().toString());
        return request;
    }

    /**
     * @param action 请求路径，例如 /user/create , /msg/bot-response 等
     * @param data   请求参数，json字符串
     * @param opts   请求方式，当前只支持 post 方法
     * @throws ClientException
     */
    public synchronized String processCommonRequest(String action, String data, HashMap<String, Integer> opts)
            throws ClientException {
        //校验入参
        //ParamsCheck.checkOpts();
        this.opts = opts;
        HttpEntity httpEntity = null;
        HttpEntityEnclosingRequestBase postrequest = null;
        String responseBody = "";
        CloseableHttpResponse httpResponse = null;
        if (httpClient == null) {
            initPools();
        }
        int timeout = opts.get("timeout");

        //获取request 对象，设置参数
        postrequest = (HttpEntityEnclosingRequestBase) getRequest(action, timeout);

        try {
            log.debug("data:" + data, true);
            postrequest.setEntity(new StringEntity(data));
        } catch (UnsupportedEncodingException e) {
            throw new ClientException(ClientExceptionConstant.SDK_INVALID_PARAMS,
                    "request setEntity exception,please check data");
        }
        postrequest = setRequestParams(postrequest);
        HttpContext context = HttpClientContext.create();
        try {
            httpResponse = httpClient.execute(postrequest, context);
        }catch (ConnectTimeoutException | HttpHostConnectException | UnknownHostException e ){
            throw new ClientException(ClientExceptionConstant.SDK_SERVER_UNREACHABLE,e.getMessage());
        } catch (IOException e) {
            throw new ServerException(ClientExceptionConstant.SDK_HTTP_ERROR,e.getMessage(),
                    httpResponse.getStatusLine().getStatusCode());
        }
        int httpCode = httpResponse.getStatusLine().getStatusCode();
        if (httpCode == 200) {
            System.out.println("http good");
        } else if (httpCode == 400) {
            log.debug(httpResponse.toString(), true);
            throw new ServerException(ClientExceptionConstant.SDK_INVALID_REQUEST, httpResponse.toString(), httpCode);
        } else if (httpCode == 401) {
            log.error("Invalid credential", true);
            throw new ClientException(ClientExceptionConstant.SDK_INVALID_CREDENTIAL,
                    " Invalid credential , please check the pubkey , secret and apiVersion");
        } else if (httpCode == 500) {
            log.debug(httpResponse.toString(), true);
            throw new ServerException(ClientExceptionConstant.SDK_HTTP_ERROR, httpResponse.toString(), httpCode);
        } else {
            System.out.println(httpCode);
            throw new ServerException(ClientExceptionConstant.SDK_HTTP_ERROR, httpResponse.toString(), httpCode);
        }
        httpEntity = httpResponse.getEntity();
        if (httpEntity != null) {
            try {
                responseBody = EntityUtils.toString(httpEntity, "UTF-8");
                log.debug("HttpEntity" + responseBody, true);
                Map map = (Map) JSONObject.parseObject(responseBody, Map.class);
                ArrayList list = new ArrayList();
                log.debug("HTTP ENTITY INFO");
                for (Object obj : map.keySet()) {
                    log.debug(obj.toString() + ":" + map.get(obj), true);
                    list.add(map.get(obj));
                }
                log.debug("responseEntity:" + list.toString(), true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        log.debug(responseBody, true);

        if (log.getDEBUG()) {
            for (Header header : httpResponse.getAllHeaders()) {
                log.info("{0}:{1}", header.getName(), header.getValue());
            }
        }
        log.debug("httpcode:{0},responseBody:{1}", httpCode, responseBody.toString());

        return responseBody;

    }


}



