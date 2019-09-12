package response.msg;

import com.alibaba.fastjson.JSONArray;

import java.io.Serializable;
import java.util.Map;

public class TaskResponse extends AbstractBotResponse implements Serializable {

    private static final long serialVersionUID = -3723078169848220650L;
    private Object[] taskSuggestedResponse;

    public TaskResponse(Map map) {
        super(map);
        setTaskSuggestedResponse(JSONArray.parseArray(map.get("task_suggested_response")
                .toString()).toArray());
    }

    public Object[] getTaskSuggestedResponse() {
        return taskSuggestedResponse;
    }

    private void setTaskSuggestedResponse(Object[] taskSuggestedResponse) {
        this.taskSuggestedResponse = taskSuggestedResponse;
    }
}
