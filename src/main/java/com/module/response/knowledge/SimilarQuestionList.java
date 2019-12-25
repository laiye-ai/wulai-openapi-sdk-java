package com.module.response.knowledge;

import com.alibaba.fastjson.annotation.JSONField;
import com.module.request.knowledge.SimilarQuestion;

import java.util.List;

public class SimilarQuestionList {
    private List<SimilarQuestion> similarQuestions;
    private int pageCount;

    @JSONField(name = "page_count")
    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }


    @JSONField(name = "similar_questions")
    public void setSimilarQuestions(List<SimilarQuestion> similarQuestions) {
        this.similarQuestions = similarQuestions;
    }

    @JSONField(name = "page_count")
    public int getPageCount() {
        return pageCount;
    }

    @JSONField(name = "similar_questions")
    public List<SimilarQuestion> getSimilarQuestions() {
        return similarQuestions;
    }
}

