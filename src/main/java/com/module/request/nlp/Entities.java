package com.module.request.nlp;

import com.alibaba.fastjson.annotation.JSONField;

public class Entities {
    private String type = "EXTRACT_ENTITIES_TYPE_ERROR";
    private int idxStart;
    private Entity entity;
    private int idxEnd;
    private String value;
    private String seg_value;
    private String desc;

    public void setValue(String value) {
        this.value = value;
    }

    public void setType(String type) {
        this.type = type;
    }

    @JSONField(name = "idx_start")
    public void setIdxStart(int idxStart) {
        this.idxStart = idxStart;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    @JSONField(name = "idx_end")
    public void setIdxEnd(int idxEnd) {
        this.idxEnd = idxEnd;
    }

    @JSONField(name = "seg_value")
    public void setSeg_value(String seg_value) {
        this.seg_value = seg_value;
    }

    public String getType() {
        return type;
    }

    @JSONField(name = "idx_start")
    public int getIdxStart() {
        return idxStart;
    }

    public Entity getEntity() {
        return entity;
    }

    @JSONField(name = "idx_end")
    public int getIdxEnd() {
        return idxEnd;
    }

    public String getDesc() {
        return desc;
    }


    @JSONField(name = "seg_value")
    public String getSeg_value() {
        return seg_value;
    }

    public String getValue() {
        return value;
    }

}
