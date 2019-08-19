import exceptions.Client_Exception;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;

public class TestWulai  {
    final static Logger logger = LoggerFactory.getLogger(TestWulai.class);
    private static String name = "zhangtao@test";
    static WulaiClient client;
//    public void setup() {
//        try {
//            WulaiClient client2 = WulaiClient.getInstance(System.getenv("pubkey"), System.getenv("secret"), "v2", false);
//        } catch (Client_Exception e) {
//            e.printStackTrace();
//        }
//    }

    @Test
    public void testwulai() throws Client_Exception, IOException {
        WulaiClient client1 = WulaiClient.getInstance("NMZXkOobOmLBbZdPoeERyQ53qXWyySRt006e1805f2230492a8", "U0xdSUh9PbW5jx8Qf1vk", "v1", false);

        String data = String.format("{\"user_id\":\"%s\",\"msg_body\":{\"text\":{\"content\":\"%s\"}},\"extra\":\"%s\"}", name, "你是谁", "");
        Long time1=System.currentTimeMillis();

        for (int i=0;i<20;i++){
            WulaiClient client2 = WulaiClient.getInstance("84Mb38jJR6Bg7qB3MT09cSHPdrEImIgv00362b370af3cc02eb",
                    "x9xBAnz8JHrI8GdsdVoJ", "v2", true);
            client2.processCommonRequest("/msg/bot-response", data, "post");
            System.err.println(i);
        }

        System.out.println((System.currentTimeMillis()-time1)/20);

    }


}



