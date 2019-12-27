package com.module.request.knowledge;

import com.alibaba.fastjson.annotation.JSONField;
import com.module.request.msg.MsgBody;

public class Answer {
    private String knowledgeId;
    private MsgBody msgBody;
    private String id;

    public void setId(String id) {
        this.id = id;
    }

    @JSONField(name = "knowledge_id")
    public void setKnowledgeId(String knowledgeId) {
        this.knowledgeId = knowledgeId;
    }

    @JSONField(name = "msg_body")
    public void setMsgBody(MsgBody msgBody) {
        this.msgBody = msgBody;
    }

    @JSONField(name = "msg_body")
    public MsgBody getMsgBody() {
        return msgBody;
    }

    public String getId() {
        return id;
    }

    @JSONField(name = "knowledge_id")
    public String getKnowledgeId() {
        return knowledgeId;
    }
}
