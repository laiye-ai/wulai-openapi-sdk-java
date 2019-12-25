package com.module.response.msg;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class TaskSuggestedResponse {

    private boolean isSend;
    private double score;
    private Task task;
    private String quickReply;
    private List<Response> response;


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

    public void setTask(Task task) {
        this.task = task;
    }

    public Task getTask() {
        return task;
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
