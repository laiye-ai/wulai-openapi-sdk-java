package com.wulai.msg;

import com.DefaultClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.request.MsgBody;
import com.module.response.msg.SyncResponse;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;
import java.util.Map;

public class MsgSync {
    private String userId;
    private MsgBody msgBody;
    private String extra;
    private Long msgTimestamp;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setMsgBody(MsgBody msgBody) {
        this.msgBody = msgBody;
    }

    public void setMsgTimestamp(Long msgTimestamp) {
        this.msgTimestamp = msgTimestamp;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public SyncResponse request(DefaultClient defaultClient) throws ServerException, ClientException {
        HashMap<String,Object> params= new HashMap<>();
        Map map = null;

        params.put("user_id", userId);
        params.put("msg_body", msgBody);
        params.put("extra", extra);
        params.put("msg_ts", msgTimestamp);

        CloseableHttpResponse httpResponse = defaultClient.excuteRequest("/msg/sync", params);
        map = defaultClient.getEntityMapFromResponse(httpResponse);

        return new SyncResponse(map);
    }

}
