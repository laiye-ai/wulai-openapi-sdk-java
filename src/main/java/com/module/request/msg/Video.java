package com.module.request.msg;

import com.alibaba.fastjson.annotation.JSONField;

public class Video {
    private String resourceUrl;
    private String thumb;
    private String description;
    private String title;

    @JSONField(name = "resource_url")
    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @JSONField(name = "resource_url")
    public String getResourceUrl() {
        return resourceUrl;
    }

    public String getDescription() {
        return description;
    }

    public String getThumb() {
        return thumb;
    }

    public String getTitle() {
        return title;
    }
}
