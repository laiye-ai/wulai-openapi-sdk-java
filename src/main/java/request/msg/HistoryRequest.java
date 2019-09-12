package request.msg;

import exceptions.ClientException;
import util.ParamsCheck;

import java.io.Serializable;

public class HistoryRequest implements Serializable {

    private static final long serialVersionUID = -2407514230544187319L;
    private String msgId;
    private String userId;
    private Integer num;
    private Direction direction=Direction.BACKWARD;
    public enum Direction {
        FORWARD, BACKWARD
    }

    public HistoryRequest(String userId,int num) throws ClientException {
        ParamsCheck.checkUserId(userId);
        ParamsCheck.checkNum(num);
        this.userId=userId;
        this.num=num;
    }

    public void setDirection(Direction direction) {
        if (direction!=null) {
            this.direction = Direction.FORWARD;
        }
    }

    public void setMsgId(String msgId) throws ClientException {
        ParamsCheck.checkMsgId(msgId);
        this.msgId = msgId;
    }

    public Direction getDirection() {
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
