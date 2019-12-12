package com.module.request.knowledge;


public class SimilarQuestion{
    private String knowledge_id;
    private String question;
    private String id ;

    public void setKnowledge_id(String knowledge_id) {
        this.knowledge_id = knowledge_id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getId() {
        return id;
    }

    public String getKnowledge_id() {
        return knowledge_id;
    }

    public String getQuestion() {
        return question;
    }
}