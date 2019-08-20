import exceptions.ClientException;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class TestWulai  {
    final static Logger logger = LoggerFactory.getLogger(TestWulai.class);
    private static String name = "zhangtao@test";
    static WulaiClient client;


    @Test
    public void testwulai() throws ClientException, IOException {

        WulaiClient client1 = WulaiClient.getInstance(System.getenv("pubkeyv1"), System.getenv("secretv1"), "v1", false);

        WulaiClient client1 = WulaiClient.getInstance(System.getenv("pubkeyv1"), System.getenv("secretv1"), "v1", false);


        String data = String.format("{\"user_id\":\"%s\",\"msg_body\":{\"text\":{\"content\":\"%s\"}},\"extra\":\"%s\"}", name, "你是谁", "");
        Long time1=System.currentTimeMillis();

        for (int i=0;i<3;i++){
            WulaiClient client2 = WulaiClient.getInstance(System.getenv("pubkey"),
                    System.getenv("secret"), "v2", true);
            client2.processCommonRequest("/msg/bot-response", data, "post");
            System.err.println(i);
        }

        System.out.println((System.currentTimeMillis()-time1)/20);

    }


}



