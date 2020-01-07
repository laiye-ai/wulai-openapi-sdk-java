package com.module.response.nlp;

import com.alibaba.fastjson.annotation.JSONField;

public class EntityElement {

    private String type;
    private int idxStart;
    private EntityEntity entity;

    @JSONField( name="type")
    public String getType() { return type; }
    @JSONField( name="type")
    public void setType(String value) { this.type = value; }

    @JSONField( name="idx_start")
    public int getIdxStart() { return idxStart; }
    @JSONField( name="idx_start")
    public void setIdxStart(int value) { this.idxStart = value; }

    @JSONField( name="entity")
    public EntityEntity getEntity() { return entity; }
    @JSONField( name="entity")
    public void setEntity(EntityEntity value) { this.entity = value; }
}
