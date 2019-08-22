package entity.msg;

import java.io.Serializable;

public class BotResponse implements Serializable {

    private static final long serialVersionUID = 7088422061413509177L;
    private Object msgBody;
    private String userId;
    private String extra;

    public Object getMsgBody() {
        return msgBody;
    }

    public void setMsgBody(Object msgBody) {
        this.msgBody = msgBody;
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
