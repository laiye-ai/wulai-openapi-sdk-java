package com.module.request.scene;

import com.alibaba.fastjson.annotation.JSONField;
import com.module.response.scene.Action;
import com.module.response.scene.Connection;

import java.util.List;

public class Block {
    private String name;
    private String defaultSlotValue;
    private boolean slotFillingWhenAsked;
    private List<Connection> connections;
    private int slotID;
    private String mode;
    private int requestCount;
    private int intentID;
    private int id;
    private List<Response> responses;
    private Action action;


    @JSONField(name = "action")
    public void setAction(Action action) {
        this.action = action;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @JSONField(name = "action")
    public Action getAction() {
        return action;
    }

    @JSONField(name = "name")
    public String getName() { return name; }
    @JSONField(name = "name")
    public void setName(String value) { this.name = value; }

    @JSONField(name = "default_slot_value")
    public String getDefaultSlotValue() { return defaultSlotValue; }
    @JSONField(name = "default_slot_value")
    public void setDefaultSlotValue(String value) { this.defaultSlotValue = value; }

    @JSONField(name = "slot_filling_when_asked")
    public boolean getSlotFillingWhenAsked() { return slotFillingWhenAsked; }
    @JSONField(name = "slot_filling_when_asked")
    public void setSlotFillingWhenAsked(boolean value) { this.slotFillingWhenAsked = value; }

    @JSONField(name = "connections")
    public List<Connection> getConnections() { return connections; }
    @JSONField(name = "connections")
    public void setConnections(List<Connection> value) { this.connections = value; }

    @JSONField(name = "slot_id")
    public int getSlotID() { return slotID; }
    @JSONField(name = "slot_id")
    public void setSlotID(int value) { this.slotID = value; }

    @JSONField(name = "mode")
    public String getMode() { return mode; }
    @JSONField(name = "mode")
    public void setMode(String value) { this.mode = value; }

    @JSONField(name = "request_count")
    public int getRequestCount() { return requestCount; }
    @JSONField(name = "request_count")
    public void setRequestCount(int value) { this.requestCount = value; }

    @JSONField(name = "intent_id")
    public int getIntentID() { return intentID; }
    @JSONField(name = "intent_id")
    public void setIntentID(int value) { this.intentID = value; }

    @JSONField(name = "id")
    public int getID() { return id; }
    @JSONField(name = "id")
    public void setID(int value) { this.id = value; }

    @JSONField(name = "responses")
    public List<Response> getResponses() { return responses; }
    @JSONField(name = "responses")
    public void setResponses(List<Response> value) { this.responses = value; }

}
