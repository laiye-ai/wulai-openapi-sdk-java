import entity.requestentity.msg.BotResponse;
import entity.requestentity.msg.MsgBody;
import entity.requestentity.user.UserCreate;
import exceptions.ClientException;
import exceptions.ServerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestWulai  {
    final static Logger logger = LoggerFactory.getLogger(TestWulai.class);
    private static String name = "zhangtao@test";
    static WulaiClient client;
    static int num;

    public static void main(String[] args) throws ClientException {
        WulaiClient wulaiClient=new WulaiClient(System.getenv("pubkey"),
                System.getenv("secret"), "v2", true);
        for(int i=0;i<15;i++){
//            new TestCreateUser().start();
//            new TestBotResponse().start();
            String usercreate=String.format("{\"user_id\":\"%s\"",name);
            String botresponse = String.format("{\"user_id\":\"%s\",\"msg_body\":{\"text\":{\"content\":\"%s\"}},\"extra\":\"%s\"}", name, "你是谁", "");

            wulaiClient.processCommonRequest("/user/create",usercreate,"post");
            wulaiClient.processCommonRequest("/msg/bot-response",botresponse,"post");

        }
    }

}
//class TestCreateUser extends Thread{
//    private WulaiClient wulaiClient=WulaiClient.getInstance();
//
//    TestCreateUser() throws ClientException {
//    }
//
//    public void run() {
//        UserCreate userCreate= null;
//        try {
//            userCreate = new UserCreate("zhangtao@test");
//        } catch (ClientException e) {
//            e.printStackTrace();
//        }
//        try {
//            while (true) {
//                assert userCreate != null;
//                wulaiClient.userCreate(userCreate);
//            }
//        } catch (ServerException e) {
//            e.printStackTrace();
//        }
//    }
//}
//
//class TestBotResponse  extends Thread{
//    private WulaiClient wulaiClient=WulaiClient.getInstance();
//
//    TestBotResponse() throws ClientException {
//    }
//
//    public void run() {
//        MsgBody msgBody=new MsgBody("你是谁");
//        BotResponse botResponse = null;
//        try {
//            botResponse = new BotResponse("zhangtao@test",msgBody);
//        } catch (ClientException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            while (true) {
//                assert botResponse != null;
//                wulaiClient.botResponse(botResponse);
//            }
//        } catch (ServerException e) {
//            e.printStackTrace();
//        }
//    }
//}



