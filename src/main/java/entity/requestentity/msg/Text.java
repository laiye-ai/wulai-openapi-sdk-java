package entity.msg;

import java.io.Serializable;

public class Text  implements Serializable {

    private static final long serialVersionUID = 9180334129402054714L;
    private String content;

    public Text(String content){
        this.content=content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
