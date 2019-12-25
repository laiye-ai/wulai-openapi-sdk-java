package com.module.response.msg;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class QaSuggestedResponse {

    private QA Qa;
    private boolean isSend;
    private double score;
    private List<Object> response;
    private List<Object> quickReply;

    @JSONField(name = "QA")
    public QA getQa() {
        return Qa;
    }

    @JSONField(name = "QA")
    public void setQa(QA value) {
        this.Qa = value;
    }

    @JSONField(name = "is_send")
    public boolean getIsSend() {
        return isSend;
    }

    @JSONField(name = "is_send")
    public void setIsSend(boolean value) {
        this.isSend = value;
    }

    @JSONField(name = "score")
    public double getScore() {
        return score;
    }

    @JSONField(name = "score")
    public void setScore(double value) {
        this.score = value;
    }

    @JSONField(name = "response")
    public List<Object> getResponse() {
        return response;
    }

    @JSONField(name = "response")
    public void setResponse(List<Object> value) {
        this.response = value;
    }

    @JSONField(name = "quick_reply")
    public List<Object> getQuickReply() {
        return quickReply;
    }

    @JSONField(name = "quick_reply")
    public void setQuickReply(List<Object> value) {
        this.quickReply = value;
    }
}
