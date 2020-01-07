package com.module.response.msg;

import com.alibaba.fastjson.annotation.JSONField;

public class Suggestion {
    private String suggestion;

    @JSONField(name = "suggestion")
    public String getSuggestion() { return suggestion; }
    @JSONField(name = "suggestion")
    public void setSuggestion(String value) { this.suggestion = value; }
}
