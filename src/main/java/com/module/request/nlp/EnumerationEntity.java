package com.module.request.nlp;

import java.util.ArrayList;
import java.util.List;

public class EnumerationEntity implements Entity {
    private String text;
    private String standard_value;
    private String name;
    private List<String> synonyms = new ArrayList<>();

    public void setName(String name) {
        this.name = name;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setSynonyms(List<String> synonyms) {
        this.synonyms = synonyms;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

    public String getStandard_value() {
        return standard_value;
    }

    public List<String> getSynonyms() {
        return synonyms;
    }

    public void addSynonym(String synonym) {
        synonyms.add(synonym);
    }

}
