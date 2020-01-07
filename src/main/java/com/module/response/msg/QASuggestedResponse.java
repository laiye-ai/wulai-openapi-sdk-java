package com.module.response.msg;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class QASuggestedResponse {

    private QA Qa;
    private boolean isSend;
    private double score;
    private List<Response> response;
    private List<String> quickReply;

    @JSONField(name = "qa")
    public QA getQa() {
        return Qa;
    }

    @JSONField(name = "qa")
    public void setQa(QA value) {
        this.Qa = value;
    }

    @JSONField(name = "is_send")
    public boolean isSend() {
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
    public List<Response> getResponse() {
        return response;
    }

    @JSONField(name = "response")
    public void setResponse(List<Response> value) {
        this.response = value;
    }

    @JSONField(name = "quick_reply")
    public List<String> getQuickReply() {
        return quickReply;
    }

    @JSONField(name = "quick_reply")
    public void setQuickReply(List<String> value) {
        this.quickReply = value;
    }
}
