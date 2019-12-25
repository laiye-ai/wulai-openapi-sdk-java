package com.module.response.scene;

import com.alibaba.fastjson.annotation.JSONField;

public class RecommendIntent {

    private int intentID;
    private int score;
    private String intentName;

    @JSONField( name ="intent_id")
    public int getIntentID() { return intentID; }
    @JSONField( name ="intent_id")
    public void setIntentID(int value) { this.intentID = value; }

    @JSONField( name ="score")
    public int getScore() { return score; }
    @JSONField( name ="score")
    public void setScore(int value) { this.score = value; }

    @JSONField( name ="intent_name")
    public String getIntentName() { return intentName; }
    @JSONField( name ="intent_name")
    public void setIntentName(String value) { this.intentName = value; }
}
