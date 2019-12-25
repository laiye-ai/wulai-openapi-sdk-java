package com.module.response.msg;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class TaskResponse {



    private boolean isDispatch;
    private String msgId;
    private String extra;
    private List<TaskSuggestedResponse> taskSuggestedResponse;

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getExtra() {
        return extra;
    }

    @JSONField(name = "is_dispatch")
    public void setDispatch(boolean dispatch) {
        isDispatch = dispatch;
    }

    @JSONField(name = "is_dispatch")
    public boolean isDispatch() {
        return isDispatch;
    }

    @JSONField(name = "task_suggested_response")
    public void setTaskSuggestedResponse(List<TaskSuggestedResponse> taskSuggestedResponse) {
        this.taskSuggestedResponse = taskSuggestedResponse;
    }

    @JSONField(name = "task_suggested_response")
    public List<TaskSuggestedResponse> getTaskSuggestedResponse() {
        return taskSuggestedResponse;
    }

    @JSONField(name = "msg_id")
    public String getMsgId() {
        return msgId;
    }

    @JSONField(name = "msg_id")
    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }


}
