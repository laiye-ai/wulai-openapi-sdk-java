package com.module.request.msg;

import com.exceptions.ClientException;
import com.module.request.MsgBody;
import com.util.ParamsCheck;

import java.io.Serializable;

public class ReceiveRequest extends AbstractBotRequest implements Serializable {

    private static final long serialVersionUID = -411243430102290085L;

    private String thirdMsgId;


    public ReceiveRequest(String userId, MsgBody msgBody) throws ClientException {
        super(userId, msgBody);
    }

    public String getThirdMsgId() {
        return thirdMsgId;
    }

    public void setThirdMsgId(String thirdMsgId) throws ClientException {
        ParamsCheck.checkThirdMsgId(thirdMsgId);
        this.thirdMsgId = thirdMsgId;
    }

}
