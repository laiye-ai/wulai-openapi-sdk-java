package response.msg;

public abstract class AbstractBotResponse {
    private boolean isDispatch;
    private String msgId;

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setDispatch(boolean dispatch) {
        isDispatch = dispatch;
    }

    public boolean isDispatch() {
        return isDispatch;
    }
}
