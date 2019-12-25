package com.module.response.msg;

import com.alibaba.fastjson.annotation.JSONField;

public class Keyword {

    private int keywordId;
    private String keyword;

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }


    @JSONField(name = "keyword_id")
    public int getKeywordId() {
        return keywordId;
    }

    @JSONField(name = "keyword_id")
    public void setKeywordId(int keywordId) {
        this.keywordId = keywordId;
    }

    public String getKeyword() {
        return keyword;
    }
}
