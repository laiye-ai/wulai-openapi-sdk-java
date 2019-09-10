package request.msg;

public class Text {
    private String content;

    public void setContent(String content) {
        this.content = content;
    }

    public Text(String content){
        this.content=content;
    }

    public String getContent() {
        return content;
    }
}
