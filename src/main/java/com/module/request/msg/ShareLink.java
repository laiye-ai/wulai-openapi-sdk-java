package com.module.request.msg;

import com.alibaba.fastjson.annotation.JSONField;

public class ShareLink {
    private String description;
    private String destinationUrl;
    private String coverUrl;
    private String title;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JSONField(name = "cover_url")
    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    @JSONField(name = "destination_url")
    public void setDestinationUrl(String destinationUrl) {
        this.destinationUrl = destinationUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    @JSONField(name = "cover_url")
    public String getCoverUrl() {
        return coverUrl;
    }

    @JSONField(name = "destination_url")
    public String getDestinationUrl() {
        return destinationUrl;
    }
}
