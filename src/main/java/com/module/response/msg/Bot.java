package com.module.response.msg;

public class Bot {
    private QA qa;
    private Task task;
    private Chitchat chitchat;
    private Keyword keyword;

    public void setTask(Task task) {
        this.task = task;
    }

    public void setChitchat(Chitchat chitchat) {
        this.chitchat = chitchat;
    }

    public void setKeyword(Keyword keyword) {
        this.keyword = keyword;
    }

    public void setQa(QA qa) {
        this.qa = qa;
    }

    public Task getTask() {
        return task;
    }

    public Chitchat getChitchat() {
        return chitchat;
    }

    public Keyword getKeyword() {
        return keyword;
    }
}
