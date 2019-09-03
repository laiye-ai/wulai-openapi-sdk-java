package responseBean.msg;

public abstract class BotResponseBase{
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
