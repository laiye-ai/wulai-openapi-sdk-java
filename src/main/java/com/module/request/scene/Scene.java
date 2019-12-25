package com.module.request.scene;

import com.alibaba.fastjson.annotation.JSONField;

public class Scene {
    private String description;
    private int id;
    private float smartSlotFillingThreshold;
    private String name;
    private String intentSwitchMode = "INTENT_SWITCH_MODE_NOTSET";

    @JSONField(name = "intent_switch_mode")
    public void setIntentSwitchMode(String intentSwitchMode) {
        this.intentSwitchMode = intentSwitchMode;
    }

    @JSONField(name = "intent_switch_mode")
    public String getIntentSwitchMode() {
        return intentSwitchMode;
    }

    @JSONField(name = "id")
    public void setId(int id) {
        this.id = id;
    }

    @JSONField(name = "name")
    public void setName(String name) {
        this.name = name;
    }

    @JSONField(name = "description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JSONField(name = "smart_slot_filling_threshold")
    public void setSmartSlotFillingThreshold(float smartSlotFillingThreshold) {
        this.smartSlotFillingThreshold = smartSlotFillingThreshold;
    }

    @JSONField(name = "id")
    public int getId() {
        return id;
    }

    @JSONField(name = "name")
    public String getName() {
        return name;
    }

    @JSONField(name = "smart_slot_filling_threshold")
    public float getSmartSlotFillingThreshold() {
        return smartSlotFillingThreshold;
    }

    @JSONField(name = "description")
    public String getDescription() {
        return description;
    }
}
