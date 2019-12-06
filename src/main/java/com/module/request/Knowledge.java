package com.module.request;

public class Knowledge {
    private boolean status;
    private String standard_question;
    private boolean respondAll;
    private String id ;
    private boolean maintainedByUserAttributeGroup;

    public void setId(String id) {
        this.id = id;
    }

    public void setMaintainedByUserAttributeGroup(boolean maintainedByUserAttributeGroup) {
        this.maintainedByUserAttributeGroup = maintainedByUserAttributeGroup;
    }

    public void setRespondAll(boolean respondAll) {
        this.respondAll = respondAll;
    }

    public void setStandard_question(String standard_question) {
        this.standard_question = standard_question;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getStandard_question() {
        return standard_question;
    }

    public boolean isMaintainedByUserAttributeGroup() {
        return maintainedByUserAttributeGroup;
    }

    public boolean isRespondAll() {
        return respondAll;
    }

    public boolean isStatus() {
        return status;
    }


}
