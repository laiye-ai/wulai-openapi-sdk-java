package com.module.request.scene;

public class Intent {
    private int lifespan_mins;
    private int id ;
    private int scene_id;
    private String name ;

    public void setScene_id(int scene_id) {
        this.scene_id = scene_id;
    }

    public int getScene_id() {
        return scene_id;
    }

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
