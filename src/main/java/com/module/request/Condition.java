package com.module.request;

public class Condition {
    private InEntity in_entity=new InEntity();

    public void setIn_entity(int in_entity_id) {
        this.in_entity.setId(in_entity_id);
    }

    public int getIn_entity() {
        return this.in_entity.getId();
    }
}
class InEntity {
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}