package requestentity.msg;

import exceptions.ClientException;
import util.ParamsCheck;

import java.io.Serializable;

public abstract class BotBase implements Serializable {

    private static final long serialVersionUID = -6175041688453020736L;

    private Object msgBody;
    private String userId;
    private String extra;

    BotBase(){}

    BotBase(String userId, Object msgBody) throws ClientException {
        ParamsCheck.checkUserId(userId);
        this.userId=userId;
        this.msgBody=msgBody;
    }

    BotBase(String userId, Object msgBody, String extra) throws ClientException {
        ParamsCheck.checkUserId(userId);
        ParamsCheck.checkExtra(extra);
        this.userId=userId;
        this.msgBody=msgBody;
        this.extra=extra;
    }

    public Object getMsgBody() {
        return msgBody;
    }

    public void setMsgBody(String content) {
        msgBody = new MsgBody(content);
    }

    public String getUserId() {
        return userId;
    }

    public String getExtra() {
        return extra;
    }

}
