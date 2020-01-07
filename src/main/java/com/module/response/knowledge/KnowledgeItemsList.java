package com.module.response.knowledge;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class KnowledgeItemsList {
    private int pageCount;
    private List<KnowledgeItems> knowledgeItems;

    @JSONField(name = "page_count")
    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    @JSONField(name = "page_count")
    public int getPageCount() {
        return pageCount;
    }

    @JSONField(name = "knowledge_items")
    public void setKnowledgeItems(List<KnowledgeItems> knowledgeItems) {
        this.knowledgeItems = knowledgeItems;
    }

    @JSONField(name = "knowledge_items")
    public List<KnowledgeItems> getKnowledgeItems() {
        return knowledgeItems;
    }
}
