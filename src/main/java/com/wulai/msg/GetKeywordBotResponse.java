package com.wulai.msg;

import com.WulaiClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.request.msg.MsgBody;
import com.module.response.msg.KeywordResponse;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;

public class GetKeywordBotResponse {
    private String userId;
    private MsgBody msgBody;
    private String extra;

    public void setMsgBody(MsgBody msgBody) {
        this.msgBody = msgBody;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public KeywordResponse request(WulaiClient wulaiClient) throws ServerException, ClientException {

        HashMap<String, Object> params = new HashMap<>();

        params.put("user_id", userId);
        params.put("msg_body", msgBody);
        params.put("extra", extra);

        CloseableHttpResponse httpResponse = wulaiClient.excuteRequest("/msg/bot-response/keyword", params);
        return wulaiClient.getResponse(httpResponse, KeywordResponse.class);
    }
}
