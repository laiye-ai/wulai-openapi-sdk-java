package response.msg;

import com.alibaba.fastjson.JSONArray;

import java.io.Serializable;
import java.util.Map;

public class QaResponse extends AbstractBotResponse implements Serializable {
    private static final long serialVersionUID = 1761016338123346254L;
    private Object[] qaSuggestedResponse;

    public QaResponse(Map map){
        super(map);
        setQaSuggestedResponse(JSONArray.parseArray(map.get("qa_suggested_response").toString()).toArray());

    }
    private void setQaSuggestedResponse(Object[] qaSuggestedResponse) {
        this.qaSuggestedResponse = qaSuggestedResponse;
    }

    public Object[] getQaSuggestedResponse() {
        return qaSuggestedResponse;
    }
}
