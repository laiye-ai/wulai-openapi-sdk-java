package com.module.request.dictionary;

import com.alibaba.fastjson.annotation.JSONField;

public class IntentEntity {
    private String standardValue;
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    @JSONField(name = "standard_value")
    public void setStandardValue(String standardValue) {
        this.standardValue = standardValue;
    }

    public String getName() {
        return name;
    }

    @JSONField(name = "standard_value")
    public String getStandardValue() {
        return standardValue;
    }

}
