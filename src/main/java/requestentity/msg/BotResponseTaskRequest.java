package requestentity.msg;

import exceptions.ClientException;

import java.io.Serializable;

public class BotResponseTaskRequest extends BotBase implements Serializable {

    private static final long serialVersionUID = 8164286816326072999L;

    public BotResponseTaskRequest() {
    }

    public BotResponseTaskRequest(String userId, Object msgBody) throws ClientException {
        super(userId, msgBody);
    }

    public BotResponseTaskRequest(String userId, Object msgBody, String extra) throws ClientException {
        super(userId, msgBody, extra);
    }
}
