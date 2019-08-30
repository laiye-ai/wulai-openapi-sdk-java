package requestentity.msg;

import exceptions.ClientException;

import java.io.Serializable;

public class BotResponseQaRequest extends BotBase implements Serializable {

    private static final long serialVersionUID = -7291915530803933028L;

    public BotResponseQaRequest() {
    }

    public BotResponseQaRequest(String userId, Object msgBody) throws ClientException {
        super(userId, msgBody);
    }

    public BotResponseQaRequest(String userId, Object msgBody, String extra) throws ClientException {
        super(userId, msgBody, extra);
    }
}
