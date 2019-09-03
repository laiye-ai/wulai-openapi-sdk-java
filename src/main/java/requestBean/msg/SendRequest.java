package requestBean.msg;

import exceptions.ClientException;

import java.io.Serializable;

public class SendRequest extends BotBase implements Serializable {

    private static final long serialVersionUID = 1353424979198565159L;

    private Object[] similarResponse;
    private String[] quickReply;


    //构造方法
    public SendRequest(String userId, Object msgBody) throws ClientException {

        super(userId, msgBody);
    }

    public SendRequest(String userId, Object msgBody,Object[] similarResponse) throws ClientException {
        super(userId, msgBody);
    }

    public SendRequest(String userId, Object msgBody, String[] quickReply) throws ClientException {
        super(userId, msgBody);
    }
    public SendRequest(String userId, Object msgBody, Object[] similarResponse, String[] quickReply) throws ClientException {
        super(userId, msgBody);
        this.similarResponse = similarResponse;
        this.quickReply = quickReply;
    }


    public Object[] getSimilarResponse() {
        return similarResponse;
    }

    public String[] getQuickReply() {
        return quickReply;
    }

}
