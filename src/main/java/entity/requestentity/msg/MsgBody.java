package entity.requestentity.msg;

import java.io.Serializable;

public class MsgBody implements Serializable {
    private static final long serialVersionUID = 5187071021694926225L;

    private Text text;

    public MsgBody(String content){
        text=new Text(content);
    }
    public void setText(Text text) {
        this.text=text;
    }

    public Text getText() {
        return text;
    }
}
