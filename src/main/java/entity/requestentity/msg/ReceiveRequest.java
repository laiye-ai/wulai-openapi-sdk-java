package entity.requestentity.msg;

import java.io.Serializable;

public class ReceiveRequest extends BotBase implements Serializable {

    private static final long serialVersionUID = -411243430102290085L;

    private String thirdMsgId;

    public void setThirdMsgId(String thirdMsgId) {
        this.thirdMsgId = thirdMsgId;
    }

    public String getThirdMsgId() {
        return thirdMsgId;
    }

}
