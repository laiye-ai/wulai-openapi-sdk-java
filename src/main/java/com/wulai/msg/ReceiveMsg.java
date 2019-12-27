package com.wulai.msg;

import com.DefaultClient;
import com.alibaba.fastjson.annotation.JSONField;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.request.msg.MsgBody;
import com.module.response.msg.Bot;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;

public class ReceiveMsg {
    private String userId;
    private String extra;
    private Bot bot;
    private String msgTs;
    private MsgBody msgBody;
    private int answerID;

    @JSONField(name = "user_id")
    public String getUserId() {
        return userId;
    }

    @JSONField(name = "user_id")
    public void setUserId(String value) {
        this.userId = value;
    }

    @JSONField(name = "extra")
    public String getExtra() {
        return extra;
    }

    @JSONField(name = "extra")
    public void setExtra(String value) {
        this.extra = value;
    }

    @JSONField(name = "bot")
    public Bot getBot() {
        return bot;
    }

    @JSONField(name = "bot")
    public void setBot(Bot value) {
        this.bot = value;
    }

    @JSONField(name = "msg_ts")
    public String getMsgTs() {
        return msgTs;
    }

    @JSONField(name = "msg_ts")
    public void setMsgTs(String value) {
        this.msgTs = value;
    }

    @JSONField(name = "msg_body")
    public MsgBody getMsgBody() {
        return msgBody;
    }

    @JSONField(name = "msg_body")
    public void setMsgBody(MsgBody value) {
        this.msgBody = value;
    }

    @JSONField(name = "answer_id")
    public int getAnswerID() {
        return answerID;
    }

    @JSONField(name = "answer_id")
    public void setAnswerID(int value) {
        this.answerID = value;
    }


    public String request(DefaultClient defaultClient) throws ServerException, ClientException {
        HashMap<String, Object> params = new HashMap<>();

        params.put("user_id", userId);
        params.put("msg_body", msgBody);
        params.put("extra", extra);
        params.put("msg_ts", msgTs);
        params.put("bot", bot);
        params.put("answer_id", answerID);

        CloseableHttpResponse httpResponse = defaultClient.excuteRequest("/msg/receive", params);
        return defaultClient.getResponse(httpResponse,String.class,"msg_id");

    }
}
