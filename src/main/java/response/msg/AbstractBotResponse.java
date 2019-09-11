package response.msg;

import com.alibaba.fastjson.JSONArray;

import java.util.Map;

public abstract class AbstractBotResponse {
    private boolean isDispatch;
    private String msgId;

    AbstractBotResponse(Map map){
        setDispatch((boolean)map.get("is_dispatch"));
        setMsgId((String)map.get("msg_id"));
    }
    private void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getMsgId() {
        return msgId;
    }

    private void setDispatch(boolean dispatch) {
        isDispatch = dispatch;
    }

    public boolean isDispatch() {
        return isDispatch;
    }
}
