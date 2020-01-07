package com.module.request.scene;

import com.alibaba.fastjson.annotation.JSONField;

public class IntentTrigger {
    private String text;
    private int intentId;
    private String type = "TRIGGER_TYPE_ERROR";
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setText(String text) {
        this.text = text;
    }

    @JSONField(name = "intent_id")
    public int getIntentId() {
        return intentId;
    }

    @JSONField(name = "intent_id")
    public void setIntentId(int intentId) {
        this.intentId = intentId;
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
