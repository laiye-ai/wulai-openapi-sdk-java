package com.module.request;

public class IntentTrigger {
    private String text;
    private int intent_id;
    private String type;
    private String id ;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getIntent_id() {
        return intent_id;
    }

    public void setIntent_id(int intent_id) {
        this.intent_id = intent_id;
    }

    public String getText() {
        return text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
