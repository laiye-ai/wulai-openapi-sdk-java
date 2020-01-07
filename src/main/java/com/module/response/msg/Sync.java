package com.module.response.msg;

import com.alibaba.fastjson.annotation.JSONField;


public class Sync {

    private String msgId;

    @JSONField(name = "msg_id")
    public String getMsgId() {
        return msgId;
    }

    @JSONField(name = "msg_id")
    private void setMsgId(String msgId) {
        this.msgId = msgId;
    }


}
