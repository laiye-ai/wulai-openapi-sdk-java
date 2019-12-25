package com.module.request.dictionary;

import com.alibaba.fastjson.annotation.JSONField;

public class ValueEntity {
    private IntentEntityValue intentEntityValue;
    private SystemEntityValue systemEntityValue;
    private RegexEntityValue regexEntityValue;
    private EnumerationEntityValue enumerationEntityValue;

    @JSONField(name = "system_entity_value")
    public void setSystemEntityValue(SystemEntityValue systemEntityValue) {
        this.systemEntityValue = systemEntityValue;
    }

    @JSONField(name = "regex_entity_value")
    public void setRegexEntityValue(RegexEntityValue regexEntityValue) {
        this.regexEntityValue = regexEntityValue;
    }

    @JSONField(name = "intent_entity_value")
    public void setIntentEntityValue(IntentEntityValue intentEntityValue) {
        this.intentEntityValue = intentEntityValue;
    }

    @JSONField(name = "enumeration_entity_value")
    public void setEnumerationEntityValue(EnumerationEntityValue enumerationEntityValue) {
        this.enumerationEntityValue = enumerationEntityValue;
    }

    @JSONField(name = "system_entity_value")
    public SystemEntityValue getSystemEntityValue() {
        return systemEntityValue;
    }

    @JSONField(name = "regex_entity_value")
    public RegexEntityValue getRegexEntityValue() {
        return regexEntityValue;
    }


    @JSONField(name = "intent_entity_value")
    public IntentEntityValue getIntentEntityValue() {
        return intentEntityValue;
    }

    @JSONField(name = "enumeration_entity_value")
    public EnumerationEntityValue getEnumerationEntityValue() {
        return enumerationEntityValue;
    }
}
