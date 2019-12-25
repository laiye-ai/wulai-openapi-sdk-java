package com.module.request.dictionary;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.ArrayList;
import java.util.List;

public class Value {
    private List<String> synonyms = new ArrayList<>();
    private String standardValue;

    public void setSynonyms(List<String> synonyms) {
        this.synonyms = synonyms;
    }

    @JSONField(name = "standard_value")
    public void setStandardValue(String standardValue) {
        this.standardValue = standardValue;
    }

    public List<String> getSynonyms() {
        return synonyms;
    }

    @JSONField(name = "standard_value")
    public String getStandardValue() {
        return standardValue;
    }

    public void addSynonym(String synonym) {
        this.synonyms.add(synonym);
    }
}
