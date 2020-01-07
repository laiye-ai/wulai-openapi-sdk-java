package com.module.response.msg;

import com.alibaba.fastjson.annotation.JSONField;
import com.module.request.msg.MsgBody;

import java.util.List;

public class Response {
    private String extra;
    private int delayTs;
    private MsgBody msgBody;
    private List<SimilarResponse> similarResponses;
    private boolean enableEvaluate;
    private int answerId;

    public void setExtra(String extra) {
        this.extra = extra;
    }

    @JSONField(name = "answer_id")
    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    @JSONField(name = "delay_ts")
    public void setDelayTs(int delayTs) {
        this.delayTs = delayTs;
    }

    @JSONField(name = "enable_evaluate")
    public void setEnableEvaluate(boolean enableEvaluate) {
        this.enableEvaluate = enableEvaluate;
    }

    @JSONField(name = "msg_body")
    public void setMsgBody(MsgBody msgBody) {
        this.msgBody = msgBody;
    }

    @JSONField(name = "similar_responses")
    public void setSimilarResponses(List<SimilarResponse> similarResponses) {
        this.similarResponses = similarResponses;
    }

    public String getExtra() {
        return extra;
    }

    @JSONField(name = "answer_id")
    public int getAnswerId() {
        return answerId;
    }

    @JSONField(name = "delay_ts")
    public int getDelayTs() {
        return delayTs;
    }

    @JSONField(name = "similar_responses")
    public List<SimilarResponse> getSimilarResponses() {
        return similarResponses;
    }

    @JSONField(name = "msg_body")
    public MsgBody getMsgBody() {
        return msgBody;
    }

    @JSONField(name = "enable_evaluate")
    public boolean isEnableEvaluate() {
        return enableEvaluate;
    }
}
