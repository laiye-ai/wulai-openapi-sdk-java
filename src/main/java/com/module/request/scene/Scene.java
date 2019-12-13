package com.module.request.scene;

public class Scene {
    private String description;
    private int id ;
    private float smart_slot_filling_threshold;
    private String name ;
    private String intent_switch_mode="INTENT_SWITCH_MODE_NOTSET";

    public void setIntent_switch_mode(String intent_switch_mode) {
        this.intent_switch_mode = intent_switch_mode;
    }

    public String getIntent_switch_mode() {
        return intent_switch_mode;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSmart_slot_filling_threshold(float smart_slot_filling_threshold) {
        this.smart_slot_filling_threshold = smart_slot_filling_threshold;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getSmart_slot_filling_threshold() {
        return smart_slot_filling_threshold;
    }

    public String getDescription() {
        return description;
    }
}
