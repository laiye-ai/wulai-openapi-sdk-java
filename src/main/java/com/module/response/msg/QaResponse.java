package com.module.response.msg;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.List;

public class QaResponse implements Serializable {

    private boolean isDispatch;
    private String msgId;
    private String extra;
    private List<SuggestedResponse> suggestedResponse;

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getExtra() {
        return extra;
    }

    @JSONField(name = "qa_suggested_response")
    public void setSuggestedResponse(List<SuggestedResponse> suggestedResponse) {
        this.suggestedResponse = suggestedResponse;
    }

    @JSONField(name = "is_dispatch")
    public void setDispatch(boolean dispatch) {
        isDispatch = dispatch;
    }

    @JSONField(name = "is_dispatch")
    public boolean isDispatch() {
        return isDispatch;
    }

    @JSONField(name = "msg_id")
    public String getMsgId() {
        return msgId;
    }

    @JSONField(name = "msg_id")
    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    @JSONField(name = "qa_suggested_response")
    public List<SuggestedResponse> getSuggestedResponse() {
        return suggestedResponse;
    }
}
