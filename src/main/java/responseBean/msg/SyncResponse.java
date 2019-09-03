package responseBean.msg;

import java.io.Serializable;

public class SyncResponse implements Serializable {
    private static final long serialVersionUID = -3926486440053993536L;
    private String msgId;

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getMsgId() {
        return msgId;
    }
}
