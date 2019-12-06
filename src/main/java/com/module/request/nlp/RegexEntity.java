package com.module.request.nlp;

public class RegexEntity implements Entity {
    private String text;
    private String name;

    public void setText(String text) {
        this.text = text;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public String getName() {
        return name;
    }
}
