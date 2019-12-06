package com.module.response.msg;

import java.io.Serializable;
import java.util.Map;

public class ReceiveResponse implements Serializable {
    private static final long serialVersionUID = -7873376951237664553L;

    private String msg_id;

    public ReceiveResponse(Map map) {
        setMsg_id(map.get("msg_id").toString());
    }

    public String getMsg_id() {
        return msg_id;
    }

    private void setMsg_id(String msg_id) {
        this.msg_id = msg_id;
    }
}
