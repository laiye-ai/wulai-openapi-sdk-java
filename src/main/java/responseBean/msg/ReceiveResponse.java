package responseBean.msg;

import java.io.Serializable;

public class ReceiveResponse implements Serializable {
    private static final long serialVersionUID = -7873376951237664553L;

    private String msg_id;

    public void setMsg_id(String msg_id) {
        this.msg_id = msg_id;
    }

    public String getMsg_id() {
        return msg_id;
    }
}
