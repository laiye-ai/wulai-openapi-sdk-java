package com.module.request.nlp;

public class SystemEntity implements Entity {
    private String text;
    private String standard_value;
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setStandard_value(String standard_value) {
        this.standard_value = standard_value;
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
}
