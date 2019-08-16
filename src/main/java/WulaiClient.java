import exceptions.Client_Exception;
import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.security.MessageDigest;
import java.util.UUID;

public class WulaiClient {
    private final static String CONTENT_TYPE = "application/json";
    private final static String CONNECTION = "keep-alive";
    private static final String DEFAULT_CONTENT_TYPE = "application/json";
    private static final int DEFAUL_TIME_OUT = 15000;
    private static final int count = 32;
    private static final int totalCount = 1000;
    private static final int Http_Default_Keep_Time = 15000;
    private static PoolingHttpClientConnectionManager cm = null;
    private static CloseableHttpClient httpClient = null;
    private static String API_VERSION = null;
    private static URI ENDPOINT = URI.create("https://openapi.wul.ai");
    private static String PUBKEY = null;
    private static String SECRET = null;
    private static MessageDigest md = null;
    private static volatile WulaiClient clientv1;
    private static volatile WulaiClient clientv2;
    private static Logger logger = LoggerFactory.getLogger(WulaiClient.class);
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
                        logger.error("format KeepAlive timeout exception, exception:" + e.toString());
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

    private WulaiClient() {
    }

    private WulaiClient(String pubkey, String secret, String api_version) {
        PUBKEY = pubkey;
        SECRET = secret;
        API_VERSION = "/" + api_version;

    }

    private static String getSign(String nonce, Long timeStamp, String secret) throws Client_Exception {
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
            throw new Client_Exception("", "getSign方法错误");
        }
    }

    public static WulaiClient create(String pubkey, String secret, String api_version) throws Client_Exception {
        if ("v2".equals(api_version)) {
            if (clientv2 == null) {
                synchronized (WulaiClient.class) {
                    if (clientv2 == null) {
                        clientv2 = new WulaiClient(pubkey, secret, api_version);
                    }
                }
            }
            return clientv2;
        } else if ("v1".equals(api_version)) {
            if (clientv1 == null) {
                synchronized (WulaiClient.class) {
                    if (clientv1 == null) {
                        clientv1 = new WulaiClient(pubkey, secret, api_version);
                    }
                }
            }
            return clientv1;
        } else {
            throw new Client_Exception("", "api_version error ,please choose api_version from v1 or v2");
        }
    }

    public static synchronized void initPools() {
        if (httpClient == null) {
            cm = new PoolingHttpClientConnectionManager();
            cm.setDefaultMaxPerRoute(count);
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

    public static HttpRequestBase getRequest(String uri, String opts, int timeout) throws Client_Exception {
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
            method = new HttpPost(ENDPOINT.resolve(API_VERSION + uri));
        } else {
           throw new Client_Exception("","opts参数需为POST");
        }
        method.setConfig(requestConfig);
        return method;
    }

    private static HttpEntityEnclosingRequestBase setRequestParams(HttpEntityEnclosingRequestBase request) throws Client_Exception {
        String nonce = UUID.randomUUID().toString().replace("-", "");
        Long timestamp = System.currentTimeMillis() / 1000;
        request.setHeader("Api-Auth-pubkey", PUBKEY);
        request.setHeader("Api-Auth-nonce", nonce);
        request.setHeader("Api-Auth-timestamp", String.valueOf(timestamp));
        request.setHeader("Api-Auth-sign", getSign(nonce, timestamp, SECRET));
        request.setHeader("Content-Type", CONTENT_TYPE);
        request.setHeader("Connection", CONNECTION);
        logger.info("url:{}", request.getURI());
        return request;
    }

    public String processCommonRequest(String action, String data, String opts) {
        long startTime = System.currentTimeMillis();
        HttpEntity httpEntity = null;
        HttpEntityEnclosingRequestBase method = null;
        String responseBody = "";
        try {
            if (httpClient == null) {
                initPools();
            }
            method = (HttpEntityEnclosingRequestBase) getRequest(action, opts, 0);
            method.setEntity(new StringEntity(data));
            method = setRequestParams(method);
            HttpContext context = HttpClientContext.create();
            CloseableHttpResponse httpResponse = httpClient.execute(method, context);
            httpEntity = httpResponse.getEntity();
            if (httpEntity != null) {
                responseBody = EntityUtils.toString(httpEntity, "UTF-8");
            }
        } catch (Exception e) {
            if (method != null) {
                method.abort();
            }
            e.printStackTrace();
            logger.error("execute post request exception, url:" + ENDPOINT + ", exception:" + e.toString()
                    + ", cost time(ms):" + (System.currentTimeMillis() - startTime));
        } finally {
            if (httpEntity != null) {
                try {
                    EntityUtils.consumeQuietly(httpEntity);
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.error("close response exception, url:" + ENDPOINT + ", exception:" + e.toString()
                            + ", cost time(ms):" + (System.currentTimeMillis() - startTime));
                }
            }
        }
        logger.info(responseBody.toString());
        return responseBody;
    }

}
