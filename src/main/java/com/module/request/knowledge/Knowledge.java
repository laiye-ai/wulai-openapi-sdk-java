package com.module.request.knowledge;

public class Knowledge {
    private boolean status;
    private String standard_question;
    private boolean respond_all;
    private String id ;
    private boolean maintained_by_user_attribute_group;

    public void setId(String id) {
        this.id = id;
    }

    public void setMaintained_by_user_attribute_group(boolean maintained_by_user_attribute_group) {
        this.maintained_by_user_attribute_group = maintained_by_user_attribute_group;
    }

    public void setRespond_all(boolean respond_all) {
        this.respond_all = respond_all;
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

    public boolean isMaintained_by_user_attribute_group() {
        return maintained_by_user_attribute_group;
    }

    public boolean isRespond_all() {
        return respond_all;
    }

    public boolean isStatus() {
        return status;
    }


}
