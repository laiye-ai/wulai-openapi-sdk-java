import exceptions.ClientException;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.junit.Before;
import org.junit.Test;
import request.msg.BotResponseRequest;
import request.msg.HistoryRequest;
import request.msg.SyncRequest;
import request.user.UserAttributeListRequest;
import request.user.UserCreateRequest;
import response.msg.*;
import response.user.UserAttributeListResponse;

import java.net.URI;

public class WulaiClientTest {
    private static WulaiClient wulaiClient;

    @Before
    public void setEnv() throws ClientException {
        wulaiClient = new WulaiClient(System.getenv("pubkey"),
                System.getenv("secret"), "v2", false);
        wulaiClient.setRetryTimes(1);
        System.out.println("setEnv");
    }

    @Test
    public void test() throws ClientException {
        try {
            TestUserCreate("","tom","");
        }catch (ClientException e){
            System.out.println("生吞");
        }
        TestBotResponse("zhangtao@test","你是谁" );
        TestUserCreate("taskbot","","");
        TestBotResponse("taskbot","年假");
        TestBotResponse("taskbot","122");
        TestHistory("taskbot",3,"", HistoryRequest.Direction.BACKWARD);
        TestAttributeList(1,5,true);
        TestHistory("zhangtao@test",3,null, null);
        TestSync("zhangtao@test","好好好","1567583854600");
    }

    public void TestUserCreate(String userId,String nickName,String avatarUrl) throws ClientException {
        UserCreateRequest userCreateRequest = new UserCreateRequest(userId);
        userCreateRequest.setNickname(nickName);
        userCreateRequest.setAvatarUrl(avatarUrl);
        int result = wulaiClient.userCreate(userCreateRequest);
        if (result==200){
            System.out.println("创建用户成功");
        }else {
            System.out.println("创建用户失败");
        }
     }

    public void TestBotResponse(String userId, String msg) throws ClientException {
        BotResponseRequest botResponseRequest = new BotResponseRequest(userId, msg);
        BotResponse botResponse = wulaiClient.getBotResponse(botResponseRequest);
        for (Object object : botResponse.getSuggestedResponse()) {
            System.out.println(object.toString());
        }
    }

    public void TestKeywordBotResponse(String userId, String msg) throws ClientException {
        BotResponseRequest botResponseRequest = new BotResponseRequest(userId, msg);
        botResponseRequest.setExtra("hello");
        KeywordResponse keywordResponse = wulaiClient.getKeywordBotResponse(botResponseRequest);
        for (Object object : keywordResponse.getKeywordSuggestedResponse()) {
            System.out.println(object);
        }

    }

    public void TestQAResponse(String userId, String msg) throws ClientException {
        BotResponseRequest botResponse = new BotResponseRequest(userId, msg);
        botResponse.setExtra("hello");
        QaResponse qaResponse = wulaiClient.getQABotResponse(botResponse);
        for (Object object : qaResponse.getQaSuggestedResponse()) {
            System.out.println(object);
        }
    }

    public void TestTaskBotResponse(String userId, String msg) throws ClientException {
        BotResponseRequest botResponse = new BotResponseRequest(userId, msg);
        TaskResponse taskResponse = wulaiClient.getTaskBotResponse(botResponse);
        for (Object object : taskResponse.getTaskSuggestedResponse()) {
            System.out.println(object);
        }

    }

    public void TestHistory(String userId,int num,String msgId ,HistoryRequest.Direction direction ) throws ClientException {
        HistoryRequest historyRequest = new HistoryRequest(userId, num);
        historyRequest.setDirection(HistoryRequest.Direction.FORWARD);
        historyRequest.setDirection(HistoryRequest.Direction.BACKWARD);
        historyRequest.setMsgId(msgId);
        HistoryResponse historyResponse = wulaiClient.msgHistory(historyRequest);
        System.out.println(historyResponse.isHasMore());
        for (Object object : historyResponse.getMsg()) {
            System.out.println(object);
        }
    }
    public void TestSync(String userId,String msgBody,String msgTs) throws ClientException {
        SyncRequest syncRequest=new SyncRequest(userId,msgBody,msgTs);
        syncRequest.setExtra("hello");
        SyncResponse syncResponse=wulaiClient.msgSync(syncRequest);
        System.out.println(syncResponse.getMsgId());
    }

    public void TestAttributeCreate() throws ClientException {
        String data = "{\"user_attribute_user_attribute_value\":[{\"user_attribute\":{\"id\":\"101480\"},\"user_attribute_value\":{\"name\":\"骑士\"}}],\"user_id\":\"zhangtao@test\"}";

        //UserAttributeCreateRequest userAttributeCreateRequest=new UserAttributeCreateRequest("zhangtao@test");
        //userAttributeCreateRequest.setUser_id("zhangtao@test");
        //wulaiClient.userCreate(userAttributeCreateRequest);
    }

    public void TestAttributeList(int page,int pageSize,boolean filter) throws ClientException {
        UserAttributeListRequest userAttributeListRequest = new UserAttributeListRequest(page, pageSize);
        userAttributeListRequest.setFilter(filter);
        UserAttributeListResponse userAttributeListResponse = wulaiClient.userAttributeList(userAttributeListRequest);
        for (Object object : userAttributeListResponse.getUserAttributeUserAttributeValues()) {
            System.out.println(object);
        }
    }

//    @Test
//    public void testNormal() throws ClientException {
//        wulaiClient.processCommonRequest("/user/create", usercreate);
//        wulaiClient.processCommonRequest("/msg/bot-response", botresponse);
//    }
//
//
//    @Test
//    public void testErrorEnv() {
//        try {
//            wulaiClient = new WulaiClient(System.getenv("LOGNAME"), System.getenv("LOGNAME"),
//                    "v2", false);
//            wulaiClient.processCommonRequest("/user/create", usercreate);
//        } catch (ClientException e) {
//            System.out.println("test捕获异常:" + e.getMessage());
//        }
//    }
//
//    @Test
//    public void testSetPool() {
//        try {
//            PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
//            cm.setDefaultMaxPerRoute(20);
//            cm.setMaxTotal(20);
//            wulaiClient.setPools(cm);
//            wulaiClient.processCommonRequest("/user/create", usercreate);
//            wulaiClient.processCommonRequest("/msg/bot-response", botresponse);
//        } catch (ClientException e) {
//            System.out.println("testProcessCommonRequest捕获异常" + e.getMessage());
//        }
//    }
//
//
//    @Test
//    public void test404() {
//        try {
//            wulaiClient.processCommonRequest("/user/createaaaaa", usercreate);
//        } catch (ClientException e) {
//            System.out.println("test404捕获异常" + e.getMessage());
//        }
//    }
//
//    @Test
//    public void testApiVersion() {
//        try {
//            wulaiClient = new WulaiClient(System.getenv("pubkey"),
//                    System.getenv("secret"), "v1", false);
//        } catch (ClientException e) {
//            System.out.println("testApiVersion捕获异常:" + e.getMessage());
//        }
//        try {
//            wulaiClient.processCommonRequest("/user/create", usercreate);
//        } catch (ClientException e) {
//            System.out.println("testApiVersion捕获异常:" + e.getMessage());
//        }
//    }
//
//    @Test
//    public void testErrorSecret() {
//        try {
//            wulaiClient = new WulaiClient(System.getenv("pubkey"),
//                    System.getenv("secret").substring(1, 4), "v2", false);
//            wulaiClient.processCommonRequest("/msg/bot-response", botresponse);
//        } catch (ClientException e) {
//            System.out.println("testErrorSecret捕获异常:" + e.getMessage());
//        }
//    }
//
//    @Test
//    public void testUnreacheableUrl() {
//        wulaiClient.setEndpoint(URI.create("https://www.google.com/"));
//        try {
//            wulaiClient.processCommonRequest("/msg/bot-response", botresponse);
//        } catch (ClientException e) {
//            System.out.println("testUnreacheableUrl捕获异常:" + e.getMessage());
//        }
//    }
//
//    @Test
//    public void testPubkeyisNull() {
//        WulaiClient wulaiClient2 = null;
//        try {
//
//            wulaiClient2 = new WulaiClient(System.getenv(""), System.getenv(""),
//                    "v2", false);
//        } catch (ClientException e) {
//            System.out.println("testPubkeyisNull捕获异常:" + e.getMessage());
//        }
//        try {
//            wulaiClient2.processCommonRequest("/user/create", usercreate);
//            wulaiClient2.processCommonRequest("/msg/bot-response", botresponse);
//        } catch (ClientException e) {
//            System.out.println("testPubkeyisNull捕获异常:" + e.getMessage());
//        } catch (NullPointerException e) {
//            System.out.println("testPubkeyisNull捕获异常:" + e.getMessage());
//        }
//    }

}




