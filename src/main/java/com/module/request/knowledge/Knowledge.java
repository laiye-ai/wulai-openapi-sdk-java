package com.module.request.knowledge;

import com.alibaba.fastjson.annotation.JSONField;

public class Knowledge {
    private boolean status;
    private String standardQuestion;
    private boolean respondAll;
    private String id;
    private boolean maintainedByUserAttributeGroup;
    private String update_time;

    public void setId(String id) {
        this.id = id;
    }

    @JSONField(name = "maintained_by_user_attribute_group")
    public void setMaintainedByUserAttributeGroup(boolean maintainedByUserAttributeGroup) {
        this.maintainedByUserAttributeGroup = maintainedByUserAttributeGroup;
    }

    @JSONField(name = "respond_all")
    public void setRespondAll(boolean respondAll) {
        this.respondAll = respondAll;
    }

    @JSONField(name = "standard_question")
    public void setStandardQuestion(String standardQuestion) {
        this.standardQuestion = standardQuestion;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    @JSONField(name = "standard_question")
    public String getStandardQuestion() {
        return standardQuestion;
    }

    @JSONField(name = "maintained_by_user_attribute_group")
    public boolean isMaintainedByUserAttributeGroup() {
        return maintainedByUserAttributeGroup;
    }

    @JSONField(name = "respond_all")
    public boolean isRespondAll() {
        return respondAll;
    }

    public boolean isStatus() {
        return status;
    }


}
