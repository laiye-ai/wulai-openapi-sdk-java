package com.module.response.dictionary;

import com.alibaba.fastjson.annotation.JSONField;

public class Entity {
    private String type;
    private int id;
    private String name;

    @JSONField(name= "type")
    public String getType() { return type; }
    @JSONField(name= "type")
    public void setType(String value) { this.type = value; }

    @JSONField(name= "id")
    public int getID() { return id; }
    @JSONField(name= "id")
    public void setID(int value) { this.id = value; }

    @JSONField(name= "name")
    public String getName() { return name; }
    @JSONField(name= "name")
    public void setName(String value) { this.name = value; }
}
