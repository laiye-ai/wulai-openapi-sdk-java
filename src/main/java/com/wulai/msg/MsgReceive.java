package com.wulai.msg;

import com.DefaultClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.request.MsgBody;
import com.module.response.msg.ReceiveResponse;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;
import java.util.Map;

public class MsgReceive {
    private String userId;
    private MsgBody msgBody;
    private String extra;
    private String thirdMsgId;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public void setMsgBody(MsgBody msgBody) {
        this.msgBody = msgBody;
    }

    public void setThirdMsgId(String thirdMsgId) {
        this.thirdMsgId = thirdMsgId;
    }

    public ReceiveResponse request(DefaultClient defaultClient) throws ServerException, ClientException {
        HashMap<String,Object> params= new HashMap<>();
        Map map = null;

        params.put("user_id", userId);
        params.put("msg_body", msgBody);
        params.put("extra", extra);
        params.put("third_msg_id", thirdMsgId);

        CloseableHttpResponse httpResponse = defaultClient.excuteRequest("/msg/receive", params);
        map = defaultClient.getEntityMapFromResponse(httpResponse);

        return new ReceiveResponse(map);

    }
}
