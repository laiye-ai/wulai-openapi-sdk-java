package request.msg;

import exceptions.ClientException;

import java.io.Serializable;

public class BotResponseRequest extends AbstractBotRequest implements Serializable {

    private static final long serialVersionUID = 7088422061413509177L;

    public BotResponseRequest() {
    }

    public BotResponseRequest(String userId, Object msgBody) throws ClientException {
        super(userId, msgBody);
    }


}
