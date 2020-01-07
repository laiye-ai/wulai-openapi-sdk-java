package com.module.request.scene;

import com.alibaba.fastjson.annotation.JSONField;
import com.module.response.scene.Connection;

public class Relation {
    private Connection connection;
    private int intentID;
    private int id;

    @JSONField(name = "id")
    public void setId(int id) {
        this.id = id;
    }

    @JSONField(name = "id")
    public int getId() {
        return id;
    }

    @JSONField(name = "connection")
    public Connection getConnection() {
        return connection;
    }

    @JSONField(name = "connection")
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @JSONField(name = "intent_id")
    public int getIntentID() {
        return intentID;
    }

    @JSONField(name = "intent_id")
    public void setIntentID(int value) {
        this.intentID = value;
    }
}
