package response.msg;

import com.alibaba.fastjson.JSONArray;

import java.io.Serializable;
import java.util.Map;

public class BotResponse extends AbstractBotResponse implements Serializable {
    private static final long serialVersionUID = 1133829771341011507L;
    private Object[] suggestedResponse;

    public BotResponse(Map map) {
        super(map);
        setSuggestedResponse(JSONArray.parseArray(map.get("suggested_response").toString()).toArray());
    }

    public Object[] getSuggestedResponse() {
        return suggestedResponse;
    }

    private void setSuggestedResponse(Object[] suggestedResponse) {
        this.suggestedResponse = suggestedResponse;
    }
}
