package request.msg;

import exceptions.ClientException;
import util.ParamsCheck;

import java.io.Serializable;

public abstract class AbstractBotRequest implements Serializable {

    private static final long serialVersionUID = -6175041688453020736L;

    private Object msgBody;
    private String userId;
    private String extra;

    protected AbstractBotRequest(){}

    protected AbstractBotRequest(String userId, Object msgBody) throws ClientException {
        ParamsCheck.checkUserId(userId);
        this.userId=userId;
        this.msgBody=msgBody;
    }
    public void setMsgBody(Object msgBody){
        this.msgBody=msgBody;
    }

    public Object getMsgBody() {
        return msgBody;
    }

    public String getUserId() {
        return userId;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) throws ClientException {
        ParamsCheck.checkExtra(extra);
        this.extra = extra;
    }
}
