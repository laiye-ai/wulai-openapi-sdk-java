package com.module.response.stats;

import com.alibaba.fastjson.annotation.JSONField;

public class SatisfactionStats {
    private int thumbUpCount;
    private int wrongAnswerCount;
    private int badAnswerCount;

    @JSONField( name ="thumb_up_count")
    public int getThumbUpCount() { return thumbUpCount; }
    @JSONField( name ="thumb_up_count")
    public void setThumbUpCount(int value) { this.thumbUpCount = value; }

    @JSONField( name ="wrong_answer_count")
    public int getWrongAnswerCount() { return wrongAnswerCount; }
    @JSONField( name ="wrong_answer_count")
    public void setWrongAnswerCount(int value) { this.wrongAnswerCount = value; }

    @JSONField( name ="bad_answer_count")
    public int getBadAnswerCount() { return badAnswerCount; }
    @JSONField( name ="bad_answer_count")
    public void setBadAnswerCount(int value) { this.badAnswerCount = value; }
}
