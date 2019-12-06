package com.module.response.msg;

import com.alibaba.fastjson.JSONArray;

import java.io.Serializable;
import java.util.Map;

public class HistoryResponse implements Serializable {
    private static final long serialVersionUID = -3796862097397377992L;
    private Object[] msg;
    private boolean hasMore;

    public HistoryResponse(Map map) {
        setHasMore((boolean) map.get("has_more"));
        setMsg(JSONArray.parseArray(map.get("msg").toString()).toArray());
    }

    public Object[] getMsg() {
        return msg;
    }

    public void setMsg(Object[] msg) {
        this.msg = msg;
    }

    public boolean isHasMore() {
        return hasMore;
    }

    private void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }
}
