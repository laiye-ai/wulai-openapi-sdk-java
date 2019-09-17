import exceptions.ClientException;
import exceptions.ServerException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import request.msg.*;
import request.user.*;
import response.msg.*;
import response.user.UserAttributeListResponse;

import java.net.URI;

public class WulaiClientTest {
    private static WulaiClient wulaiClient;

    @Before
    public void setEnv() throws ClientException {
        wulaiClient = new WulaiClient(System.getenv("pubkey"),
                System.getenv("secret"), "v2");
        wulaiClient.setRetryTimes(1);
        wulaiClient.setTimeout(10);

        wulaiClient.setEndpoint(URI.create("https://preopenapi.wul.ai/"));

    }

    @Test
    public void testProcessCommonRequest() throws ServerException, ClientException {
        String data = "{\"user_id\":\"zhangtao@test\"}";
        String body = wulaiClient.processCommonRequest("/user/create", data);
        Assert.assertNotNull(body);
    }

    @Test
    public void TestUserCreate() throws ClientException, ServerException {
        UserCreateRequest userCreateRequest = new UserCreateRequest("laiye@test");
        userCreateRequest.setNickname("JavaSDK");
        userCreateRequest.setAvatarUrl("https://www.laiye.com/static/official-website/logo.png");
        int result = wulaiClient.userCreate(userCreateRequest);
        Assert.assertEquals(result,200);
    }

    @Test
    public void TestBotResponse() throws ClientException, ServerException {
        Text text = new Text("你是谁");
        MsgBody msgBody = new MsgBody(text);
        BotResponseRequest botResponseRequest = new BotResponseRequest("laiye@test", msgBody);
        botResponseRequest.setExtra("readme");
        BotResponse botResponse = wulaiClient.getBotResponse(botResponseRequest);
        Assert.assertNotNull(botResponse.getMsgId());
        Assert.assertNotNull(botResponse.getSuggestedResponse());
    }

    @Test
    public void TestKeywordBotResponse() throws ClientException, ServerException {
        Text text = new Text("你好");
        MsgBody msgBody = new MsgBody(text);
        BotResponseRequest botResponseRequest = new BotResponseRequest("laiye@test", msgBody);
        botResponseRequest.setExtra("hello");
        KeywordResponse keywordResponse = wulaiClient.getKeywordBotResponse(botResponseRequest);
        Assert.assertNotNull(keywordResponse.getMsgId());
        Assert.assertNotNull(keywordResponse.getKeywordSuggestedResponse());
    }

    @Test
    public void TestQAResponse() throws ClientException, ServerException {
        Text text = new Text("你好");
        MsgBody msgBody = new MsgBody(text);
        BotResponseRequest botResponse = new BotResponseRequest("laiye@test", msgBody);
        botResponse.setExtra("hello");
        QaResponse qaResponse = wulaiClient.getQABotResponse(botResponse);
        Assert.assertNotNull(qaResponse);
    }

    @Test
    public void TestTaskBotResponse() throws ClientException, ServerException {
        Text text = new Text("年假");
        MsgBody msgBody = new MsgBody(text);
        BotResponseRequest botResponse = new BotResponseRequest("laiye@test", msgBody);
        TaskResponse taskResponse = wulaiClient.getTaskBotResponse(botResponse);
        Assert.assertNotNull(taskResponse);
    }

    @Test
    public void TestHistory() throws ClientException, ServerException {
        HistoryRequest historyRequest = new HistoryRequest("laiye@test", 3);
        historyRequest.setDirection(HistoryRequest.Direction.BACKWARD);
        //historyRequest.setMsgId(msgId);
        HistoryResponse historyResponse = wulaiClient.msgHistory(historyRequest);
        Assert.assertNotNull(historyResponse);
    }

    @Test
    public void TestSync() throws ClientException, ServerException {
        Text text = new Text("好好好");
        MsgBody msgBody = new MsgBody(text);
        SyncRequest syncRequest = new SyncRequest("laiye@test", msgBody, (String.valueOf(System.currentTimeMillis())));
        syncRequest.setExtra("hello");
        SyncResponse syncResponse = wulaiClient.msgSync(syncRequest);
        Assert.assertNotNull(syncResponse);
    }

    @Test
    public void TestMsgReceive() throws ClientException, ServerException {
        Text text = new Text("你好");
        MsgBody msgBody = new MsgBody(text);
        ReceiveRequest receiveRequest = new ReceiveRequest("laiye@test", msgBody);
        //receiveRequest.setThirdMsgId("123231");
        ReceiveResponse receiveResponse = wulaiClient.msgReceive(receiveRequest);
        Assert.assertNotNull(receiveResponse);
    }

    @Test
    public void TestAttributeCreate() throws ClientException, ServerException {
        UserAttribute user_attribute = new UserAttribute("101481");

        UserAttributeValue user_attribute_value = new UserAttributeValue("德鲁伊");

        UserAttributeUserAttributeValue userAttributeUserAttributeValue = new UserAttributeUserAttributeValue();
        userAttributeUserAttributeValue.setUser_attribute(user_attribute);
        userAttributeUserAttributeValue.setUser_attribute_value(user_attribute_value);

        UserAttributeCreateRequest userAttributeCreateRequest = new UserAttributeCreateRequest("laiye@test");
        userAttributeCreateRequest.addUserAttributeUserAttributeValue(userAttributeUserAttributeValue);

        int httpCode = wulaiClient.userAttributeCreate(userAttributeCreateRequest);
        Assert.assertEquals(httpCode,200);
    }

    @Test
    public void TestAttributeList() throws ClientException, ServerException {
        UserAttributeListRequest userAttributeListRequest = new UserAttributeListRequest(1, 5);
        userAttributeListRequest.setFilter(false);
        UserAttributeListResponse userAttributeListResponse = wulaiClient.userAttributeList(userAttributeListRequest);
        Assert.assertNotNull(userAttributeListResponse);
    }

    @Test
    public void TestPreOpenApi() throws ClientException, ServerException {
        wulaiClient.setEndpoint(URI.create("https://preopenapi.wul.ai/"));
        wulaiClient.setTimeout(1);
        UserCreateRequest userCreateRequest = new UserCreateRequest("zhangtao@test");
        int httpcode = wulaiClient.userCreate(userCreateRequest);
        Assert.assertEquals(httpcode,200);
    }
}




