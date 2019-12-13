package com.module.request.scene;

public class Slot {
    private int id ;
    private String name ;
    private boolean query_slot_filling;
    private int scene_id;


    public void setScene_id(int scene_id) {
        this.scene_id = scene_id;
    }

    public int getScene_id() {
        return scene_id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setQuery_slot_filling(boolean query_slot_filling) {
        this.query_slot_filling = query_slot_filling;
    }

    public boolean isQuery_slot_filling() {
        return query_slot_filling;
    }
}
