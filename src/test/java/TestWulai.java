import exceptions.Client_Exception;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestWulai {
    final static Logger logger = LoggerFactory.getLogger(TestWulai.class);
    private static WulaiClient clientv1;
    private static WulaiClient clientv2;
    private static WulaiClient clientv3;
    private static String name = "zhangtao@test";

    @Before
    public void setup() {
        try {
            clientv1 = WulaiClient.create(System.getenv("pubkey"), System.getenv("secret"), "v1");
            clientv2 = WulaiClient.create(System.getenv("pubkey"), System.getenv("secret"), "v2");
            clientv3 = WulaiClient.create(System.getenv("pubkey"), System.getenv("secret"), "v3");
        } catch (Client_Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUserCreate() throws Client_Exception {
        String data = String.format("{\"user_id\":\"%s\",\"avatar_url\":\"%s\",\"nikename\":\"%s\"}", name, "sb", "tom");
        try {
            clientv1.processCommonRequest("/user/create", data, "post");
            clientv2.processCommonRequest("/user/create", data, "post");
            clientv3.processCommonRequest("/user/create", data, "get");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetRobotRes() {
        String data = String.format("{\"user_id\":\"%s\",\"msg_body\":{\"text\":{\"content\":\"%s\"}},\"extra\":\"%s\"}", name, "你是谁", "");
        clientv1.processCommonRequest("/msg/bot-response", data, "get");
        clientv1.processCommonRequest("/msg/bot-response", data, "post");
        clientv2.processCommonRequest("/msg/bot-response", data, "get");
        clientv2.processCommonRequest("/msg/bot-response", data, "post");

    }


}
