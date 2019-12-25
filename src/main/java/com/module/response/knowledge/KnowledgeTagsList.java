package com.module.response.knowledge;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class KnowledgeTagsList {
    private int pageCount;
    private List<KnowledgeTag> knowledgeTags;


    @JSONField(name = "page_count")
    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    @JSONField(name = "page_count")
    public int getPageCount() {
        return pageCount;
    }


    @JSONField(name = "knowledge_tags")
    public void setKnowledgeTags(List<KnowledgeTag> knowledgeTags) {
        this.knowledgeTags = knowledgeTags;
    }

    @JSONField(name = "knowledge_tags")
    public List<KnowledgeTag> getKnowledgeTags() {
        return knowledgeTags;
    }
}
