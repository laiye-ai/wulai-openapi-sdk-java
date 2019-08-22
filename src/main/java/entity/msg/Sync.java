package entity.msg;

import java.io.Serializable;

public class Sync implements Serializable {

    private static final long serialVersionUID = -5803831295883603569L;

    private Object msgBody;
    private String extra;
    private String userId;
    private String msgTs;

    public Object getMsgBody() {
        return msgBody;
    }

    public void setMsgBody(Object msgBody) {
        this.msgBody = msgBody;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMsgTs() {
        return msgTs;
    }

    public void setMsgTs(String msgTs) {
        this.msgTs = msgTs;
    }


}
