package requestBean.msg;

import exceptions.ClientException;
import util.ParamsCheck;

import java.io.Serializable;

public class ReceiveRequest extends BotBase implements Serializable {

    private static final long serialVersionUID = -411243430102290085L;

    private String thirdMsgId;

    public ReceiveRequest(String thirdMsgId) {
        this.thirdMsgId = thirdMsgId;
    }

    public ReceiveRequest(String userId, Object msgBody, String thirdMsgId) throws ClientException {
        super(userId, msgBody);
        ParamsCheck.checkThirdMsgId(thirdMsgId);
        this.thirdMsgId = thirdMsgId;
    }


    public String getThirdMsgId() {
        return thirdMsgId;
    }

}
