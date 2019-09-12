package response.msg;

import java.util.Map;

public abstract class AbstractBotResponse {
    private boolean isDispatch;
    private String msgId;

    AbstractBotResponse(Map map) {
        setDispatch((boolean) map.get("is_dispatch"));
        setMsgId((String) map.get("msg_id"));
    }

    public String getMsgId() {
        return msgId;
    }

    private void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public boolean isDispatch() {
        return isDispatch;
    }

    private void setDispatch(boolean dispatch) {
        isDispatch = dispatch;
    }
}
