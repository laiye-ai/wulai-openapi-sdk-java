package com.module.request.dictionary;

import java.util.ArrayList;
import java.util.List;

public class Value {
    private List<String> synonyms=new ArrayList<>();
    private String standard_value;

    public void setSynonyms(List<String> synonyms) {
        this.synonyms = synonyms;
    }

    public void setStandard_value(String standard_value) {
        this.standard_value = standard_value;
    }

    public List<String> getSynonyms() {
        return synonyms;
    }

    public String getStandard_value() {
        return standard_value;
    }

    public void addSynonym(String synonym){
        this.synonyms.add(synonym);
    }
}
