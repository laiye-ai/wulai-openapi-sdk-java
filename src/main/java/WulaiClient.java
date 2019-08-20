import exceptions.ClientException;
import org.apache.http.*;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import util.Log;

import java.io.IOException;
import java.net.URI;
import java.security.MessageDigest;
import java.util.UUID;

public class WulaiClient {
    private final static String CONTENT_TYPE = "application/json";
    private final static String CONNECTION = "keep-alive";
    private static final String DEFAULT_CONTENT_TYPE = "application/json";
    private static final int DEFAUL_TIME_OUT = 15000;

    private static final int Http_Default_Keep_Time = 15000;
    private static PoolingHttpClientConnectionManager cm = null;
    private static CloseableHttpClient httpClient = null;
    private static URI ENDPOINT = URI.create("https://openapi.wul.ai");
    private static MessageDigest md = null;
    private static WulaiClient wulaiClientV1 = null;
    private static WulaiClient wulaiClientV2 = null;
    private static ConnectionKeepAliveStrategy defaultStrategy = new ConnectionKeepAliveStrategy() {
        public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
            HeaderElementIterator it = new BasicHeaderElementIterator(response.headerIterator(HTTP.CONN_KEEP_ALIVE));
            while (it.hasNext()) {
                HeaderElement he = it.nextElement();
                String param = he.getName();
                String value = he.getValue();
                if (value != null && param.equalsIgnoreCase("timeout")) {
                    try {
                        return Long.parseLong(value) * 1000;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            return Http_Default_Keep_Time * 1000;
        }
    };

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

    public static WulaiClient getInstance(String pubkey, String secret, String apiVersion, boolean debug) throws ClientException {
        initPools();
        if ("v1".equalsIgnoreCase(apiVersion)) {
            if (wulaiClientV1 == null) {
                wulaiClientV1=getSingleton(wulaiClientV1,pubkey, secret, apiVersion,debug);
            }
            return wulaiClientV1;
        } else if ("v2".equalsIgnoreCase(apiVersion)) {
            if (wulaiClientV2 == null) {
                wulaiClientV2=getSingleton(wulaiClientV2,pubkey, secret, apiVersion,debug);
            }
            return wulaiClientV2;
        } else {
            throw new ClientException("", "api_version error,please use v1 or v2");
        }
    }

    private synchronized static WulaiClient getSingleton(WulaiClient client,String pubkey,String secret,String apiVersion,boolean debug){
                if (client==null){
                    client=new WulaiClient(pubkey, secret, apiVersion);
                    client.log=new Log();
                    client.log.setDEBUG(debug);
                }
                return client;
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
            int count = 32;
            cm.setDefaultMaxPerRoute(count);
            int totalCount=200;
            cm.setMaxTotal(totalCount);
            httpClient = HttpClients.custom().setKeepAliveStrategy(defaultStrategy).setConnectionManager(cm).build();
        }
    }

    public static CloseableHttpClient getHttpClient() {
        return httpClient;
    }

    public static PoolingHttpClientConnectionManager getHttpConnectionManager() {
        return cm;
    }


    public HttpRequestBase getRequest(String uri, String opts, int timeout) throws ClientException {
        if (httpClient == null) {
            initPools();
        }
        HttpRequestBase method = null;
        if (timeout <= 0) {
            timeout = DEFAUL_TIME_OUT;
        }
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(timeout * 1000)
                .setConnectTimeout(timeout * 1000).setConnectionRequestTimeout(timeout * 1000)
                .setExpectContinueEnabled(false).build();
        if (HttpPost.METHOD_NAME.equalsIgnoreCase(opts)) {
            method = new HttpPost(ENDPOINT.resolve("/" + ApiVersion + uri));
        } else {
            log.error("{0}", "SDK目前只支持POST方法");
            throw new ClientException("", "opts参数需为POST");
        }
        method.setConfig(requestConfig);
        return method;
    }

    public HttpEntityEnclosingRequestBase setRequestParams(HttpEntityEnclosingRequestBase request) throws ClientException {
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

    public synchronized String processCommonRequest(String action, String data, String opts) throws IOException {
        long startTime = System.currentTimeMillis();

        HttpEntity httpEntity = null;
        HttpEntityEnclosingRequestBase postrequest = null;
        String responseBody = "";
        try {
            if (httpClient == null) {
                initPools();
            }
            postrequest = (HttpEntityEnclosingRequestBase) getRequest(action, opts, 0);
            postrequest.setEntity(new StringEntity(data));
            postrequest = setRequestParams(postrequest);
            HttpContext context = HttpClientContext.create();
            CloseableHttpResponse httpResponse = httpClient.execute(postrequest, context);
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
        }
        log.debug("responseBody:{0}", responseBody.toString());
        return responseBody;
    }

}
