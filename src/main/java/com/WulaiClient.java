package com;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.exceptions.ClientException;
import com.exceptions.ClientExceptionConstant;
import com.exceptions.ServerException;
import com.util.Credentials;
import com.util.DefaultProfile;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.NoHttpResponseException;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.URI;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class WulaiClient {

    private final static String CONTENT_TYPE = "application/json";
    private final static int DEFAULT_TIME_OUT = 5;
    private static Logger logger = LoggerFactory.getLogger(WulaiClient.class);
    private static MessageDigest md = null;
    private Credentials credentials;
    private CloseableHttpClient httpClient = null;
    private PoolingHttpClientConnectionManager cm = null;
    private int retryTimes = 5;
    private int timeout = 10;
    private URI endpoint;

    private String ApiVersion = "v2";

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


    private JSONObject getJsonFromResponse(CloseableHttpResponse httpResponse) throws ClientException {
        HttpEntity httpEntity = httpResponse.getEntity();
        String body = null;
        try {
            body = EntityUtils.toString(httpEntity, "UTF-8");
        } catch (IOException e) {
            logger.error("EntityUtils toString exception");
            throw new ClientException(ClientExceptionConstant.SDK_RESOLVING_ERROR, e.getMessage());
        }

        return JSONObject.parseObject(body, JSONObject.class);
    }

    public <T> T getResponse(CloseableHttpResponse httpResponse, Class<T> T) throws ClientException, ServerException {
        HttpEntity httpEntity = httpResponse.getEntity();
        String body = null;
        try {
            body = EntityUtils.toString(httpEntity, "UTF-8");
        } catch (IOException e) {
            logger.error("EntityUtils toString exception");
            throw new ClientException(ClientExceptionConstant.SDK_RESOLVING_ERROR, e.getMessage());
        }
        logger.debug(body);
        T t;
        try {
            t = JSONObject.parseObject(body, T);
        } catch (JSONException e) {
            throw new ClientException("10", e.getMessage());
        }

        return t;
    }

    public <T> T getResponse(CloseableHttpResponse httpResponse, Class<T> T, String key) throws ClientException, ServerException {
        HttpEntity httpEntity = httpResponse.getEntity();
        String body = null;
        try {
            body = EntityUtils.toString(httpEntity, "UTF-8");
        } catch (IOException e) {
            logger.error("EntityUtils toString exception");
            throw new ClientException(ClientExceptionConstant.SDK_RESOLVING_ERROR, e.getMessage());
        }
        logger.debug(body);
        T t;
        try {
            JSONObject jsonObject = JSONObject.parseObject(body, JSONObject.class);
            t = JSONObject.parseObject(jsonObject.get(key).toString(), T);
        } catch (JSONException e) {
            throw new ClientException("10", e.getMessage());
        }
        return t;
    }

    public <T> List<T> getResponseArray(CloseableHttpResponse httpResponse, Class<T> T) throws ClientException {

        HttpEntity httpEntity = httpResponse.getEntity();
        String body = null;
        try {
            body = EntityUtils.toString(httpEntity, "UTF-8");
        } catch (IOException e) {
            logger.error("EntityUtils toString exception");
            throw new ClientException(ClientExceptionConstant.SDK_RESOLVING_ERROR, e.getMessage());
        }
        logger.debug(body);

        List<T> tList;
        try {
            tList = JSONObject.parseArray(body, T);
        } catch (JSONException e) {
            throw new ClientException("10", e.getMessage());
        }
        return tList;

    }

    public <T> List<T> getResponseArray(CloseableHttpResponse httpResponse, Class<T> T, String key) throws ClientException {

        HttpEntity httpEntity = httpResponse.getEntity();
        String body = null;
        try {
            body = EntityUtils.toString(httpEntity, "UTF-8");
        } catch (IOException e) {
            logger.error("EntityUtils toString exception");
            throw new ClientException(ClientExceptionConstant.SDK_RESOLVING_ERROR, e.getMessage());
        }
        logger.debug(body);
        List<T> tList;
        try {
            JSONObject jsonObject = JSONObject.parseObject(body, JSONObject.class);
            tList = JSONObject.parseArray(jsonObject.get(key).toString(), T);

        } catch (JSONException e) {
            throw new ClientException("10", e.getMessage());
        }

        return tList;
    }


    public synchronized CloseableHttpResponse executeRequest(String action, HashMap<String, Object> data)
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
        JSONObject jsonObject = null;
        httpCode = response.getStatusLine().getStatusCode();
        if (httpCode == 200) {
            return;
        } else {
            jsonObject = getJsonFromResponse(response);
        }
        switch (httpCode) {
            case 400:
                throw new ClientException(ClientExceptionConstant.SDK_INVALID_PARAMS, jsonObject.get("message").toString());
            case 401:
                throw new ClientException(ClientExceptionConstant.SDK_INVALID_CREDENTIAL, jsonObject.get("message").toString());
            case 403:
            case 429:
            case 404:
                throw new ClientException(ClientExceptionConstant.SDK_INVALID_REQUEST, jsonObject.get("message").toString());
            case 409:
            case 499:
                throw new ClientException(ClientExceptionConstant.SDK_HTTP_ERROR, jsonObject.get("message").toString());
            case 500:
            case 501:
            case 504:
            case 503:
                throw new ServerException(jsonObject.get("code").toString(), jsonObject.get("message").toString(), httpCode);
            default:
                break;
        }
    }

    public synchronized JSONObject processCommonRequest(String action, JSONObject data) throws ClientException, ServerException {

        HashMap<String, Object> params = JSONObject.parseObject(data.toString(), HashMap.class);
        CloseableHttpResponse httpResponse = executeRequest(action, params);
        return getJsonFromResponse(httpResponse);

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


    public WulaiClient() {
        this.endpoint = DefaultProfile.getEndpoint();
        this.credentials = new Credentials();
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public WulaiClient(URI uri, Credentials credentials) {
        this.endpoint = uri;
        this.credentials = credentials;
    }

}
