package com.module.request.user;

import com.alibaba.fastjson.annotation.JSONField;

public class UserAttribute {

    private String id;
    private String name;
    private String type;
    private String valueType;
    private boolean useInUserAttributeGroup;
    private long lifespan;

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @JSONField(name = "use_in_user_attribute_group")
    public void setUseInUserAttributeGroup(boolean useInUserAttributeGroup) {
        this.useInUserAttributeGroup = useInUserAttributeGroup;
    }

    @JSONField(name = "name")
    public String getName() {
        return name;
    }

    @JSONField(name = "name")
    public void setName(String name) {
        this.name = name;
    }

    @JSONField(name = "lifespan")
    public long getLifespan() {
        return lifespan;
    }

    @JSONField(name = "lifespan")
    public void setLifespan(long lifespan) {
        this.lifespan = lifespan;
    }

    @JSONField(name = "value_type")
    public String getValueType() {
        return valueType;
    }

    @JSONField(name = "use_in_user_attribute_group")
    public boolean isUseInUserAttributeGroup() {
        return useInUserAttributeGroup;
    }

    @JSONField(name = "value_type")
    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    @JSONField(name = "id")
    public String getId() {
        return id;
    }

    @JSONField(name = "id")
    public void setId(String id) {
        this.id = id;
    }


}
