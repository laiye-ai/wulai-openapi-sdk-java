package com.module.response.msg;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;


public class QAResponse{

    private boolean isDispatch;
    private String msgId;
    private String extra;
    private List<QASuggestedResponse> qaSuggestedResponses;

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getExtra() {
        return extra;
    }

    @JSONField(name = "qa_suggested_response")
    public void setQASuggestedResponse(List<QASuggestedResponse> qaSuggestedResponses) {
        this.qaSuggestedResponses = qaSuggestedResponses;
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
    public List<QASuggestedResponse> getQASuggestedResponse() {
        return qaSuggestedResponses;
    }
}
