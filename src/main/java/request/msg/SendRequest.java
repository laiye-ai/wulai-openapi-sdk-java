package request.msg;

import exceptions.ClientException;

import java.io.Serializable;

@Deprecated
public class SendRequest extends AbstractBotRequest implements Serializable {

    private static final long serialVersionUID = 1353424979198565159L;

    private Object[] similarResponse;
    private String[] quickReply;


    //构造方法
    public SendRequest(String userId, Object msgBody) throws ClientException {
        super(userId, msgBody);
    }

    public void setQuickReply(String[] quickReply) {
        this.quickReply = quickReply;
    }

    public void setSimilarResponse(Object[] similarResponse) {
        this.similarResponse = similarResponse;
    }

    public Object[] getSimilarResponse() {
        return similarResponse;
    }

    public String[] getQuickReply() {
        return quickReply;
    }

}
