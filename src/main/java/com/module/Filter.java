package com.module;

import com.alibaba.fastjson.annotation.JSONField;

public class Filter {
    private boolean useInUserAttributeGroup;

    @JSONField(name = "use_in_user_attribute_group")
    public void setUseInUserAttributeGroup(boolean useInUserAttributeGroup) {
        this.useInUserAttributeGroup = useInUserAttributeGroup;
    }

    @JSONField(name = "use_in_user_attribute_group")
    public boolean isUseInUserAttributeGroup() {
        return useInUserAttributeGroup;
    }
}
