package com.module.request.msg;

import com.alibaba.fastjson.annotation.JSONField;

public class Voice {
    private String resourceUrl;
    private String type;
    private String recognition;

    @JSONField(name = "resource_url")
    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRecognition(String recognition) {
        this.recognition = recognition;
    }

    @JSONField(name = "resource_url")
    public String getResourceUrl() {
        return resourceUrl;
    }

    public String getType() {
        return type;
    }

    public String getRecognition() {
        return recognition;
    }
}
