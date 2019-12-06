package com.module.request;

public class Intent {
    private int lifespan_mins;
    private int id ;
    private String name ;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLifespan_mins(int lifespan_mins) {
        this.lifespan_mins = lifespan_mins;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getLifespan_mins() {
        return lifespan_mins;
    }
}
