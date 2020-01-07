package com.module.response.nlp;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class Cluster {
    private int id;
    private List<Sentence> sentences;

    @JSONField( name="id")
    public int getID() { return id; }
    @JSONField( name="id")
    public void setID(int value) { this.id = value; }

    @JSONField( name="sentences")
    public List<Sentence> getSentences() { return sentences; }
    @JSONField( name="sentences")
    public void setSentences(List<Sentence> value) { this.sentences = value; }
}
