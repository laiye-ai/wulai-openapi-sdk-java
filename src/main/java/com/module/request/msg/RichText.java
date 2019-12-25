package com.module.request.msg;

import com.alibaba.fastjson.annotation.JSONField;

public class RichText {

    private String resourceUrl;

    @JSONField(name = "resource_url")
    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    @JSONField(name = "resource_url")
    public String getResourceUrl() {
        return resourceUrl;
    }
}
