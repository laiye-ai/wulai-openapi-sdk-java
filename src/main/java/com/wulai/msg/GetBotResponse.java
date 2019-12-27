package com.wulai.msg;

import com.DefaultClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.request.msg.MsgBody;
import com.module.response.msg.BotResponse;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;


public class GetBotResponse {
    private String userId;
    private String extra;
    private MsgBody msgBody;

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public void setMsgBody(MsgBody msgBody) {
        this.msgBody = msgBody;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getExtra() {
        return extra;
    }

    public MsgBody getMsgBody() {
        return msgBody;
    }

    public String getUserId() {
        return userId;
    }


    public BotResponse request(DefaultClient defaultClient) throws ServerException, ClientException {
        HashMap<String, Object> params = new HashMap<>();

        params.put("msg_body", msgBody);
        params.put("user_id", userId);
        params.put("extra", extra);

        CloseableHttpResponse httpResponse = defaultClient.excuteRequest("/msg/bot-response", params);

        return defaultClient.getResponse(httpResponse, BotResponse.class);


    }


}
