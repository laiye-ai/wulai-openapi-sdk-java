package com.wulai.msg;

import com.DefaultClient;
import com.alibaba.fastjson.JSONObject;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.request.msg.MsgBody;
import com.module.response.msg.TaskResponse;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;
import java.util.Map;

public class GetTaskBotResponse {
    private String userId;
    private MsgBody msgBody;
    private String extra;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setMsgBody(MsgBody msgBody) {
        this.msgBody = msgBody;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public TaskResponse request(DefaultClient defaultClient) throws ServerException, ClientException {
        HashMap<String, Object> params = new HashMap<>();
        Map map = null;

        params.put("user_id", userId);
        params.put("msg_body", msgBody);
        params.put("extra", extra);

        CloseableHttpResponse httpResponse = defaultClient.excuteRequest("/msg/bot-dictionary/task", params);
        JSONObject jsonObject = defaultClient.getJsonFromResponse(httpResponse);

        return JSONObject.parseObject(jsonObject.toString(), TaskResponse.class);
    }
}
