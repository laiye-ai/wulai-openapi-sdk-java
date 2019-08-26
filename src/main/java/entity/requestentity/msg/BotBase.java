package entity.requestentity.msg;

import java.io.Serializable;

public abstract class BotBase implements Serializable {

    private static final long serialVersionUID = -6175041688453020736L;

    private MsgBody msgBody;
    private String userId;
    private String extra;


    public MsgBody getMsgBody() {
        return msgBody;
    }

    public void setMsgBody(String content) {
        msgBody = new MsgBody(content);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }



}
