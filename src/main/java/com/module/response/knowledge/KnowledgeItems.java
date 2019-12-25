package com.module.response.knowledge;

import com.alibaba.fastjson.annotation.JSONField;
import com.module.request.knowledge.Knowledge;
import com.module.request.knowledge.SimilarQuestion;

import java.util.List;

public class KnowledgeItems {

    private KnowledgeTag knowledgeTag;
    private List<SimilarQuestion> similarQuestions;
    private Knowledge knowledge;

    @JSONField(name = "knowledge")
    public void setKnowledge(Knowledge knowledge) {
        this.knowledge = knowledge;
    }


    @JSONField(name = "knowledge_tag")
    public void setKnowledgeTag(KnowledgeTag knowledgeTag) {
        this.knowledgeTag = knowledgeTag;
    }


    @JSONField(name = "similar_questions")
    public void setSimilarQuestions(List<SimilarQuestion> similarQuestions) {
        this.similarQuestions = similarQuestions;
    }

    @JSONField(name = "knowledge")
    public Knowledge getKnowledge() {
        return knowledge;
    }

    @JSONField(name = "knowledge_tag")
    public KnowledgeTag getKnowledgeTag() {
        return knowledgeTag;
    }

    @JSONField(name = "similar_questions")
    public List<SimilarQuestion> getSimilarQuestions() {
        return similarQuestions;
    }
}
