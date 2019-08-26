import com.alibaba.fastjson.JSONObject;
import entity.requestentity.msg.BotResponse;
import entity.requestentity.user.UserCreate;
import exceptions.ClientException;
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
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import util.Log;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.URI;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.UUID;


public class WulaiClient {
    private final static String CONTENT_TYPE = "application/json";
    private final static String CONNECTION = "keep-alive";
    private static final int DEFAUL_TIME_OUT = 15000;
    private static PoolingHttpClientConnectionManager cm = null;
    private static CloseableHttpClient httpClient = null;
    private static URI ENDPOINT = URI.create("https://openapi.wul.ai/");
    private static MessageDigest md = null;
    private static WulaiClient wulaiClient;

    static {
        try {
            md = MessageDigest.getInstance("SHA");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String PUBKEY = null;
    private String SECRET = null;
    private String ApiVersion = null;
    private Log log;

    private WulaiClient() {
    }

    private WulaiClient(String pubkey, String secret, String apiVersion) {
        this.PUBKEY = pubkey;
        this.SECRET = secret;
        this.ApiVersion = apiVersion;
    }

    public static WulaiClient init(String pubkey, String secret, String apiVersion, boolean debug) throws ClientException {
        if (null== wulaiClient) {
            synchronized (WulaiClient.class){
                if (wulaiClient ==null){
                    wulaiClient =new WulaiClient(pubkey,secret,apiVersion);
                    wulaiClient.log=new Log();
                    wulaiClient.log.setDEBUG(debug);
                }
            }
        }
        return wulaiClient;
    }

    public static WulaiClient getInstance() throws ClientException {
        if (wulaiClient !=null){
            return wulaiClient;
        }else {
            throw new ClientException("","SDK 未初始化");
        }
    }


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
            throw new ClientException("", "getSign方法错误");
        }
    }

    public static synchronized void initPools() {
        if (httpClient == null) {
            cm = new PoolingHttpClientConnectionManager();
            int count = 20;
            cm.setDefaultMaxPerRoute(count);
            int totalCount=20;
            cm.setMaxTotal(totalCount);
            httpClient = HttpClients.custom().setConnectionManager(cm).setRetryHandler(retryHandler(5)).build();
        }
    }

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

    private HttpRequestBase getRequest(String uri, String opts, int timeout) throws ClientException {
        if (httpClient == null) {
            initPools();
        }
        HttpRequestBase request = null;
        if (timeout <= 0) {
            timeout = DEFAUL_TIME_OUT;
        }
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(timeout * 1000)
                .setConnectTimeout(timeout * 1000).setConnectionRequestTimeout(timeout * 1000)
                .setExpectContinueEnabled(false).build();
        if (HttpPost.METHOD_NAME.equalsIgnoreCase(opts)) {
            request = new HttpPost(ENDPOINT.resolve(ApiVersion + uri));
        } else {
            log.error("{0}", "SDK目前只支持POST方法");
            throw new ClientException("", "opts参数需为POST");
        }
        request.setConfig(requestConfig);
        return request;
    }

    private HttpEntityEnclosingRequestBase setRequestParams(HttpEntityEnclosingRequestBase request) throws ClientException {
        String nonce = UUID.randomUUID().toString().replace("-", "");
        Long timestamp = System.currentTimeMillis() / 1000;
        request.setHeader("Api-Auth-pubkey", PUBKEY);
        request.setHeader("Api-Auth-nonce", nonce);
        request.setHeader("Api-Auth-timestamp", String.valueOf(timestamp));
        request.setHeader("Api-Auth-sign", getSign(nonce, timestamp, SECRET));
        request.setHeader("Content-Type", CONTENT_TYPE);
        request.setHeader("Connection", CONNECTION);
        if (log.getDEBUG()) {
            for (Header header : request.getAllHeaders()) {
                log.info("{0}:{1}", header.getName(), header.getValue());
            }
        }
        log.debug("url:{0}", request.getURI().toString());
        return request;
    }

    public synchronized String processCommonRequest(String action, String data, String opts) throws ServerException {
        long startTime = System.currentTimeMillis();
        HttpEntity httpEntity = null;
        HttpEntityEnclosingRequestBase postrequest = null;
        String responseBody = "";
        CloseableHttpResponse httpResponse =null;
        try {
            if (httpClient == null) {
                initPools();
            }
            postrequest = (HttpEntityEnclosingRequestBase) getRequest(action, opts, 0);
            postrequest.setEntity(new StringEntity(data));
            postrequest = setRequestParams(postrequest);
            HttpContext context = HttpClientContext.create();
            httpResponse =httpClient.execute(postrequest, context);
            httpEntity = httpResponse.getEntity();
            if (httpEntity != null) {
                responseBody = EntityUtils.toString(httpEntity, "UTF-8");
            }
        } catch (Exception e) {
            if (postrequest != null) {
                postrequest.abort();
            }
            e.printStackTrace();
            log.error("execute post request exception, url:" + ENDPOINT + ", exception:" + e.toString()
                    + ", cost time(ms):" + (System.currentTimeMillis() - startTime));
            throw new ServerException("",e.getMessage(),httpResponse.getStatusLine().getStatusCode());
        }
        log.debug("responseBody:{0}", responseBody.toString());
        return responseBody;
    }




    public void userCreate(UserCreate userCreate) throws ServerException {

        HashMap<String,Object> params=new HashMap<String,Object>();
        params.put("nickname",userCreate.getNikename());
        params.put("avatar_url",userCreate.getAvatar_url());
        params.put("user_id",userCreate.getUser_id());
        Object body=JSONObject.toJSON(params);
        processCommonRequest("/user/create",body.toString(),"post");
    }

    public void botResponse(BotResponse botResponse) throws ServerException {
        HashMap<String,Object> params=new HashMap<String ,Object>();
        params.put("extra",botResponse.getExtra());
        params.put("user_id",botResponse.getUserId());
        HashMap<String,Object> msg=new HashMap<String,Object>();
        HashMap<String,Object> text=new HashMap<String,Object>();
        text.put("content",botResponse.getMsgBody().getText().getContent());
        msg.put("text",text);
        params.put("msg_body",msg);
        Object body=JSONObject.toJSON(params);
        processCommonRequest("/msg/bot-response",body.toString(),"post");
    }


}



