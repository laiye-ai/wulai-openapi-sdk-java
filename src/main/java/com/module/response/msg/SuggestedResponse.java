package com.module.response.msg;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class SuggestedResponse {
    private boolean isSend;
    private double score;
    private String source;
    private String quickReply;
    private List<Response> response;

    public void setSource(String source) {
        this.source = source;
    }

    public String getSource() {
        return source;
    }

    @JSONField(name = "quick_reply")
    public void setQuickReply(String quickReply) {
        this.quickReply = quickReply;
    }

    public double getScore() {
        return score;
    }

    public void setResponse(List<Response> response) {
        this.response = response;
    }

    public List<Response> getResponse() {
        return response;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @JSONField(name = "quick_reply")
    public String getQuickReply() {
        return quickReply;
    }

    @JSONField(name = "is_send")
    public void setSend(boolean send) {
        isSend = send;
    }

    @JSONField(name = "is_send")
    public boolean isSend() {
        return isSend;
    }
}
