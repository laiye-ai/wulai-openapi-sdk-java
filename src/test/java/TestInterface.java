import exceptions.ClientException;
import org.junit.Before;
import org.junit.Test;
import requestBean.msg.BotResponseRequest;
import requestBean.msg.HistoryRequest;
import requestBean.user.UserAttributeListRequest;
import requestBean.user.UserCreateRequest;
import responseBean.msg.*;
import responseBean.user.UserAttributeListResponse;

public class TestInterface {
    WulaiClient wulaiClient = null;

    @Before
    public void setEnv() throws ClientException {
        wulaiClient = new WulaiClient(System.getenv("pubkey"),
                System.getenv("secret"), "v2", false);
        wulaiClient.setTimeout(10000);
        wulaiClient.setRetryTimes(3);
    }

    @Test
    public void TestUserCreate() throws ClientException {
        UserCreateRequest userCreateRequest = new UserCreateRequest("zhangtao@test");
        wulaiClient.userCreate(userCreateRequest);

    }

    @Test
    public void TestBotResponse() throws ClientException{
        BotResponseRequest botResponseRequest=new BotResponseRequest("zhangtao@test","你是谁");
        BotResponse botResponse =wulaiClient.getBotResponse(botResponseRequest);
        int i=0;
        for(Object object:botResponse.getSuggestedResponse()){
           System.out.println(object.toString());
            System.out.println(i++);
        }
    }


    @Test
    public void TestKeywordBotResponse() throws ClientException {
        BotResponseRequest botResponseRequest = new BotResponseRequest("zhangtao@test", "欢迎语222");
        botResponseRequest.setExtra("hello");
        KeywordResponse keywordResponse =wulaiClient.getKeywordBotResponse(botResponseRequest);
        for (Object object:keywordResponse.getKeywordSuggestedResponse()){
            System.out.println(object);
        }

    }

    @Test
    public void TestQAResponse() throws ClientException{
        BotResponseRequest botResponse=new BotResponseRequest("zhangtao@test","欢迎语222");
        botResponse.setExtra("hello");
        QaResponse qaResponse=wulaiClient.getQABotResponse(botResponse);
        for (Object object:qaResponse.getQaSuggestedResponse()) {
            System.out.println(object);
        }
    }

    @Test
    public void TestTaskBotResponse() throws ClientException{
        BotResponseRequest botResponse=new BotResponseRequest("zhangtao@test","年假" );
        TaskResponse taskResponse=wulaiClient.getTaskBotResponse(botResponse);
        for (Object object:taskResponse.getTaskSuggestedResponse()){
            System.out.println(object);
        }
        botResponse.setMsgBody("122");
        taskResponse=wulaiClient.getTaskBotResponse(botResponse);
        for (Object object:taskResponse.getTaskSuggestedResponse()){
            System.out.println(object);
        }

    }
    @Test
    public void TestHistory() throws ClientException {
        HistoryRequest historyRequest=new HistoryRequest("zhangtao@test",3);
        historyRequest.setDirection(HistoryRequest.Direction.FORWARD);
        historyRequest.setDirection(HistoryRequest.Direction.BACKWARD);
        HistoryResponse historyResponse=wulaiClient.msgHistory(historyRequest);
        System.out.println(historyResponse.isHasMore());
        for (Object object:historyResponse.getMsg()){
            System.out.println(object);
        }
    }
//    @Test
//    public void TestAttributeCreate() throws ClientException {
//        String data="{\"user_attribute_user_attribute_value\":[{\"user_attribute\":{\"id\":\"101480\"},\"user_attribute_value\":{\"name\":\"骑士\"}}],\"user_id\":\"zhangtao@test\"}";
//
//        UserAttributeCreateRequest userAttributeCreateRequest=new UserAttributeCreateRequest("zhangtao@test");
//        userAttributeCreateRequest.setUser_id("zhangtao@test");
//        wulaiClient.userCreate(userAttributeCreateRequest);
//    }

    @Test
    public void TestAttributeList() throws ClientException {
        UserAttributeListRequest userAttributeListRequest=new UserAttributeListRequest(3,10);
        UserAttributeListResponse userAttributeListResponse=wulaiClient.userAttributeList(userAttributeListRequest);
        System.out.println(userAttributeListResponse.getPageCount());
        for (Object object:userAttributeListResponse.getUserAttributeUserAttributeValues()){
            System.out.println(object);
        }
    }

}
