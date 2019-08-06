import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import exceptions.Server_Exception;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

class Wulai {
    private static String API_VERSION;
    private static URI ENDPOINT;
    private static String PUBKEY;
    private static String SECRET;
    private static String nonce;
    private static HttpPost request;
    private static HttpResponse response;
    private static MessageDigest md;
    private static HttpClient client = HttpClientBuilder.create().build();
    private static Long timestamp;
    private static HashMap<String, Object> resMap;

    static {
        try {
            md = MessageDigest.getInstance("SHA");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Wulai() {
    }

    private Wulai(String pubkey, String secret, String endpoint, String api_version) {
        PUBKEY = pubkey;
        SECRET = secret;
        API_VERSION = "/" + api_version;
        ENDPOINT = URI.create(endpoint);
    }

    public static Wulai create(String pubkey, String secret, String endpoint, String api_version) {
        Wulai wulai = new Wulai(pubkey, secret, endpoint, api_version);
        request = new HttpPost(ENDPOINT);
        request.setHeader("content-type", "application/json");
        request.setHeader("Connection", "Keep-Alive");
        request.setHeader("Api-Auth-pubkey", PUBKEY);
        return wulai;
    }

    private static String getSign(String nonce, Long timeStamp, String secret) {
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
        }
        return null;
    }

    private void log(String log) {
        System.out.println(log);
    }

    private void log(int i) {
        System.out.println(i);
    }

    private void print(Object obj) {
        System.out.println(obj.toString());
    }

    private HashMap makeStrToMap(String responseStr) {
        return new Gson().fromJson(responseStr, HashMap.class);

    }

    private List printMapKey(HashMap hashMap) {
        List list = new ArrayList();
        for (Object obj : hashMap.keySet()) {
            list.add(obj);
        }
        return list;
    }

    private void getRequest(String interface_type) {
        switch (interface_type) {
            case "getUserList":
                ENDPOINT = ENDPOINT.resolve(API_VERSION + "/user-attribute/list");
                break;
            case "createUserAttribute":
                ENDPOINT = ENDPOINT.resolve(API_VERSION + "/user/user-attribute/create");
                break;
            case "createUser":
                ENDPOINT = ENDPOINT.resolve(API_VERSION + "/user/create");
                break;
            case "getKeywordRes":
                ENDPOINT = ENDPOINT.resolve(API_VERSION + "/msg/bot-response/keyword");
                break;
            case "sensMsg":
                ENDPOINT = ENDPOINT.resolve(API_VERSION + "/msg/send");
                break;
            case "getMsgHistory":
                ENDPOINT = ENDPOINT.resolve(API_VERSION + "/msg/history");
                break;
            case "getTaskbotRes":
                ENDPOINT = ENDPOINT.resolve(API_VERSION + "/msg/bot-response/task");
                break;
            case "getRobotRes":
                ENDPOINT = ENDPOINT.resolve(API_VERSION + "/msg/bot-response");
                break;
            case "syncMsg":
                ENDPOINT = ENDPOINT.resolve(API_VERSION + "/msg/sync");
                break;
            case "receiveMsg":
                ENDPOINT = ENDPOINT.resolve(API_VERSION + "/msg/receive");
                break;
            case "getQabotRes":
                ENDPOINT = ENDPOINT.resolve(API_VERSION + "/msg/bot-response/qa");
                break;
            default:
                ENDPOINT = ENDPOINT.resolve("");
        }
        request.setURI(ENDPOINT);
        nonce = UUID.randomUUID().toString().replace("-", "");
        timestamp = System.currentTimeMillis() / 1000;
        request.setHeader("Api-Auth-nonce", nonce);
        request.setHeader("Api-Auth-timestamp", String.valueOf(timestamp));
        request.setHeader("Api-Auth-sign", getSign(nonce, timestamp, SECRET));
    }

    private HttpResponse executeRequest(HashMap params) {
        try {
            Object body = JSONObject.toJSON(params);
            request.setEntity(new StringEntity(body.toString(), "UTF-8"));
            return client.execute(request);
        } catch (ClientProtocolException e) {
            return null;
        } catch (IOException e) {
            return null;
        }
    }


    public HashMap getUserList(int page, int page_size, Object filter) {
        getRequest("getUserList");
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("page", page);
        params.put("page_size", page_size);
        params.put("filter", filter);
        response = executeRequest(params);
        return null;
    }


    public HashMap createUserAttribute(String user_id) {
        getRequest("createUserAttribute");
        HashMap<String, Object> params = new HashMap<String, Object>();
        HashMap<String, Object> user_attribute_user_attribute_value = new HashMap<String, Object>();
        params.put("user_id", user_id);
        HashMap<String, Object> user_attribute = new HashMap<String, Object>();
        HashMap<String, Object> user_attribute_value = new HashMap<String, Object>();
        user_attribute.put("id", "101111");
        user_attribute_value.put("name", "测试属性");
        user_attribute_value.put("test", "测试属性1");
        user_attribute_user_attribute_value.put("user_attribute", user_attribute);
        user_attribute_user_attribute_value.put("user_attribute_value", user_attribute_value);
        params.put("user_attribute_user_attribute_value", user_attribute_user_attribute_value);
        response = executeRequest(params);
        return params;
    }


    public HashMap createUser(String user_id, String nickname, String avatar_url) {
        getRequest("createUser");
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("user_id", user_id);
        params.put("nickname", nickname);
        params.put("avatar_url", avatar_url);
        response = executeRequest(params);
        return null;
    }


    public HashMap getRobotRes(String user_id, Object message, String extra) {
        try {
            getRequest("getRobotRes");
            HashMap<String, Object> params = new HashMap<String, Object>();
            params.put("user_id", user_id);
            if (extra != null) {
                params.put("extra", extra);
            }
            HashMap<String, Object> msgBody = new HashMap<String, Object>();
            HashMap<String, Object> text = new HashMap<String, Object>();
            text.put("content", message);
            msgBody.put("text", text);
            params.put("msg_body", msgBody);
            response = executeRequest(params);
            HttpEntity entity = response.getEntity();
            String responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
            HashMap hashMap = makeStrToMap(responseString);
            return hashMap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }


    public void test() {
        try {
            getRequest("");
            response = executeRequest(new HashMap<>());
            if (response.getStatusLine().getStatusCode() == 404) {
                throw new Server_Exception(response.getStatusLine().getStatusCode(), 404, "endpoint is not a legal url or can not reach it ,please check!");
            }
        } catch (Server_Exception e) {
            log("网址错了啊，你是在测试吗");
            log(response.getStatusLine().getStatusCode());
        }
    }


}