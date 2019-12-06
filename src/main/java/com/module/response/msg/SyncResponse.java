package com.module.response.msg;

import java.io.Serializable;
import java.util.Map;

public class SyncResponse implements Serializable {
    private static final long serialVersionUID = -3926486440053993536L;
    private String msgId;

    public SyncResponse(Map map) {
        setMsgId(map.get("msg_id").toString());
    }

    public String getMsgId() {
        return msgId;
    }

    private void setMsgId(String msgId) {
        this.msgId = msgId;
    }
}