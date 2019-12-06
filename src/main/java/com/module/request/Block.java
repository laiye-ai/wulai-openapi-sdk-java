package com.module.request;

public class Block {
    private int intent_id;
    private String name ;
    private String mode="RESPONSE_ERROR";
    private boolean slot_memorizing;
    private boolean slot_filling_when_asked=false;
    private Action action;
    private String default_slot_value;
    private int request_count=3;

    public void setSlot_filling_when_asked(boolean slot_filling_when_asked) {
        this.slot_filling_when_asked = slot_filling_when_asked;
    }

    public boolean isSlot_filling_when_asked() {
        return slot_filling_when_asked;
    }

    public void setDefault_slot_value(String default_slot_value) {
        this.default_slot_value = default_slot_value;
    }

    public void setRequest_count(int request_count) {
        this.request_count = request_count;
    }

    public int getRequest_count() {
        return request_count;
    }

    public String getDefault_slot_value() {
        return default_slot_value;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public Action getAction() {
        return action;
    }

    public void setSlot_memorizing(boolean slot_memorizing) {
        this.slot_memorizing = slot_memorizing;
    }

    public boolean isSlot_memorizing() {
        return slot_memorizing;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIntent_id(int intent_id) {
        this.intent_id = intent_id;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getName() {
        return name;
    }

    public int getIntent_id() {
        return intent_id;
    }

    public String getMode() {
        return mode;
    }



}
