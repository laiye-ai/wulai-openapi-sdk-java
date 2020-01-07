package com.module.response.msg;

import com.alibaba.fastjson.annotation.JSONField;
import com.module.request.msg.MsgBody;

public class Msg {

    private String direction;
    private SenderInfo senderInfo;
    private String msgType;
    private String extra;
    private String msgID;
    private String msgTs;
    private UserInfo userInfo;
    private MsgBody msgBody;

    @JSONField(name = "direction")
    public String getDirection() {
        return direction;
    }

    @JSONField(name = "direction")
    public void setDirection(String value) {
        this.direction = value;
    }

    @JSONField(name = "sender_info")
    public SenderInfo getSenderInfo() {
        return senderInfo;
    }

    @JSONField(name = "sender_info")
    public void setSenderInfo(SenderInfo value) {
        this.senderInfo = value;
    }

    @JSONField(name = "msg_type")
    public String getMsgType() {
        return msgType;
    }

    @JSONField(name = "msg_type")
    public void setMsgType(String value) {
        this.msgType = value;
    }

    @JSONField(name = "extra")
    public String getExtra() {
        return extra;
    }

    @JSONField(name = "extra")
    public void setExtra(String value) {
        this.extra = value;
    }

    @JSONField(name = "msg_id")
    public String getMsgID() {
        return msgID;
    }

    @JSONField(name = "msg_id")
    public void setMsgID(String value) {
        this.msgID = value;
    }

    @JSONField(name = "msg_ts")
    public String getMsgTs() {
        return msgTs;
    }

    @JSONField(name = "msg_ts")
    public void setMsgTs(String value) {
        this.msgTs = value;
    }

    @JSONField(name = "user_info")
    public UserInfo getUserInfo() {
        return userInfo;
    }

    @JSONField(name = "user_info")
    public void setUserInfo(UserInfo value) {
        this.userInfo = value;
    }

    @JSONField(name = "msg_body")
    public MsgBody getMsgBody() {
        return msgBody;
    }

    @JSONField(name = "msg_body")
    public void setMsgBody(MsgBody value) {
        this.msgBody = value;
    }
}
