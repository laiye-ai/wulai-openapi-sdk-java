package com.wulai.msg;

import com.DefaultClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.request.msg.HistoryRequest;
import com.module.response.msg.HistoryResponse;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;
import java.util.Map;

public class MsgHistory {
    private String userId;
    private int Num;
    private Direction direction=Direction.BACKWARD;
    private String msgId;
    public enum Direction {
        FORWARD, BACKWARD
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public void setNum(int num) {
        Num = num;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public HistoryResponse request(DefaultClient defaultClient) throws ClientException, ServerException {
        HashMap<String,Object> params= new HashMap<>();
        Map map = null;
        params.put("user_id", userId);
        params.put("num", Num);
        params.put("direction", direction);
        params.put("msg_id", msgId);

        CloseableHttpResponse httpResponse = defaultClient.excuteRequest("/msg/history", params);
        map = defaultClient.getEntityMapFromResponse(httpResponse);
        return new HistoryResponse(map);
    }

}
