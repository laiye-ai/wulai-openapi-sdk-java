package com.module.response.dictionary;

import com.alibaba.fastjson.annotation.JSONField;
import com.module.request.dictionary.TermItem;

import java.util.List;

public class TermListResponse {

    private List<TermItem> termItem;
    private int pageCount;

    @JSONField(name = "term_item")
    public void setTermItem(List<TermItem> termItem) {
        this.termItem = termItem;
    }

    @JSONField(name = "term_item")
    public List<TermItem> getTermItem() {
        return termItem;
    }

    @JSONField(name = "page_count")
    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    @JSONField(name = "page_count")
    public int getPageCount() {
        return pageCount;
    }

}
