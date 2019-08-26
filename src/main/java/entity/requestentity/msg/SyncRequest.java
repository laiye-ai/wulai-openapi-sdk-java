package entity.requestentity.msg;

import java.io.Serializable;

public class SyncRequest extends BotBase implements Serializable {

    private static final long serialVersionUID = -5803831295883603569L;

    private String msgTs;

    public String getMsgTs() {
        return msgTs;
    }

    public void setMsgTs(String msgTs) {
        this.msgTs = msgTs;
    }


}
