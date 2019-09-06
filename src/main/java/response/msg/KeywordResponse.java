package response.msg;

import java.io.Serializable;

public class KeywordResponse extends AbstractBotResponse implements Serializable {
    private static final long serialVersionUID = -3572603159680660431L;
    private Object[] keywordSuggestedResponse;

    public Object[] getKeywordSuggestedResponse() {
        return keywordSuggestedResponse;
    }

    public void setKeywordSuggestedResponse(Object[] suggestedResponse) {
        this.keywordSuggestedResponse = suggestedResponse;
    }

}
