package com.module.request.knowledge;

import com.module.request.knowledge.Knowledge;

public class KnowledgeTagKnowledge {
    private Knowledge knowledge;
    private String knowledge_tag_id;

    public void setKnowledge_tag_id(String knowledge_tag_id) {
        this.knowledge_tag_id = knowledge_tag_id;
    }

    public Knowledge getKnowledge() {
        return knowledge;
    }

    public void setKnowledge(Knowledge knowledge) {
        this.knowledge = knowledge;
    }

    public String getKnowledge_tag_id() {
        return knowledge_tag_id;
    }
}
