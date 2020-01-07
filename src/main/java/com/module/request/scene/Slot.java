package com.module.request.scene;

import com.alibaba.fastjson.annotation.JSONField;

public class Slot {
    private int id;
    private String name;
    private boolean querySlotFilling;
    private int sceneId;

    @JSONField(name = "scene_id")
    public void setSceneId(int sceneId) {
        this.sceneId = sceneId;
    }

    @JSONField(name = "scene_id")
    public int getSceneId() {
        return sceneId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @JSONField(name = "query_slot_filling")
    public void setQuerySlotFilling(boolean querySlotFilling) {
        this.querySlotFilling = querySlotFilling;
    }

    @JSONField(name = "query_slot_filling")
    public boolean isQuerySlotFilling() {
        return querySlotFilling;
    }
}
