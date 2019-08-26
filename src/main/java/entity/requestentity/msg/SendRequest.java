package entity.msg;

import java.io.Serializable;

public class SendRequest extends BotBase implements Serializable {

    private static final long serialVersionUID = 1353424979198565159L;

    private Object[] similarResponse;
    private String[] quickReply;

    public Object[] getSimilarResponse() {
        return similarResponse;
    }

    public void setSimilarResponse(Object[] similarResponse) {
        this.similarResponse = similarResponse;
    }

    public String[] getQuickReply() {
        return quickReply;
    }

    public void setQuickReply(String[] quickReply) {
        this.quickReply = quickReply;
    }
}
