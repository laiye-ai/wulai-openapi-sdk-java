package com.module.response.msg;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

public class ReceiveResponse implements Serializable {

    private String msgId;

    @JSONField(name = "msg_id")
    public String getMsgId() {
        return msgId;
    }

    @JSONField(name = "msg_id")
    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }
}
