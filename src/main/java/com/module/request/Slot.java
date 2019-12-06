package com.module.request;

public class Slot {
    private int id ;
    private String name ;
    private boolean query_slot_filling;

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
