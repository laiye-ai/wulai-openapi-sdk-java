package com.module.response.msg;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class KeywordSuggestedResponse {
    private boolean isSend;
    private double score;
    private Keyword keyword;
    private String quickReply;
    private List<Response> response;

    public void setKeyword(Keyword keyword) {
        this.keyword = keyword;
    }

    @JSONField(name = "is_send")
    public void setSend(boolean send) {
        isSend = send;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public void setResponse(List<Response> response) {
        this.response = response;
    }

    @JSONField(name = "quick_reply")
    public void setQuickReply(String quickReply) {
        this.quickReply = quickReply;
    }

    public List<Response> getResponse() {
        return response;
    }

    public double getScore() {
        return score;
    }

    public Keyword getKeyword() {
        return keyword;
    }

    @JSONField(name = "quick_reply")
    public String getQuickReply() {
        return quickReply;
    }

    @JSONField(name = "is_send")
    public boolean isSend() {
        return isSend;
    }
}
