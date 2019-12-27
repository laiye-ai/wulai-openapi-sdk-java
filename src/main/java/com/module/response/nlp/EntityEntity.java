package com.module.response.nlp;

import com.alibaba.fastjson.annotation.JSONField;
import com.module.request.nlp.EnumerationEntity;
import com.module.request.nlp.RegexEntity;
import com.module.request.nlp.SystemEntity;

public class EntityEntity {
    private EnumerationEntity enumerationEntity;
    private RegexEntity regexEntity;
    private SystemEntity systemEntity;

    @JSONField(name = "regex_entity")
    public void setRegexEntity(RegexEntity regexEntity) {
        this.regexEntity = regexEntity;
    }

    @JSONField(name = "system_entity")
    public void setSystemEntity(SystemEntity systemEntity) {
        this.systemEntity = systemEntity;
    }

    @JSONField(name = "regex_entity")
    public RegexEntity getRegexEntity() {
        return regexEntity;
    }

    @JSONField(name = "system_entity")
    public SystemEntity getSystemEntity() {
        return systemEntity;
    }

    @JSONField(name = "enumeration_entity")
    public void setEnumerationEntity(EnumerationEntity enumerationEntity) {
        this.enumerationEntity = enumerationEntity;
    }

    @JSONField(name = "enumeration_entity")
    public EnumerationEntity getEnumerationEntity() {
        return enumerationEntity;
    }
}
