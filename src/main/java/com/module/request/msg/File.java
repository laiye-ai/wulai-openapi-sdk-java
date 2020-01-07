package com.module.request.msg;

import com.alibaba.fastjson.annotation.JSONField;

public class File {
    private String fileName;
    private String resourceUrl;

    @JSONField(name = "resource_url")
    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    @JSONField(name = "file_name")
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @JSONField(name = "resource_url")
    public String getResourceUrl() {
        return resourceUrl;
    }

    @JSONField(name = "file_name")
    public String getFileName() {
        return fileName;
    }

}
