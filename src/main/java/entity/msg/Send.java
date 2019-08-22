package entity.msg;

import java.io.Serializable;

public class Send implements Serializable {

    private static final long serialVersionUID = 1353424979198565159L;

    private Object[] similarResponse;
    private Object msgBody;
    private String[] quickReply;
    private String userId;
    private String extra;

    public Object[] getSimilarResponse() {
        return similarResponse;
    }

    public void setSimilarResponse(Object[] similarResponse) {
        this.similarResponse = similarResponse;
    }

    public Object getMsgBody() {
        return msgBody;
    }

    public void setMsgBody(Object msgBody) {
        this.msgBody = msgBody;
    }

    public String[] getQuickReply() {
        return quickReply;
    }

    public void setQuickReply(String[] quickReply) {
        this.quickReply = quickReply;
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
