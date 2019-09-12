package response.msg;

import com.alibaba.fastjson.JSONArray;

import java.io.Serializable;
import java.util.Map;

public class KeywordResponse extends AbstractBotResponse implements Serializable {
    private static final long serialVersionUID = -3572603159680660431L;
    private Object[] keywordSuggestedResponse;

    public KeywordResponse(Map map) {
        super(map);
        setKeywordSuggestedResponse(JSONArray.parseArray(map.get("keyword_suggested_response")
                .toString()).toArray());
    }

    public Object[] getKeywordSuggestedResponse() {
        return keywordSuggestedResponse;
    }

    private void setKeywordSuggestedResponse(Object[] suggestedResponse) {
        this.keywordSuggestedResponse = suggestedResponse;
    }

}
