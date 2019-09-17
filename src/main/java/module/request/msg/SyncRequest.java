package module.request.msg;

import exceptions.ClientException;
import util.ParamsCheck;

import java.io.Serializable;


public class SyncRequest extends AbstractBotRequest implements Serializable {

    private static final long serialVersionUID = -5803831295883603569L;

    private String msgTs;

    public SyncRequest(String userId, MsgBody msgBody, String msgTs) throws ClientException {
        super(userId, msgBody);
        ParamsCheck.checkMsgTs(msgTs.trim());
        this.msgTs = msgTs.trim();
    }

    public String getMsgTs() {
        return msgTs;
    }


}
