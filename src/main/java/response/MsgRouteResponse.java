package response;

public class MsgRouteResponse {
    private Boolean isDispatch;
    private Object[] suggestedResponse;

    public void setDispatch(Boolean dispatch) {
        isDispatch = dispatch;
    }

    public Boolean getDispatch() {
        return isDispatch;
    }

    public void setSuggestedResponse(Object[] suggestedResponse) {
        this.suggestedResponse = suggestedResponse;
    }

    public Object[] getSuggestedResponse() {
        return suggestedResponse;
    }
}
