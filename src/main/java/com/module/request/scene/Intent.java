package com.module.request.scene;

import com.alibaba.fastjson.annotation.JSONField;

public class Intent {
    private int lifespanMins;
    private int id;
    private int sceneId;
    private String name;
    private boolean status;

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

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

    public void setName(String name) {
        this.name = name;
    }

    @JSONField(name = "lifespan_mins")
    public void setLifespanMins(int lifespanMins) {
        this.lifespanMins = lifespanMins;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @JSONField(name = "lifespan_mins")
    public int getLifespanMins() {
        return lifespanMins;
    }
}
