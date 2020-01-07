package com.module.response.scene;

import com.alibaba.fastjson.annotation.JSONField;

public class DataSource {
    private int entityID;
    private int slotID;
    private int id;

    @JSONField( name ="entity_id")
    public int getEntityID() { return entityID; }
    @JSONField( name ="entity_id")
    public void setEntityID(int value) { this.entityID = value; }

    @JSONField( name ="slot_id")
    public int getSlotID() { return slotID; }
    @JSONField( name ="slot_id")
    public void setSlotID(int value) { this.slotID = value; }

    @JSONField( name ="id")
    public int getID() { return id; }
    @JSONField( name ="id")
    public void setID(int value) { this.id = value; }
}
