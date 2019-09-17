import com.alibaba.fastjson.JSONObject;
import exceptions.ClientException;
import exceptions.ClientExceptionConstant;
import exceptions.ServerException;
import module.request.msg.BotResponseRequest;
import module.request.msg.HistoryRequest;
import module.request.msg.ReceiveRequest;
import module.request.msg.SyncRequest;
import module.request.user.UserAttributeCreateRequest;
import module.request.user.UserAttributeListRequest;
import module.request.user.UserCreateRequest;
import module.response.msg.*;
import module.response.user.UserAttributeListResponse;
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
import util.ParamsCheck;

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

/**
 * Laiye Wulai SDK for Java Programming Language
 */
// no package declaration
public class WulaiClient implements IUser, IMsg {
    private final static String CONTENT_TYPE = "application/json";
    private final static int DEFAULT_TIME_OUT = 5;
    private static Logger logger = LoggerFactory.getLogger(WulaiClient.class);
    private static MessageDigest md = null;

    static {
        try {
            md = MessageDigest.getInstance("SHA");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private URI endpoint = URI.create("https://openapi.wul.ai/");
    private int timeout = 10;
    private CloseableHttpClient httpClient = null;
    private int retryTimes = 5;
    private PoolingHttpClientConnectionManager cm = null;
    private String PUBKEY = null;
    private String SECRET = null;
    private String ApiVersion = null;
    private HashMap<String, Object> params = new HashMap<String, Object>();

    /**
     * 初始化SDK对象，需要传入公钥密钥信息及对应的SDK版本和是否开启debug模式
     *
     * @param pubkey     每个开放平台渠道有一组 Pubkey 和 Secret，可从吾来平台-渠道设置-开放平台新版(v2)页面上查询。
     * @param secret     每个开放平台渠道有一组 Pubkey 和 Secret，可从吾来平台-渠道设置-开放平台新版(v2)页面上查询。
     * @param apiVersion 当前支持v2模式
     * @throws ClientException 客户端错误
     */
    public WulaiClient(String pubkey, String secret, String apiVersion) throws ClientException {
        try {
            assert null != pubkey;
            assert null != secret;
        } catch (AssertionError error) {
            logger.error("pubkey or secret can not be null ,Please check !");
            throw new ClientException(ClientExceptionConstant.SDK_NOT_SUPPORT,
                    "pubkey or secret can not be null ,Please check !");
        }
        ParamsCheck.checkApiVersion(apiVersion);
        this.PUBKEY = pubkey;
        this.SECRET = secret;
        this.ApiVersion = apiVersion;

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

    public void setEndpoint(URI endpoint) throws ClientException {
        ParamsCheck.checkEndPoint(endpoint.toString());
        this.endpoint = endpoint;
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

    public synchronized void initPools() {
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

    private void setRequestParams(HttpEntityEnclosingRequestBase request)
            throws ClientException {
        String nonce = UUID.randomUUID().toString().replace("-", "");
        Long timestamp = System.currentTimeMillis() / 1000;

        request.setHeader("Api-Auth-pubkey", PUBKEY);
        request.setHeader("Api-Auth-nonce", nonce);
        request.setHeader("Api-Auth-timestamp", String.valueOf(timestamp));
        request.setHeader("Api-Auth-sign", getSign(nonce, timestamp, SECRET));
        request.setHeader("Content-Type", CONTENT_TYPE);
        logger.debug("url:" + request.getURI().toString());

    }

    private synchronized CloseableHttpResponse excuteRequest(String action, HashMap<String, Object> data)
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

    private Map getEntityMapFromResponse(CloseableHttpResponse httpResponse) throws ClientException {
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


    /**
     * @param action 请求路径，例如 /user/create , /msg/bot-response 等
     * @param data   请求参数，json字符串
     * @throws ClientException 客户端异常
     */
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

    /**
     * 创建用户
     *
     * @param userCreateRequest Constructor:new UserCreateRequest(String userId)
     * @return int
     * @throws ClientException
     * @throws ServerException
     */
    public int userCreate(UserCreateRequest userCreateRequest) throws ClientException, ServerException {
        params.clear();
        params.put("user_id", userCreateRequest.getUserId());
        params.put("avatar_url", userCreateRequest.getAvatarUrl());
        params.put("nickname", userCreateRequest.getNickname());

        CloseableHttpResponse httpResponse = excuteRequest("/user/create", params);
        int httpCode = httpResponse.getStatusLine().getStatusCode();
        logger.info("userCreate:{} ,status:{}", userCreateRequest.getUserId(), httpCode);
        return httpCode;
    }

    /**
     * 获取机器人回复
     *
     * @param botResponseRequest Constructor: new BotResponseRequest(String userId, MsgBody msgBody)
     * @return BotResponse
     * @throws ClientException 客户端错误
     * @Required userId 用户唯一标识  [ 1 .. 128 ] characters
     * @Required MsgBody 消息体
     * extra 自定义字段 <= 1024 characters
     */
    public BotResponse getBotResponse(BotResponseRequest botResponseRequest) throws ClientException, ServerException {
        params.clear();
        Map map = null;
        params.put("msg_body", botResponseRequest.getMsgBody());
        params.put("user_id", botResponseRequest.getUserId());
        params.put("extra", botResponseRequest.getExtra());

        CloseableHttpResponse httpResponse = excuteRequest("/msg/bot-response", params);
        map = getEntityMapFromResponse(httpResponse);
        logger.info("getBotResponse:msg_id->{}", map.get("msg_id"));
        return new BotResponse(map);
    }


    /**
     * 获取关键字机器人回复
     *
     * @param botResponseRequest Constructor: new BotResponseRequest(String userId, MsgBody msgBody)
     * @return KeywordResponse
     * @throws ClientException 客户端错误
     * @Required userId 用户唯一标识 [ 1 .. 128 ] characters
     * @Required MsgBody 消息体
     * extra 自定义字段 <= 1024 characters
     */
    public KeywordResponse getKeywordBotResponse(BotResponseRequest botResponseRequest) throws ClientException, ServerException {
        params.clear();
        Map map = null;

        params.put("user_id", botResponseRequest.getUserId());
        params.put("msg_body", botResponseRequest.getMsgBody());
        params.put("extra", botResponseRequest.getExtra());

        CloseableHttpResponse httpResponse = excuteRequest("/msg/bot-response/keyword", params);
        map = getEntityMapFromResponse(httpResponse);
        logger.info("getKeywordBotResponse:msg_id->{}", map.get("msg_id"));
        return new KeywordResponse(map);
    }

    /**
     * 获取问答机器人回复
     *
     * @param botResponseRequest Constructor: new BotResponseRequest(String userId,MsgBody msgBody)
     * @return QaResponse
     * @throws ClientException
     * @Required user_id 用户唯一标识  [ 1 .. 128 ] characters
     * @Required MsgBody 消息体
     * extra 自定义字段 <= 1024 characters
     */
    public QaResponse getQABotResponse(BotResponseRequest botResponseRequest) throws ClientException, ServerException {
        params.clear();
        Map map = null;

        params.put("user_id", botResponseRequest.getUserId());
        params.put("msg_body", botResponseRequest.getMsgBody());
        params.put("extra", botResponseRequest.getExtra());

        CloseableHttpResponse httpResponse = excuteRequest("/msg/bot-response/qa", params);
        map = getEntityMapFromResponse(httpResponse);
        logger.info("getQABotResponse:msg_id->{}", map.get("msg_id"));
        return new QaResponse(map);
    }

    /**
     * 获取任务机器人回复
     *
     * @param botResponseRequest Constructor: new BotResponseRequest(String user_id, MsgBody msgBody)
     * @return TaskResponse
     * @throws ClientException 客户端错误
     * @Required user_id 用户唯一标识  [ 1 .. 128 ] characters
     * @Required MsgBody 消息体
     * extra 自定义字段 <= 1024 characters
     */
    public TaskResponse getTaskBotResponse(BotResponseRequest botResponseRequest) throws ClientException, ServerException {
        params.clear();
        Map map = null;

        params.put("user_id", botResponseRequest.getUserId());
        params.put("msg_body", botResponseRequest.getMsgBody());
        params.put("extra", botResponseRequest.getExtra());

        CloseableHttpResponse httpResponse = excuteRequest("/msg/bot-response/task", params);
        map = getEntityMapFromResponse(httpResponse);
        logger.info("getTaskBotResponse:msg_id->{}", map.get("msg_id"));
        return new TaskResponse(map);
    }

    /**
     * 同步发给用户的消息
     *
     * @param syncRequest Constructor: new SyncRequest(String userId, MsgBody msgBody, String msgTs)
     * @return
     * @throws ClientException
     * @Required userId 用户唯一标识  [ 1 .. 128 ] characters
     * @Required msgTs 消息毫秒级时间戳 >= 1
     * @Required msgBody 消息体
     * extra 自定义字段 <= 1024 characters
     */
    public SyncResponse msgSync(SyncRequest syncRequest) throws ClientException, ServerException {
        params.clear();
        Map map = null;

        params.put("user_id", syncRequest.getUserId());
        params.put("msg_body", syncRequest.getMsgBody());
        params.put("extra", syncRequest.getExtra());
        params.put("msg_ts", syncRequest.getMsgTs());

        CloseableHttpResponse httpResponse = excuteRequest("/msg/sync", params);
        map = getEntityMapFromResponse(httpResponse);
        logger.info("msgSync:msg_id->{}", map.get("msg_id"));

        return new SyncResponse(map);
    }

    public int userAttributeCreate(UserAttributeCreateRequest userAttributeCreateRequest)
            throws ClientException, ServerException {
        params.clear();

        params.put("user_id", userAttributeCreateRequest.getUser_id());
        params.put("user_attribute_user_attribute_value",
                userAttributeCreateRequest.getUser_attribute_user_attribute_value());

        CloseableHttpResponse httpResponse = excuteRequest("/user/user-attribute/create", params);
        int httpCode = httpResponse.getStatusLine().getStatusCode();
        logger.info("userAttributeCreate:{}", httpCode);
        return httpCode;
    }

    /**
     * 获取用户属性列表
     *
     * @param userAttributeListRequest
     * @return
     * @throws ClientException
     * @Required page 页码，代表查看第几页的数据，从1开始 >= 1
     * @Required page_size 每页的属性组数量 [ 1 .. 200 ]
     * filter 过滤条件
     */
    public UserAttributeListResponse userAttributeList(UserAttributeListRequest userAttributeListRequest)
            throws ClientException, ServerException {
        params.clear();
        HashMap<String, Object> useInUserAttributeGroup = new HashMap<>();
        useInUserAttributeGroup.put("use_in_user_attribute_group", userAttributeListRequest.getFilter());
        params.put("page", userAttributeListRequest.getPage());
        params.put("page_size", userAttributeListRequest.getPageSize());
        params.put("filter", useInUserAttributeGroup);

        CloseableHttpResponse httpResponse = excuteRequest("/user-attribute/list", params);
        Map map = null;
        map = getEntityMapFromResponse(httpResponse);
        logger.info("userAttributeList:page_count->{}", map.get("page_count"));
        return new UserAttributeListResponse(map);
    }

    /**
     * 查询历史消息
     *
     * @param historyRequest Constructor: new HistoryRequest(String userId,int num)
     * @return HistoryResponse
     * @throws ClientException 客户端错误
     * @Required userId 用户唯一标识  [ 1 .. 128 ] characters
     * @Required num 一次获取消息的数目  [ 1 .. 50 ]
     * msgId 从这个msg_id开始查询（结果包含此条消息）；为空时查询最新的消息  <= 18 characters
     * direction 翻页方向. Default: "BACKWARD"
     * Enum:"BACKWARD" "FORWARD"
     * BACKWARD: 向旧的消息翻页，查询比传入msg_id更小的消息
     * FORWARD: 先新的消息翻页，查询比传入msg_id更大的消息
     */
    public HistoryResponse msgHistory(HistoryRequest historyRequest) throws ClientException, ServerException {
        params.clear();
        Map map = null;
        params.put("user_id", historyRequest.getUserId());
        params.put("num", historyRequest.getNum());
        params.put("direction", historyRequest.getDirection());
        params.put("msg_id", historyRequest.getMsgId());

        CloseableHttpResponse httpResponse = excuteRequest("/msg/history", params);
        map = getEntityMapFromResponse(httpResponse);
        logger.info("msgHistory:hasMore{}", map.get("has_more"));
        return new HistoryResponse(map);
    }

    /**
     * 接收用户发的消息
     *
     * @param receiveRequest
     * @return ReceiveResponse
     * @throws ClientException 客户端错误
     * @Required user_id 用户唯一标识 [ 1 .. 128 ] characters
     * @Required msgBody 消息体
     * thirdMsgId 接入方唯一msg_id，保证1分钟内的幂等性 <= 64 characters
     * extra 自定义字段 <= 1024 characters
     */
    public ReceiveResponse msgReceive(ReceiveRequest receiveRequest) throws ClientException, ServerException {
        params.clear();
        Map map = null;

        params.put("user_id", receiveRequest.getUserId());
        params.put("msg_body", receiveRequest.getMsgBody());
        params.put("extra", receiveRequest.getExtra());
        params.put("third_msg_id", receiveRequest.getThirdMsgId());

        CloseableHttpResponse httpResponse = excuteRequest("/msg/receive", params);
        map = getEntityMapFromResponse(httpResponse);
        logger.info("msgReceive:msg_id->{}", map.get("msg_id"));

        return new ReceiveResponse(map);
    }

}