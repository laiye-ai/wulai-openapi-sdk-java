package com.wulai.msg;

import com.DefaultClient;
import com.alibaba.fastjson.annotation.JSONField;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.request.msg.MsgBody;
import com.module.response.msg.Bot;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;
import java.util.Map;

public class SyncMsg {
    private String userID;
    private String extra;
    private Bot bot;
    private String msgTs;
    private MsgBody msgBody;
    private long answerID;

    @JSONField(name = "user_id")
    public String getUserID() {
        return userID;
    }

    @JSONField(name = "user_id")
    public void setUserID(String value) {
        this.userID = value;
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
    public long getAnswerID() {
        return answerID;
    }

    @JSONField(name = "answer_id")
    public void setAnswerID(long value) {
        this.answerID = value;
    }

    public String request(DefaultClient defaultClient) throws ServerException, ClientException {
        HashMap<String, Object> params = new HashMap<>();
        Map map = null;

        params.put("user_id", userID);
        params.put("msg_body", msgBody);
        params.put("extra", extra);
        params.put("msg_ts", msgTs);

        CloseableHttpResponse httpResponse = defaultClient.excuteRequest("/msg/sync", params);
        return defaultClient.getJsonFromResponse(httpResponse).get("msg_id").toString();

    }

}
