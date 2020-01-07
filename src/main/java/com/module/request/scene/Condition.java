package com.module.request.scene;

import com.alibaba.fastjson.annotation.JSONField;
import com.module.request.scene.condition.*;

public class Condition {

    private Default aDefault;
    private DismatchRegex dismatchRegex;
    private EqualTo equalTo;
    private Exclude exclude;
    private GreaterThan greaterThan;
    private GreaterThanOrEqualTo greaterThanOrEqualTo;
    private Include include;
    private InEntity inEntity;
    private LessThan lessThan;
    private LessThanOrEqualTo lessThanOrEqualTo;
    private MatchRegex matchRegex;
    private NotEqualTo notEqualTo;
    private NotInEntity notInEntity;

    @JSONField(name = "default")
    public void setaDefault(Default aDefault) {
        this.aDefault = aDefault;
    }

    @JSONField(name = "default")
    public Default getaDefault() {
        return aDefault;
    }

    @JSONField(name = "dismatch_regex")
    public void setDismatchRegex(DismatchRegex dismatchRegex) {
        this.dismatchRegex = dismatchRegex;
    }

    @JSONField(name = "dismatch_regex")
    public DismatchRegex getDismatchRegex() {
        return dismatchRegex;
    }

    @JSONField(name = "equal_to")
    public void setEqualTo(EqualTo equalTo) {
        this.equalTo = equalTo;
    }

    @JSONField(name = "equal_to")
    public EqualTo getEqualTo() {
        return equalTo;
    }

    @JSONField(name = "exclude")
    public void setExclude(Exclude exclude) {
        this.exclude = exclude;
    }

    @JSONField(name = "exclude")
    public Exclude getExclude() {
        return exclude;
    }

    @JSONField(name = "greater_than")
    public void setGreaterThan(GreaterThan greaterThan) {
        this.greaterThan = greaterThan;
    }

    @JSONField(name = "greater_than")
    public GreaterThan getGreaterThan() {
        return greaterThan;
    }

    @JSONField(name = "greater_than_or_equal_to")
    public void setGreaterThanOrEqualTo(GreaterThanOrEqualTo greaterThanOrEqualTo) {
        this.greaterThanOrEqualTo = greaterThanOrEqualTo;
    }

    @JSONField(name = "greater_than_or_equal_to")
    public GreaterThanOrEqualTo getGreaterThanOrEqualTo() {
        return greaterThanOrEqualTo;
    }

    @JSONField(name = "include")
    public void setInclude(Include include) {
        this.include = include;
    }

    @JSONField(name = "include")
    public Include getInclude() {
        return include;
    }

    @JSONField(name = "less_than")
    public void setLessThan(LessThan lessThan) {
        this.lessThan = lessThan;
    }

    @JSONField(name = "less_than")
    public LessThan getLessThan() {
        return lessThan;
    }

    @JSONField(name = "less_than_or_equal_to")
    public void setLessThanOrEqualTo(LessThanOrEqualTo lessThanOrEqualTo) {
        this.lessThanOrEqualTo = lessThanOrEqualTo;
    }

    @JSONField(name = "less_than_or_equal_to")
    public LessThanOrEqualTo getLessThanOrEqualTo() {
        return lessThanOrEqualTo;
    }

    public void setMatchRegex(MatchRegex matchRegex) {
        this.matchRegex = matchRegex;
    }

    public MatchRegex getMatchRegex() {
        return matchRegex;
    }

    public void setNotEqualTo(NotEqualTo notEqualTo) {
        this.notEqualTo = notEqualTo;
    }

    public NotEqualTo getNotEqualTo() {
        return notEqualTo;
    }

    public void setNotInEntity(NotInEntity notInEntity) {
        this.notInEntity = notInEntity;
    }

    public NotInEntity getNotInEntity() {
        return notInEntity;
    }


    @JSONField(name = "in_entity")
    public InEntity getInEntity() {
        return inEntity;
    }

    @JSONField(name = "in_entity")
    public void setInEntity(InEntity value) {
        this.inEntity = value;
    }
}
