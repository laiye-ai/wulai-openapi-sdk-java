package request.msg;

import exceptions.ClientException;
import util.ParamsCheck;

import java.io.Serializable;

public class ReceiveRequest extends AbstractBotRequest implements Serializable {

    private static final long serialVersionUID = -411243430102290085L;

    private String thirdMsgId;


    public ReceiveRequest(String userId, MsgBody msgBody) throws ClientException {
        super(userId, msgBody);
    }

    public void setThirdMsgId(String thirdMsgId) throws ClientException {
        ParamsCheck.checkThirdMsgId(thirdMsgId);
        this.thirdMsgId = thirdMsgId;
    }

    public String getThirdMsgId() {
        return thirdMsgId;
    }

}
