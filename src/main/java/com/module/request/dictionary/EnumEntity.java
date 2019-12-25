package com.module.request.dictionary;

import java.util.List;

public class EnumEntity {
    private int id;
    private String name;
    private List<Value> values;

    public void setValues(List<Value> values) {
        this.values = values;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Value> getValues() {
        return values;
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
}
