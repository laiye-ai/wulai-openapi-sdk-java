package com.module.request.dictionary;

public class Entity {
    private String type;
    private int id;
    private String name;
    private ValueEntity value;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setValue(ValueEntity value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public ValueEntity getValue() {
        return value;
    }
}
