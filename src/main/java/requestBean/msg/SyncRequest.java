package requestBean.msg;

import exceptions.ClientException;

import java.io.Serializable;

public class SyncRequest extends BotBase implements Serializable {

    private static final long serialVersionUID = -5803831295883603569L;

    private String msgTs;

    public String getMsgTs() {
        return msgTs;
    }


    public SyncRequest(String userId, Object msgBody, String msgTs) throws ClientException {
        super(userId, msgBody);
        if (null==msgTs||msgTs.length()<1){
            throw new ClientException("","msgTs 时间戳字段长度须大于1");
        }
        this.msgTs = msgTs;
    }


}
