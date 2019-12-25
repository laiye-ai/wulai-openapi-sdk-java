package com.module.response.dictionary;

import com.module.request.dictionary.Value;

public class IntentResponse {
    private int id;
    private String name;
    private Value value;

    public void setId(int id) {
        this.id = id;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public Value getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
