package com.module.response.msg;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.List;

public class MsgHistory implements Serializable {
    private static final long serialVersionUID = -3796862097397377992L;
    private List<Msg> msg;
    private boolean hasMore;

    @JSONField(name = "msg")
    public List<Msg> getMsg() {
        return msg;
    }

    @JSONField(name = "msg")
    public void setMsg(List<Msg> value) {
        this.msg = value;
    }

    @JSONField(name = "has_more")
    public boolean getHasMore() {
        return hasMore;
    }

    @JSONField(name = "has_more")
    public void setHasMore(boolean value) {
        this.hasMore = value;
    }
}
