package requestentity.msg;

import java.awt.*;
import java.io.Serializable;

public class MsgBody implements Serializable {
    private static final long serialVersionUID = 5187071021694926225L;

    private Object object;
    private Text text;
    private Image image;
    public MsgBody(Object object){
       if (object instanceof Text){
       }
    }

    public Object getObject() {
        return object;
    }
}
