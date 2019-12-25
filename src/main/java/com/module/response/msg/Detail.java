package com.module.response.msg;

public class Detail {
    private QA qa;
    private Chitchat chitchat;
    private Task task;
    private Keyword keyword;

    public void setKeyword(Keyword keyword) {
        this.keyword = keyword;
    }

    public void setChitchat(Chitchat chitchat) {
        this.chitchat = chitchat;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Keyword getKeyword() {
        return keyword;
    }

    public Chitchat getChitchat() {
        return chitchat;
    }

    public Task getTask() {
        return task;
    }

    public void setQa(QA qa) {
        this.qa = qa;
    }

    public QA getQa() {
        return qa;
    }
}
