package requestentity.msg;

import exceptions.ClientException;

import java.io.Serializable;

public class BotResponseKeywordRequest  extends BotBase implements Serializable {

    private static final long serialVersionUID = 5724067953229238500L;

    public BotResponseKeywordRequest() {
    }

    public BotResponseKeywordRequest(String userId, Object msgBody) throws ClientException {
        super(userId, msgBody);
    }

    public BotResponseKeywordRequest(String userId, Object msgBody, String extra) throws ClientException {
        super(userId, msgBody, extra);
    }
}
