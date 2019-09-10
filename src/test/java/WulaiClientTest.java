import com.alibaba.fastjson.JSONObject;
import exceptions.ClientException;
import exceptions.ServerException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.junit.Before;
import org.junit.Test;
import request.msg.*;
import request.user.*;
import response.msg.*;
import response.user.UserAttributeListResponse;

import java.util.*;

public class WulaiClientTest {
    private static WulaiClient wulaiClient;

    @Before
    public void setEnv() throws ClientException {
        wulaiClient = new WulaiClient(System.getenv("pubkey"),
                System.getenv("secret"), "v2", true);
        wulaiClient.setRetryTimes(1);
        System.out.println("setEnv");
    }

    @Test
    public void test() throws ClientException, ServerException {
        String userId="zhangtao@test";
        TestBotResponse(userId,"你是谁" );
        TestUserCreate("taskbot","","");
        TestKeywordBotResponse(userId,"hello");
        TestQAResponse(userId,"你是谁");
        TestTaskBotResponse("taskbot","年假");
        TestTaskBotResponse("taskbot","122");
        TestHistory("taskbot",3,"", HistoryRequest.Direction.BACKWARD);
        TestHistory(userId,3,"",null);
        TestAttributeList(1,5,false);
        TestHistory(userId,3,null, null);
        TestSync(userId,"好好好","1567583854600");
        TestMsgReceive(userId,"你好","");
        TestAttributeCreate("101481","德鲁伊");
    }

    private void TestUserCreate(String userId, String nickName, String avatarUrl) throws ClientException, ServerException {
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

    private void TestBotResponse(String userId, String msg) throws ClientException, ServerException {
        Text text=new Text(msg);
        MsgBody msgBody=new MsgBody(text);
        BotResponseRequest botResponseRequest = new BotResponseRequest(userId, msgBody);
        botResponseRequest.setExtra("readme");
        BotResponse botResponse = wulaiClient.getBotResponse(botResponseRequest);
        Object jsonObject=JSONObject.toJSON(botResponse);
        System.out.println(jsonObject.toString());
    }

    private void TestKeywordBotResponse(String userId, String msg) throws ClientException, ServerException {
        Text text=new Text(msg);
        MsgBody msgBody=new MsgBody(text);
        BotResponseRequest botResponseRequest = new BotResponseRequest(userId, msgBody);
        botResponseRequest.setExtra("hello");
        KeywordResponse keywordResponse = wulaiClient.getKeywordBotResponse(botResponseRequest);
        Object jsonObject=JSONObject.toJSON(keywordResponse);
        System.out.println(jsonObject.toString());

    }

    private void TestQAResponse(String userId, String msg) throws ClientException, ServerException {
        Text text=new Text(msg);
        MsgBody msgBody=new MsgBody(text);
        BotResponseRequest botResponse = new BotResponseRequest(userId, msgBody);
        botResponse.setExtra("hello");
        QaResponse qaResponse = wulaiClient.getQABotResponse(botResponse);
        Object jsonObject=JSONObject.toJSON(qaResponse);
        System.out.println(jsonObject.toString());
    }

    private void TestTaskBotResponse(String userId, String msg) throws ClientException, ServerException {
        Text text=new Text(msg);
        MsgBody msgBody=new MsgBody(text);
        BotResponseRequest botResponse = new BotResponseRequest(userId, msgBody);
        TaskResponse taskResponse = wulaiClient.getTaskBotResponse(botResponse);
        Object jsonObject=JSONObject.toJSON(taskResponse);
        System.out.println(jsonObject.toString());
    }

    private void TestHistory(String userId, int num, String msgId, HistoryRequest.Direction direction) throws ClientException, ServerException {
        HistoryRequest historyRequest = new HistoryRequest(userId, num);
        historyRequest.setDirection(direction);
        historyRequest.setMsgId(msgId);
        HistoryResponse historyResponse = wulaiClient.msgHistory(historyRequest);
        Object jsonObject=JSONObject.toJSON(historyResponse);
        System.out.println(jsonObject.toString());
    }
    private void TestSync(String userId, String msg, String msgTs) throws ClientException, ServerException {
        Text text=new Text(msg);
        MsgBody msgBody=new MsgBody(text);
        SyncRequest syncRequest=new SyncRequest(userId,msgBody,msgTs);
        syncRequest.setExtra("hello");
        SyncResponse syncResponse=wulaiClient.msgSync(syncRequest);
        Object jsonObject=JSONObject.toJSON(syncResponse);
        System.out.println(jsonObject.toString());
    }
    private void TestMsgReceive(String userId, String msg, String thirdMsgId) throws ClientException, ServerException {
        Text text=new Text(msg);
        MsgBody msgBody=new MsgBody(text);
        ReceiveRequest receiveRequest=new ReceiveRequest(userId,msgBody);
        receiveRequest.setThirdMsgId(thirdMsgId);
        ReceiveResponse receiveResponse=wulaiClient.msgReceive(receiveRequest);
        Object jsonObject=JSONObject.toJSON(receiveResponse);
        System.out.println(jsonObject.toString());
    }

    public void TestAttributeCreate(String id,String name) throws ClientException, ServerException {
        UserAttribute user_attribute=new UserAttribute();
        user_attribute.setId(id);

        UserAttributeValue user_attribute_value=new UserAttributeValue();
        user_attribute_value.setName(name);

        UserAttributeUserAttributeValue userAttributeUserAttributeValue=new UserAttributeUserAttributeValue();
        userAttributeUserAttributeValue.setUser_attribute(user_attribute);
        userAttributeUserAttributeValue.setUser_attribute_value(user_attribute_value);

        UserAttributeCreateRequest userAttributeCreateRequest=new UserAttributeCreateRequest("zhangtao@test");
        userAttributeCreateRequest.addUserAttributeUserAttributeValue(userAttributeUserAttributeValue);

        wulaiClient.userAttributeCreate(userAttributeCreateRequest);
    }

    private void TestAttributeList(int page, int pageSize, boolean filter) throws ClientException, ServerException {
        UserAttributeListRequest userAttributeListRequest = new UserAttributeListRequest(page, pageSize);
        userAttributeListRequest.setFilter(filter);
        UserAttributeListResponse userAttributeListResponse = wulaiClient.userAttributeList(userAttributeListRequest);
        Object jsonObject=JSONObject.toJSON(userAttributeListResponse);
        System.out.println(jsonObject.toString());
    }


    public void testList(){
        List list =new ArrayList();

    }
}




