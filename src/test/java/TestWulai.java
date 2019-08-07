import exceptions.Client_Exception;
import exceptions.Server_Exception;
import http.Common_Request;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

public class TestWulai {
    private static WulaiClient client;
    HashMap<String, Object> params = null;

    private HashMap getClearMap() {
        if (params == null) {
            synchronized (TestWulai.class) {
                params = new HashMap<String, Object>();
            }
        }
        params.clear();
        return params;
    }

    @Test
    public void Test() throws Client_Exception, IOException {
        String pubkey = System.getenv("pubkey");
        String secret = System.getenv("secret");
        client = WulaiClient.create(pubkey,
                secret, "https://openapi.wul.ai", "v2");
        Long timestamp;
        for (int i = 0; i < 5; i++) {
            timestamp = System.currentTimeMillis();
            testUserCreate();
            Long timestamp2 = System.currentTimeMillis();
            Long time1 = timestamp2 - timestamp;
            testGetRobotRes();
            Long time2 = System.currentTimeMillis() - timestamp2;
            System.out.println("t1:" + time1);
            System.out.println("t2:" + time2);
        }

    }

    public void testUserCreate() throws Client_Exception {
        params = getClearMap();
        params.put("user_id", "zhangtao@test");
        params.put("nickname", "tom");
        params.put("avatar_url", "sb");
        HttpPost request = (HttpPost) Common_Request.getRequest(params, "/user/create", "post");

        HttpResponse response = client.process_common_request(request);
        if (response.getStatusLine().getStatusCode() != 200) {
            System.out.println(response.getStatusLine().getStatusCode());
            throw new Server_Exception("", "", response.getStatusLine().getStatusCode());
        }
    }

    public void testGetRobotRes() throws Client_Exception, IOException {
        params = getClearMap();
        params.put("user_id", "zhangtao@test");
        HashMap<String, Object> msgBody = new HashMap<String, Object>();
        HashMap<String, Object> text = new HashMap<String, Object>();
        text.put("content", "你是谁");
        msgBody.put("text", text);
        params.put("msg_body", msgBody);
        HttpPost request = (HttpPost) Common_Request.getRequest(params, "/msg/bot-response", "post");
        HttpResponse response = client.process_common_request(request);
        Header[] headers = response.getAllHeaders();
        for (Header head : headers) {
            System.out.println(head.toString());
        }


        if (response.getStatusLine().getStatusCode() != 200) {
            throw new Server_Exception("1", "服务端错误", response.getStatusLine().getStatusCode());
        } else {
            System.out.println(EntityUtils.toString(response.getEntity(), "UTF-8"));
        }

    }

}
