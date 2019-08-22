package entity.msg;

import java.io.Serializable;

public class History implements Serializable {

    private static final long serialVersionUID = -2407514230544187319L;

    private String direction;
    private String msgId;
    private String userId;
    private int num;

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
