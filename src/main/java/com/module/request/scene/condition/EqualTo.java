package com.module.request.scene.condition;

public class EqualTo {
    private String value;

    public EqualTo(){
    }
    public EqualTo(String value){
        this.value=value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
