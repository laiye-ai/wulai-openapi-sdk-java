package requestentity.msg;

import exceptions.ClientException;

import java.io.Serializable;

public class BotResponse extends BotBase implements Serializable {



    private BotResponse() {
    }

    public BotResponse(String userId, Object msgBody) throws ClientException {
        super(userId, msgBody);
    }

    public BotResponse(String userId, Object msgBody, String extra) throws ClientException {
        super(userId, msgBody, extra);
    }
}
