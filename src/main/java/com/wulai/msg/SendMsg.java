package com.wulai.msg;

import com.DefaultClient;
import com.alibaba.fastjson.annotation.JSONField;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.request.msg.MsgBody;
import com.module.response.msg.SimilarResponse;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;
import java.util.List;

public class SendMsg {
    private List<SimilarResponse> similarResponse;
    private MsgBody msgBody;
    private List<String> quickReply;
    private String userID;
    private String extra;

    @JSONField(name = "similar_response")
    public List<SimilarResponse> getSimilarResponse() {
        return similarResponse;
    }

    @JSONField(name = "similar_response")
    public void setSimilarResponse(List<SimilarResponse> value) {
        this.similarResponse = value;
    }

    @JSONField(name = "msg_body")
    public MsgBody getMsgBody() {
        return msgBody;
    }

    @JSONField(name = "msg_body")
    public void setMsgBody(MsgBody value) {
        this.msgBody = value;
    }

    @JSONField(name = "quick_reply")
    public List<String> getQuickReply() {
        return quickReply;
    }

    @JSONField(name = "quick_reply")
    public void setQuickReply(List<String> value) {
        this.quickReply = value;
    }

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

    public String request(DefaultClient defaultClient) throws ServerException, ClientException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("similar_response", similarResponse);
        params.put("user_id", userID);
        params.put("extra", extra);
        params.put("quick_reply", quickReply);
        params.put("msg_body", msgBody);


        CloseableHttpResponse httpResponse = defaultClient.excuteRequest("/msg/send", params);
        return defaultClient.getJsonFromResponse(httpResponse).get("msg_id").toString();


    }

}
