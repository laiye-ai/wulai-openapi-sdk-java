package responseBean.msg;

import java.io.Serializable;

public class QaResponse extends BotResponseBase implements Serializable {
    private static final long serialVersionUID = 1761016338123346254L;
    private Object[] qaSuggestedResponse;

    public void setQaSuggestedResponse(Object[] qaSuggestedResponse) {
        this.qaSuggestedResponse = qaSuggestedResponse;
    }

    public Object[] getQaSuggestedResponse() {
        return qaSuggestedResponse;
    }
}
