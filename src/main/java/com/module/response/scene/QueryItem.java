package com.module.response.scene;

import com.alibaba.fastjson.annotation.JSONField;

public class QueryItem {

    private String content;
    private int id;
    private RecommendIntent recommendIntent;

    @JSONField( name ="content")
    public String getContent() { return content; }
    @JSONField( name ="content")
    public void setContent(String value) { this.content = value; }

    @JSONField( name ="id")
    public int getID() { return id; }
    @JSONField( name ="id")
    public void setID(int value) { this.id = value; }

    @JSONField( name ="recommend_intent")
    public RecommendIntent getRecommendIntent() { return recommendIntent; }
    @JSONField( name ="recommend_intent")
    public void setRecommendIntent(RecommendIntent value) { this.recommendIntent = value; }
}
