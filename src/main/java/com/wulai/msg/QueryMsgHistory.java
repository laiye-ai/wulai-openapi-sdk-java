package com.wulai.msg;

import com.DefaultClient;
import com.alibaba.fastjson.annotation.JSONField;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.response.msg.MsgHistory;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;

public class QueryMsgHistory {
    private String userId;
    private int Num;
    private Direction direction = Direction.BACKWARD;
    private String msgId;

    public enum Direction {
        FORWARD, BACKWARD
    }

    @JSONField(name = "user_id")
    public void setUserId(String userId) {
        this.userId = userId;
    }

    @JSONField(name = "user_id")
    public String getUserId() {
        return userId;
    }

    @JSONField(name = "msg_id")
    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    @JSONField(name = "msg_id")
    public String getMsgId() {
        return msgId;
    }

    public void setNum(int num) {
        Num = num;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getNum() {
        return Num;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public MsgHistory request(DefaultClient defaultClient) throws ClientException, ServerException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("user_id", userId);
        params.put("num", Num);
        params.put("direction", direction);
        params.put("msg_id", msgId);

        CloseableHttpResponse httpResponse = defaultClient.excuteRequest("/msg/history", params);
        return defaultClient.getResponse(httpResponse,MsgHistory.class);

    }

}
