package com.module.response.scene;

import com.alibaba.fastjson.annotation.JSONField;

public class Condition {

    private InEntity inEntity;

    @JSONField(name = "in_entity")
    public InEntity getInEntity() { return inEntity; }
    @JSONField(name = "in_entity")
    public void setInEntity(InEntity value) { this.inEntity = value; }
}
