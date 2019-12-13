package com;

import com.alibaba.fastjson.JSONObject;
import com.exceptions.ClientException;
import com.exceptions.ClientExceptionConstant;
import com.exceptions.ServerException;
import com.util.DefaultProfile;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.util.Credentials;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.URI;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DefaultClient {

    private final static String CONTENT_TYPE = "application/json";
    private final static int DEFAULT_TIME_OUT = 5;
    private static Logger logger = LoggerFactory.getLogger(DefaultClient.class);
    private static MessageDigest md = null;
    private Credentials credentials;
    private CloseableHttpClient httpClient = null;
    private PoolingHttpClientConnectionManager cm = null;
    private int retryTimes = 5;
    private int timeout = 10;
    private URI endpoint;

    private String ApiVersion = DefaultProfile.getApiVersion();

    static {
        try {
            md = MessageDigest.getInstance("SHA");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setApiVersion(String apiVersion) {
        ApiVersion = apiVersion;
    }

    public void setRetryTimes(int retryTimes) {
        this.retryTimes = retryTimes;
    }

    public void setPools(PoolingHttpClientConnectionManager cm) {
        this.cm = cm;
        httpClient = HttpClients.custom().setConnectionManager(cm).setRetryHandler(retryHandler()).build();
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
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
            logger.error(e.getMessage());
            throw new ClientException(ClientExceptionConstant.SDK_INVALID_CREDENTIAL, "getSign方法错误");
        }
    }

    private HttpRequestBase getRequest(String uri, int timeout) {
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

    public Map getEntityMapFromResponse(CloseableHttpResponse httpResponse) throws ClientException {
        Map map = null;
        HttpEntity entity = httpResponse.getEntity();
        String body = null;
        try {
            body = EntityUtils.toString(entity, "UTF-8");
        } catch (IOException e) {
            logger.error("EntityUtils toString exception");
            throw new ClientException(ClientExceptionConstant.SDK_RESOLVING_ERROR, e.getMessage());
        }
        map = JSONObject.parseObject(body, HashMap.class);
        if (map == null) {
            logger.error("EntityMap is null");
        }
        return map;
    }
    public String getBodyFromResponse(CloseableHttpResponse httpResponse) throws ClientException {
        Map map = null;
        HttpEntity entity = httpResponse.getEntity();
        String body = null;
        try {
            body = EntityUtils.toString(entity, "UTF-8");
        } catch (IOException e) {
            logger.error("EntityUtils toString exception");
            throw new ClientException(ClientExceptionConstant.SDK_RESOLVING_ERROR, e.getMessage());
        }

        return body;
    }

    public synchronized CloseableHttpResponse excuteRequest(String action, HashMap<String, Object> data)
            throws ClientException, ServerException {
        HttpEntityEnclosingRequestBase postrequest = null;
        CloseableHttpResponse httpResponse = null;
        String body = null;
        if (httpClient == null) {
            initPools();
        }
        postrequest = (HttpEntityEnclosingRequestBase) getRequest(action, timeout);
        setRequestParams(postrequest);
        body = JSONObject.toJSON(data).toString();
        logger.debug(body);
        System.out.println(body);
        postrequest.setEntity(new StringEntity(body, "UTF-8"));
        HttpContext context = HttpClientContext.create();
        try {
            httpResponse = httpClient.execute(postrequest, context);
        } catch (IOException e) {
            throw new ClientException(ClientExceptionConstant.SDK_HTTP_ERROR, e.getMessage());
        }
        assert httpResponse != null;
        checkHttpCode(httpResponse);
        return httpResponse;
    }


    private void checkHttpCode(CloseableHttpResponse response) throws ClientException, ServerException {
        int httpCode;
        Map map = null;
        httpCode = response.getStatusLine().getStatusCode();
        if (httpCode != 200) {
            map = getEntityMapFromResponse(response);
        }
        switch (httpCode) {
            case 400:
                throw new ClientException(ClientExceptionConstant.SDK_INVALID_PARAMS, map.get("message").toString());
            case 401:
                throw new ClientException(ClientExceptionConstant.SDK_INVALID_CREDENTIAL, map.get("message").toString());
            case 403:
            case 429:
            case 404:
                throw new ClientException(ClientExceptionConstant.SDK_INVALID_REQUEST, map.get("message").toString());
            case 409:
            case 499:
                throw new ClientException(ClientExceptionConstant.SDK_HTTP_ERROR, map.get("message").toString());
            case 500:
            case 501:
            case 504:
            case 503:
                throw new ServerException(map.get("code").toString(), map.get("message").toString(), httpCode);
            default:
                break;
        }
    }

    public synchronized String processCommonRequest(String action, String data) throws ClientException, ServerException {
        HttpEntity httpEntity = null;
        HttpEntityEnclosingRequestBase postrequest = null;
        String responseBody = "";
        CloseableHttpResponse httpResponse = null;
        HttpContext context = HttpClientContext.create();
        int httpCode = 0;

        if (httpClient == null) {
            initPools();
        }
        // 获取request 对象，设置参数
        postrequest = (HttpEntityEnclosingRequestBase) getRequest(action, timeout);
        System.out.println(postrequest.getURI());
        logger.debug(data);
        postrequest.setEntity(new StringEntity(data, "UTF-8"));
        setRequestParams(postrequest);
        try {
            httpResponse = httpClient.execute(postrequest, context);
        } catch (ConnectTimeoutException | HttpHostConnectException | UnknownHostException e) {
            throw new ClientException(ClientExceptionConstant.SDK_SERVER_UNREACHABLE, e.getMessage());
        } catch (IOException e) {
            throw new ClientException(ClientExceptionConstant.SDK_HTTP_ERROR, e.getMessage());
        }
        httpCode = httpResponse.getStatusLine().getStatusCode();
        checkHttpCode(httpResponse);
        httpEntity = httpResponse.getEntity();
        if (httpEntity != null) {
            try {
                responseBody = EntityUtils.toString(httpEntity, "UTF-8");
            } catch (IOException e) {
                throw new ClientException(ClientExceptionConstant.SDK_RESOLVING_ERROR, e.getMessage());
            }
        }

        logger.debug("HttpEntity:{}", responseBody);
        Map map = (Map) JSONObject.parseObject(responseBody, Map.class);
        ArrayList list = new ArrayList();
        for (Object obj : map.keySet()) {
            logger.debug(obj.toString() + ":" + map.get(obj));
            list.add(map.get(obj));
        }

        if (logger.isDebugEnabled()) {
            logger.debug("responseEntity:" + list.toString());
            logger.debug(responseBody);
            for (Header header : httpResponse.getAllHeaders()) {
                logger.debug(header.getName() + " : " + header.getValue());
            }
        }
        logger.debug("httpCode:{}, responseBody:{}", httpCode, responseBody);
        return responseBody;
    }

    private void setRequestParams(HttpEntityEnclosingRequestBase request)
            throws ClientException {
        String nonce = UUID.randomUUID().toString().replace("-", "");
        Long timestamp = System.currentTimeMillis() / 1000;

        request.setHeader("Api-Auth-pubkey", credentials.getPubkey());
        request.setHeader("Api-Auth-nonce", nonce);
        request.setHeader("Api-Auth-timestamp", String.valueOf(timestamp));
        request.setHeader("Api-Auth-sign", getSign(nonce, timestamp, credentials.getSecret()));
        request.setHeader("Content-Type", CONTENT_TYPE);
        logger.debug("url:" + request.getURI().toString());

    }

    private HttpRequestRetryHandler retryHandler() {
        HttpRequestRetryHandler httpRequestRetryHandler = new HttpRequestRetryHandler() {
            public boolean retryRequest(IOException e, int executionCount, HttpContext httpContext) {
                if (executionCount > retryTimes) {
                    return false;
                }
                if (e instanceof NoHttpResponseException) {
                    return true;
                }
                if (e instanceof SSLHandshakeException) {
                    return false;
                }
                if (e instanceof ConnectTimeoutException) {
                    return false;
                }
                if (e instanceof UnknownHostException) {
                    return true;
                }
                if (e instanceof InterruptedIOException) {
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

    private synchronized void initPools() {
        if (httpClient == null) {
            int count = 20;
            int totalCount = 20;
            cm = new PoolingHttpClientConnectionManager();

            cm.setDefaultMaxPerRoute(count);
            cm.setMaxTotal(totalCount);
            httpClient = HttpClients.custom().
                    setConnectionManager(cm).
                    setRetryHandler(retryHandler()).
                    build();
            logger.debug("init pool success");
        }
    }


    public DefaultClient() {
        this.endpoint = DefaultProfile.getEndpoint();
        this.credentials = new Credentials();
    }

    public DefaultClient(URI uri, Credentials credentials) {
        this.endpoint = uri;
        this.credentials = credentials;
    }

}
