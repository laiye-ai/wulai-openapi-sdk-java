package com.module.request;

import java.util.ArrayList;
import java.util.List;

public class TermItem {
    private List<String> synonyms =new ArrayList<>();

    private Term term;

    public void setSynonyms(List<String> synonyms) {
        this.synonyms = synonyms;
    }

    public List<String> getSynonyms() {
        return synonyms;
    }

    public void addSynonym(String synonym){
        synonyms.add(synonym);
    }

    public void setTerm(Term term) {
        this.term = term;
    }

    public Term getTerm() {
        return term;
    }
}
