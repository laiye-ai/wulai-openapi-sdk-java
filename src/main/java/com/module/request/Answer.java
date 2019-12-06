package com.module.request;

public class Answer {
    private String knowledge_id;
    private MsgBody msg_body;
    private String id ;

    public void setId(String id) {
        this.id = id;
    }

    public void setKnowledge_id(String knowledge_id) {
        this.knowledge_id = knowledge_id;
    }

    public void setMsg_body(MsgBody msg_body) {
        this.msg_body = msg_body;
    }
}
