package response.msg;

import java.io.Serializable;

public class BotResponse extends AbstractBotResponse implements Serializable {
    private static final long serialVersionUID = 1133829771341011507L;


    private Object[] suggestedResponse;

    public Object[] getSuggestedResponse() {
        return suggestedResponse;
    }

    public void setSuggestedResponse(Object[] suggestedResponse)
    {
        this.suggestedResponse = suggestedResponse;
    }
}
