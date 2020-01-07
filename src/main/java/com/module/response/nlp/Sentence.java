package com.module.response.nlp;

import com.alibaba.fastjson.annotation.JSONField;

public class Sentence {
    private int id;
    private String sentence;

    @JSONField( name="id")
    public int getID() { return id; }
    @JSONField( name="id")
    public void setID(int value) { this.id = value; }

    @JSONField( name="sentence")
    public String getSentence() { return sentence; }
    @JSONField( name="sentence")
    public void setSentence(String value) { this.sentence = value; }
}
