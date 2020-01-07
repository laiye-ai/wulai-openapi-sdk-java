package com.module.response.msg;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class BotResponse {
    private boolean isDispatch;
    private String msgId;
    private String extra;
    private List<SuggestedResponse> suggestedResponse;

    @JSONField(name = "is_dispatch")
    public void setDispatch(boolean dispatch) {
        isDispatch = dispatch;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    @JSONField(name = "msg_id")
    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    @JSONField(name = "suggested_response")
    public void setSuggestedResponse(List<SuggestedResponse> suggestedResponse) {
        this.suggestedResponse = suggestedResponse;
    }

    public String getExtra() {
        return extra;
    }

    @JSONField(name = "msg_id")
    public String getMsgId() {
        return msgId;
    }

    @JSONField(name = "suggested_response")
    public List<SuggestedResponse> getSuggestedResponse() {
        return suggestedResponse;
    }

    @JSONField(name = "is_dispatch")
    public boolean isDispatch() {
        return isDispatch;
    }
}
