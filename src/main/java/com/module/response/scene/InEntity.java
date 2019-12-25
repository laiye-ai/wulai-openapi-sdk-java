package com.module.response.scene;

import com.alibaba.fastjson.annotation.JSONField;

public class InEntity {
    private int id;

    @JSONField(name = "id")
    public int getID() { return id; }
    @JSONField(name = "id")
    public void setID(int value) { this.id = value; }


}
