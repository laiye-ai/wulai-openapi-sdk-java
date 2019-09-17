package module.request.msg;

public class MsgBody {
    private Text text;

    public MsgBody(Text text) {
        this.text = text;
    }

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }

}
