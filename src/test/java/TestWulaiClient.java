
import exceptions.ClientException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestWulaiClient {
    private static WulaiClient wulaiClient;
    private static String name;

    private static String usercreate;
    private static String botresponse;


    @Before
    public void setEnv() throws ClientException {
        wulaiClient=new WulaiClient(System.getenv("pubkey"),
                System.getenv("secret"), "v2", true);
        name="zhangtao@test";
        usercreate=String.format("{\"user_id\":\"%s\"}", name);
        botresponse = String.format("{\"user_id\":\"%s\",\"msg_body\":{\"text\":{\"content\":\"%s\"}},\"extra\":\"%s\"}", name, "你是谁", "");

    }

    @Test
    public void testProcessCommonRequest(){
        try {
            wulaiClient.processCommonRequest("/user/create", usercreate, "post");
            wulaiClient.processCommonRequest("/msg/bot-response",botresponse, "post");
            wulaiClient.processCommonRequest("/msg/bot-response",botresponse, "post");

        } catch (ClientException e) {
            System.out.println("捕获异常");
        }
    }

    @After
    public void testConstruct(){
        try {
            new WulaiClient(System.getenv("pubkey"),
                    System.getenv("secret"), "v1", true);
        } catch (ClientException e) {
            System.out.println("捕获异常");
        }
        try {
            wulaiClient=new WulaiClient(System.getenv("pubkey"),
                    System.getenv("secret").substring(1,4), "v2", true);
        } catch (ClientException e) {
            System.out.println("捕获异常");
        }
        try {
            wulaiClient.processCommonRequest("/user/create",usercreate,"post");
        } catch (ClientException e) {
            System.out.println("捕获异常");
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



