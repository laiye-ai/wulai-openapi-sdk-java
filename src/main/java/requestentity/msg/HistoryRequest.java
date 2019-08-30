package requestentity.msg;

import exceptions.ClientException;
import util.ParamsCheck;

import java.io.Serializable;

public class HistoryRequest implements Serializable {

    private static final long serialVersionUID = -2407514230544187319L;

    private String direction;
    private String msgId;
    private String userId;
    private int num;

    private HistoryRequest(){ }

    //构造方法
    public HistoryRequest(String userId,int num) throws ClientException {
        ParamsCheck.checkUserId(userId);
        ParamsCheck.checkNum(num);
        this.userId=userId;
        this.num=num;
    }


    public HistoryRequest(String userId,int num,String msgId) throws ClientException {
        ParamsCheck.checkUserId(userId);
        ParamsCheck.checkNum(num);
        ParamsCheck.checkMsgId(msgId);
        this.userId=userId;
        this.num=num;
        this.msgId=msgId;
    }
    public HistoryRequest(String userId,int num,String msgId,String direction) throws ClientException {
        ParamsCheck.checkUserId(userId);
        ParamsCheck.checkMsgId(msgId);
        ParamsCheck.checkNum(num);
        ParamsCheck.checkDirection(direction);
        this.userId=userId;
        this.num=num;
        this.msgId=msgId;
        this.direction=direction;
    }


    public String getDirection() {
        return direction;
    }

    public String getMsgId() {
        return msgId;
    }


    public String getUserId() {
        return userId;
    }


    public int getNum() {
        return num;
    }


}
