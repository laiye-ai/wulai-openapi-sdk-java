import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.UUID;

class Wulai {
    private final static String API_VERSION = "/v2";
    private static URI ENDPOINT;
    private static String PUBKEY;
    private static String SECRET;
    private static String nonce;
    private static HttpPost request;
    private static HttpResponse response;
    private static MessageDigest md;

    static {
        try {
            md = MessageDigest.getInstance("SHA");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Wulai() {
    }

    private Wulai(String pubkey, String secret, String endpoint) {
        PUBKEY = pubkey;
        SECRET = secret;
        ENDPOINT = URI.create(endpoint);
    }

    public static Wulai create(String pubkey, String secret, String endpoint) {
        Wulai wulai = new Wulai(pubkey, secret, endpoint);
        request = new HttpPost(ENDPOINT);
        request.setHeader("content-type", "application/json");
        request.setHeader("Connection", "Keep-Alive");
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

    //获取openapi url
    private void getAbsoluteURI(String interface_type) throws URISyntaxException {
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

    }

    private HashMap executeRequest(HashMap params) {
        try {
            nonce = UUID.randomUUID().toString().replace("-", "");
            Long timestamp = System.currentTimeMillis() / 1000;
            request.setHeader("Api-Auth-pubkey", PUBKEY);
            request.setHeader("Api-Auth-nonce", nonce);
            request.setHeader("Api-Auth-timestamp", String.valueOf(timestamp));
            request.setHeader("Api-Auth-sign", getSign(nonce, timestamp, SECRET));
            HttpClient client = HttpClientBuilder.create().build();
            Object body = JSONObject.toJSON(params);
            request.setEntity(new StringEntity(body.toString(), "UTF-8"));
            response = client.execute(request);
            HttpEntity entity = response.getEntity();
            String responseString = EntityUtils.toString(entity, "UTF-8");
            HashMap res = new HashMap();
            res.put("response", responseString);
            res.put("status", response.getStatusLine());
            return res;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}