import com.alibaba.fastjson.JSONArray;
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
import request.msg.*;
import request.user.UserAttributeCreateRequest;
import request.user.UserAttributeListRequest;
import request.user.UserCreateRequest;
import response.msg.*;
import response.user.UserAttributeListResponse;
import util.Log;
import util.ParamsCheck;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import java.io.*;
import java.net.URI;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Laiye Wulai SDK for Java Programming Language
 * @Author zhangtao@laiye.com
 */
public class WulaiClient {
    private final static String CONTENT_TYPE = "application/json";
    private final static int DEFAULT_TIME_OUT = 15000;
    private static MessageDigest md = null;

    static {
        try {
            md = MessageDigest.getInstance("SHA");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int timeout = 15000;
    private int retryTimes = 5;
    private PoolingHttpClientConnectionManager cm = null;
    private CloseableHttpClient httpClient = null;
    private URI endpoint = URI.create("https://openapi.wul.ai/");
    private HashMap<String, Integer> opts = null;
    private String PUBKEY = null;
    private String SECRET = null;
    private String ApiVersion = null;
    private Log log;
    private HashMap<String, Object> params=new HashMap<String,Object>();;
    private WulaiClient() {}


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
     * @param
     * @return
     */
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

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public void setRetryTimes(int retryTimes) {
        this.retryTimes = retryTimes;
    }

    // 将string 转化为HashMap
    private Map parseToHashMap(String str){
        return JSONObject.parseObject(str,HashMap.class);
    }


    public void setEndpoint(URI endpoint) {
        this.endpoint = endpoint;
    }

    /**
     * 初始化http连接池
     */
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
        }
    }

    public void setPools(PoolingHttpClientConnectionManager cm) {
        this.cm = cm;
        httpClient = HttpClients.custom().setConnectionManager(cm).setRetryHandler(retryHandler()).build();
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
     * @throws ClientException 客户端异常
     */
    public synchronized String processCommonRequest(String action, String data) throws ClientException, ServerException {
        HttpEntity httpEntity = null;
        HttpEntityEnclosingRequestBase postrequest = null;
        String responseBody = "";
        CloseableHttpResponse httpResponse = null;
        HttpContext context = HttpClientContext.create();
        int httpCode=0;

        if (httpClient == null) {
            initPools();
        }
        // 获取request 对象，设置参数
        postrequest = (HttpEntityEnclosingRequestBase) getRequest(action, timeout);
        log.debug("data:" + data, true);
        postrequest.setEntity(new StringEntity(data, "UTF-8"));
        postrequest = setRequestParams(postrequest);
        try {
            httpResponse = httpClient.execute(postrequest, context);
        } catch (ConnectTimeoutException | HttpHostConnectException | UnknownHostException e) {
            throw new ClientException(ClientExceptionConstant.SDK_SERVER_UNREACHABLE, e.getMessage());
        } catch (IOException e) {
            throw new ServerException(ClientExceptionConstant.SDK_HTTP_ERROR, e.getMessage(),
                    httpCode);
        }
        httpCode = httpResponse.getStatusLine().getStatusCode();
        if (httpCode == 200) {
            log.debug("http good",true);
        } else if (httpCode == 400) {
            log.debug(httpResponse.toString(), true);
            throw new ServerException(ClientExceptionConstant.SDK_INVALID_PARAMS, httpResponse.toString(), httpCode);
        } else if (httpCode == 401) {
            log.error("Invalid credential", true);
            throw new ClientException(ClientExceptionConstant.SDK_INVALID_CREDENTIAL,
                    " Invalid credential , please check the pubkey , secret and apiVersion");
        } else if (httpCode == 500) {
            log.debug(httpResponse.toString(), true);
            throw new ServerException(ClientExceptionConstant.SDK_HTTP_ERROR, httpResponse.toString(), httpCode);
        } else {
            throw new ServerException(ClientExceptionConstant.SDK_HTTP_ERROR, httpResponse.toString(), httpCode);
        }
        httpEntity = httpResponse.getEntity();
        if (httpEntity != null) {
            try {
                responseBody = EntityUtils.toString(httpEntity, "UTF-8");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        log.debug("HttpEntity" + responseBody, true);
        Map map = (Map) JSONObject.parseObject(responseBody, Map.class);
        ArrayList list = new ArrayList();
        for (Object obj : map.keySet()) {
            log.debug(obj.toString() + ":" + map.get(obj), true);
            list.add(map.get(obj));
        }

        if (log.getDEBUG()) {
            log.debug("responseEntity:" + list.toString(), true);
            log.debug(responseBody, true);
            for (Header header : httpResponse.getAllHeaders()) {
                log.info("{0}:{1}", header.getName(), header.getValue());
            }
        }
        log.info("httpcode:{0},responseBody:{1}", httpCode, responseBody.toString());
        return responseBody;
    }

    /**
     *
     * @param action 请求路径
     * @param data 请求参数
     * @return 返回 CloseableHttpResponse
     * @throws ClientException 客户端异常
     */
    private synchronized CloseableHttpResponse excuteRequest(String action, HashMap<String, Object> data)
            throws ClientException, ServerException {
        HttpEntityEnclosingRequestBase postrequest = null;
        CloseableHttpResponse httpResponse = null;
        String body =null;

        if (httpClient == null) {
            initPools();
        }
        postrequest = (HttpEntityEnclosingRequestBase) getRequest(action, timeout);
        postrequest = setRequestParams(postrequest);
        body = JSONObject.toJSON(data).toString();
        log.debug(body, true);
        postrequest.setEntity(new StringEntity(body, "UTF-8"));
        HttpContext context = HttpClientContext.create();

        try {
            httpResponse = httpClient.execute(postrequest, context);

        } catch (IOException e) {
            e.printStackTrace();
        }
        checkHttpCode(httpResponse);
        return httpResponse;
    }
    private void checkHttpCode(CloseableHttpResponse response) throws ClientException, ServerException {
        int httpCode=0;
        Map map=null;
        String responseBody = null;
        httpCode=response.getStatusLine().getStatusCode();
        if (httpCode!=200) {
            HttpEntity entity = response.getEntity();
            try {
                responseBody = EntityUtils.toString(entity, "UTF-8");
            } catch (IOException e) {
                e.printStackTrace();
            }
            map = JSONObject.parseObject(responseBody);
        }
        switch (httpCode){
            case 400:
                throw new ClientException(ClientExceptionConstant.SDK_INVALID_PARAMS,map.get("message").toString());
            case 401:
                throw new ClientException(ClientExceptionConstant.SDK_INVALID_CREDENTIAL,map.get("message").toString());
            case 403:
            case 429:
            case 404:
                throw new ClientException(ClientExceptionConstant.SDK_INVALID_REQUEST,map.get("message").toString());
            case 409:
            case 499:
                throw new ClientException(ClientExceptionConstant.SDK_HTTP_ERROR,map.get("message").toString());
            case 500:
            case 501:
            case 504:
            case 503:
                throw new ServerException(map.get("code").toString(),map.get("message").toString(),httpCode);
            default:
                break;
        }
    }

    /**
     * 创建用户
     * @param userCreateRequest
     * Constructor: new UserCreateRequest(String user_id)
     * @Required userId 用户唯一标识 [ 1 .. 128 ] characters
     * avatarUrl 用户头像url <= 512 characters
     * nickname 用户昵称 <= 128 characters
     * @return httpCode
     * @throws ClientException 客户端异常
     */
    public int userCreate(UserCreateRequest userCreateRequest) throws ClientException, ServerException {
        params = new HashMap<String, Object>();
        params.put("user_id", userCreateRequest.getUserId());
        params.put("avatar_url", userCreateRequest.getAvatarUrl());
        params.put("nickname", userCreateRequest.getNickname());

        CloseableHttpResponse httpResponse= excuteRequest("/user/create", params);
        return httpResponse.getStatusLine().getStatusCode();
    }

    /**
     * 获取机器人回复
     * @param botResponseRequest
     * Constructor: new BotResponseRequest(String userId, Object msgBody)
     * @Required userId 用户唯一标识  [ 1 .. 128 ] characters
     * @Required MsgBody 消息体
     * extra 自定义字段 <= 1024 characters
     * @return BotResponse
     * @throws ClientException 客户端错误
     */
    public BotResponse getBotResponse(BotResponseRequest botResponseRequest) throws ClientException, ServerException {
        params.clear();
        HashMap<String, Object> text = new HashMap<String, Object>();
        HashMap<String, Object> contentmap = new HashMap<String, Object>();
        Map map=null;

        if (botResponseRequest.getMsgBody() instanceof String) {
            contentmap.put("content", (String) botResponseRequest.getMsgBody());
            text.put("text", contentmap);
        }
        params.put("user_id", botResponseRequest.getUserId());
        params.put("msg_body", text);
        params.put("extra", botResponseRequest.getExtra());

        CloseableHttpResponse httpResponse= excuteRequest("/msg/bot-response", params);
        try {
            map= parseToHashMap(EntityUtils.toString(httpResponse.getEntity(),"UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        BotResponse botResponse =new BotResponse();
        botResponse.setDispatch((boolean)map.get("is_dispatch"));
        botResponse.setSuggestedResponse(JSONArray.parseArray(map.get("suggested_response").toString()).toArray());
        botResponse.setMsgId((String)map.get("msg_id"));
        return botResponse;
    }


    /**
     * 获取关键字机器人回复
     * @param botResponseRequest
     * Constructor: new BotResponseRequest(String userId, Object msgBody)
     * @Required userId 用户唯一标识 [ 1 .. 128 ] characters
     * @Required MsgBody 消息体
     * extra 自定义字段 <= 1024 characters
     * @return KeywordResponse
     * @throws ClientException 客户端错误
     */
    public KeywordResponse getKeywordBotResponse(BotResponseRequest botResponseRequest) throws ClientException, ServerException {
        params.clear();
        Map map=null;
        HashMap<String, Object> text = new HashMap<String, Object>();
        HashMap<String, Object> contentmap = new HashMap<String, Object>();

        if (botResponseRequest.getMsgBody() instanceof String) {
            contentmap.put("content", (String) botResponseRequest.getMsgBody());
            text.put("text", contentmap);
        }
        params.put("user_id", botResponseRequest.getUserId());
        params.put("msg_body", text);
        params.put("extra", botResponseRequest.getExtra());

        CloseableHttpResponse httpResponse= excuteRequest("/msg/bot-response/keyword", params);
        try {
            map= parseToHashMap(EntityUtils.toString(httpResponse.getEntity(),"UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        KeywordResponse keywordResponse =new KeywordResponse();
        keywordResponse.setDispatch((boolean)map.get("is_dispatch"));
        keywordResponse.setKeywordSuggestedResponse(JSONArray.parseArray(map.get("keyword_suggested_response")
                .toString()).toArray());
        keywordResponse.setMsgId((String)map.get("msg_id"));
        return keywordResponse;
    }

    /**
     * 获取问答机器人回复
     * @param botResponseRequest
     * Constructor: new BotResponseRequest(String userId,Object msgBody)
     * @Required user_id 用户唯一标识  [ 1 .. 128 ] characters
     * @Required MsgBody 消息体
     * extra 自定义字段 <= 1024 characters
     * @return QaResponse
     * @throws ClientException
     */
    public QaResponse getQABotResponse(BotResponseRequest botResponseRequest) throws ClientException, ServerException {
        params.clear();
        Map map=null;
        HashMap<String, Object> text = new HashMap<String, Object>();
        HashMap<String, Object> contentmap = new HashMap<String, Object>();

        if (botResponseRequest.getMsgBody() instanceof String) {
            contentmap.put("content", (String) botResponseRequest.getMsgBody());
            text.put("text", contentmap);
        }
        params.put("user_id", botResponseRequest.getUserId());
        params.put("msg_body", text);
        params.put("extra", botResponseRequest.getExtra());

        CloseableHttpResponse httpResponse= excuteRequest("/msg/bot-response/qa", params);
        try {
            map= parseToHashMap(EntityUtils.toString(httpResponse.getEntity(),"UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        QaResponse qaResponse =new QaResponse();
        qaResponse.setDispatch((boolean)map.get("is_dispatch"));
        qaResponse.setQaSuggestedResponse(JSONArray.parseArray(map.get("qa_suggested_response").toString()).toArray());
        qaResponse.setMsgId((String)map.get("msg_id"));
        return qaResponse;
    }

    /**
     * 获取任务机器人回复
     * @param botResponseRequest
     * Constructor: new BotResponseRequest(String user_id, Object msgBody)
     * @Required user_id 用户唯一标识  [ 1 .. 128 ] characters
     * @Required MsgBody 消息体
     * extra 自定义字段 <= 1024 characters
     * @return TaskResponse
     * @throws ClientException 客户端错误
     */
    public TaskResponse getTaskBotResponse(BotResponseRequest botResponseRequest) throws ClientException, ServerException {
        params.clear();
        Map map=null;
        HashMap<String, Object> text = new HashMap<String, Object>();
        HashMap<String, Object> contentmap = new HashMap<String, Object>();

        if (botResponseRequest.getMsgBody() instanceof String) {
            contentmap.put("content", botResponseRequest.getMsgBody());
            text.put("text", contentmap);
        }
        params.put("user_id", botResponseRequest.getUserId());
        params.put("msg_body", text);
        params.put("extra", botResponseRequest.getExtra());

        CloseableHttpResponse  httpResponse= excuteRequest("/msg/bot-response/task", params);
        try {
            map= parseToHashMap(EntityUtils.toString(httpResponse.getEntity(),"UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        TaskResponse taskResponse =new TaskResponse();
        taskResponse.setDispatch((boolean)map.get("is_dispatch"));
        taskResponse.setTaskSuggestedResponse(JSONArray.parseArray(map.get("task_suggested_response")
                .toString()).toArray());
        taskResponse.setMsgId((String)map.get("msg_id"));
        return taskResponse;
    }

    /**
     * 同步发给用户的消息
     * @param syncRequest
     * Constructor: new SyncRequest(String userId, Object msgBody, String msgTs)
     * @Required userId 用户唯一标识  [ 1 .. 128 ] characters
     * @Required msgTs 消息毫秒级时间戳 >= 1
     * @Required msgBody 消息体
     * extra 自定义字段 <= 1024 characters
     * @return
     * @throws ClientException
     */
    public SyncResponse msgSync(SyncRequest syncRequest) throws ClientException, ServerException {
        params.clear();
        Map map=null;
        HashMap<String, Object> text = new HashMap<String, Object>();
        HashMap<String, Object> contentmap = new HashMap<String, Object>();

        if (syncRequest.getMsgBody() instanceof String) {
            contentmap.put("content", syncRequest.getMsgBody());
            text.put("text", contentmap);
        }
        params.put("user_id", syncRequest.getUserId());
        params.put("msg_body", text);
        params.put("extra", syncRequest.getExtra());
        params.put("msg_ts", syncRequest.getMsgTs());


        CloseableHttpResponse httpResponse= excuteRequest("/msg/sync", params);
        try {
            map= parseToHashMap(EntityUtils.toString(httpResponse.getEntity(),"UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        SyncResponse syncResponse=new SyncResponse();
        syncResponse.setMsgId(map.get("msg_id").toString());
        return syncResponse;
    }

    public String userAttributeCreate(UserAttributeCreateRequest userAttributeCreateRequest) throws ClientException, ServerException {
        params.clear();
        params.put("user_id", userAttributeCreateRequest.getUser_id());
        params.put("user_attribute_user_attribute_value", userAttributeCreateRequest
                .getUser_attribute_user_attribute_value());

        CloseableHttpResponse httpResponse= excuteRequest("/user/user-attribute/create",params);
        return null;
    }

    /**
     * 获取用户属性列表
     * @param userAttributeListRequest
     * @Required page 页码，代表查看第几页的数据，从1开始 >= 1
     * @Required page_size 每页的属性组数量 [ 1 .. 200 ]
     * filter 过滤条件
     * @return
     * @throws ClientException
     */
    public UserAttributeListResponse userAttributeList(UserAttributeListRequest userAttributeListRequest)
            throws ClientException, ServerException {
        params.clear();
        HashMap<String,Object> useInUserAttributeGroup=new HashMap<String, Object>();
        useInUserAttributeGroup.put("use_in_user_attribute_group",userAttributeListRequest.getFilter());
        params.put("page", userAttributeListRequest.getPage());
        params.put("page_size", userAttributeListRequest.getPageSize());
        params.put("filter",useInUserAttributeGroup);

        CloseableHttpResponse httpResponse= excuteRequest("/user-attribute/list",params);
        Map map=null;
        try {
            map= parseToHashMap(EntityUtils.toString(httpResponse.getEntity(),"UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        UserAttributeListResponse userAttributeListResponse=new UserAttributeListResponse();
        userAttributeListResponse.setPageCount((int)map.get("page_count"));
        userAttributeListResponse.setUserAttributeUserAttributeValues(JSONArray.
                parseArray(map.get("user_attribute_user_attribute_values").toString()).toArray());
        return userAttributeListResponse;
    }

    /**
     * 查询历史消息
     * @param historyRequest
     * Constructor: new HistoryRequest(String userId,int num)
     * @Required userId 用户唯一标识  [ 1 .. 128 ] characters
     * @Required num 一次获取消息的数目  [ 1 .. 50 ]
     * msgId 从这个msg_id开始查询（结果包含此条消息）；为空时查询最新的消息  <= 18 characters
     * direction 翻页方向. Default: "BACKWARD"
     *      Enum:"BACKWARD" "FORWARD"
     *      BACKWARD: 向旧的消息翻页，查询比传入msg_id更小的消息
     *      FORWARD: 先新的消息翻页，查询比传入msg_id更大的消息
     * @return HistoryResponse
     * @throws ClientException 客户端错误
     */
    public HistoryResponse msgHistory(HistoryRequest historyRequest) throws ClientException, ServerException {
        params.clear();
        Map map=null;
        params.put("user_id",historyRequest.getUserId());
        params.put("num",historyRequest.getNum());
        params.put("direction",historyRequest.getDirection());
        params.put("msg_id",historyRequest.getMsgId());

        CloseableHttpResponse  httpResponse= excuteRequest("/msg/history",params);
        try {
            map= parseToHashMap(EntityUtils.toString(httpResponse.getEntity(),"UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        HistoryResponse historyResponse=new HistoryResponse();
        historyResponse.setHasMore((boolean) map.get("has_more"));
        historyResponse.setMsg(JSONArray.parseArray(map.get("msg").toString()).toArray());
        return historyResponse;
    }

    /**
     * 接收用户发的消息
     * @param receiveRequest
     * @Required user_id 用户唯一标识 [ 1 .. 128 ] characters
     * @Required msgBody 消息体
     * thirdMsgId 接入方唯一msg_id，保证1分钟内的幂等性 <= 64 characters
     * extra 自定义字段 <= 1024 characters
     * @return
     * @throws ClientException
     */
    public ReceiveResponse msgReceive(ReceiveRequest receiveRequest) throws ClientException, ServerException {
        params.clear();
        Map map=null;
        HashMap<String,Object> text=new HashMap<String, Object>();
        HashMap<String,Object> contentmap=new HashMap<String, Object>();

        if (receiveRequest.getMsgBody() instanceof String){
            contentmap.put("content",receiveRequest);
            text.put("text",contentmap);
        }
        params.put("user_id",receiveRequest.getUserId());
        params.put("msg_body",text);
        params.put("extra",receiveRequest.getExtra());
        params.put("third_msg_id",receiveRequest.getThirdMsgId());


        CloseableHttpResponse httpResponse= excuteRequest("/msg/receive",params);
        try {
            map= parseToHashMap(EntityUtils.toString(httpResponse.getEntity(),"UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ReceiveResponse receiveResponse=new ReceiveResponse();
        receiveResponse.setMsg_id(map.get("msg_id").toString());
        return receiveResponse;
    }

}