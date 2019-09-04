package response.msg;

import java.io.Serializable;

public class TaskResponse extends AbstractBotResponse implements Serializable {

    private static final long serialVersionUID = -3723078169848220650L;
    private Object[] taskSuggestedResponse;


    public void setTaskSuggestedResponse(Object[] taskSuggestedResponse) {
        this.taskSuggestedResponse = taskSuggestedResponse;
    }

    public Object[] getTaskSuggestedResponse() {
        return taskSuggestedResponse;
    }
}
