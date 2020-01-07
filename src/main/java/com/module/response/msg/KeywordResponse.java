package com.module.response.msg;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class KeywordResponse  {

    private boolean isDispatch;
    private String msgId;
    private String extra;
    private List<KeywordSuggestedResponse> keywordSuggestedResponse;

    @JSONField(name = "msg_id")
    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    @JSONField(name = "is_dispatch")
    public void setDispatch(boolean dispatch) {
        isDispatch = dispatch;
    }

    @JSONField(name = "keyword_suggested_response")
    public void setKeywordSuggestedResponse(List<KeywordSuggestedResponse> keywordSuggestedResponse) {
        this.keywordSuggestedResponse = keywordSuggestedResponse;
    }

    @JSONField(name = "msg_id")
    public String getMsgId() {
        return msgId;
    }

    public String getExtra() {
        return extra;
    }

    @JSONField(name = "keyword_suggested_response")
    public List<KeywordSuggestedResponse> getKeywordSuggestedResponse() {
        return keywordSuggestedResponse;
    }

    @JSONField(name = "is_dispatch")
    public boolean isDispatch() {
        return isDispatch;
    }
}
