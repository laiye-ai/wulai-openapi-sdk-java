import entity.requestentity.msg.BotResponse;
import entity.requestentity.user.UserCreate;
import exceptions.ClientException;
import exceptions.ServerException;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class TestWulai  {
    final static Logger logger = LoggerFactory.getLogger(TestWulai.class);
    private static String name = "zhangtao@test";
    static WulaiClient client;
    static int num;


    public static void main(String[] args) throws ClientException, InterruptedException {
        WulaiClient.init(System.getenv("pubkey"),
                System.getenv("secret"), "v2", true);
        for(int i=0;i<15;i++){
            new TestCreateUser().start();
            new TestBotResponse().start();
        }

    }


}
class TestCreateUser extends Thread{
    WulaiClient wulaiClient=WulaiClient.getInstance();
    int num=0;
    TestCreateUser() throws ClientException {
    }

    public void run() {
        UserCreate userCreate=new UserCreate();
        try {
            userCreate.setUser_id("zhangtao@test");
        } catch (ClientException e) {
            e.printStackTrace();
        }
        try {
            while (true) {
                wulaiClient.userCreate(userCreate);

            }
        } catch (ServerException e) {
            e.printStackTrace();
        }
    }
}

class TestBotResponse  extends Thread{
    WulaiClient wulaiClient=WulaiClient.getInstance();

    TestBotResponse() throws ClientException {
    }

    public void run() {
        BotResponse botResponse =new BotResponse();
        botResponse.setUserId("zhangtao@test");
        botResponse.setMsgBody("你是谁");
        botResponse.setExtra("");
        try {
            while (true) {
                wulaiClient.botResponse(botResponse);
            }
        } catch (ServerException e) {
            e.printStackTrace();
        }
    }
}



