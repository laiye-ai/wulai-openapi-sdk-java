package responseBean.msg;

import java.io.Serializable;

public class HistoryResponse implements Serializable {
    private static final long serialVersionUID = -3796862097397377992L;
    private Object[] msg;
    private boolean hasMore;

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    public void setMsg(Object[] msg) {
        this.msg = msg;
    }

    public Object[] getMsg() {
        return msg;
    }

    public boolean isHasMore() {
        return hasMore;
    }
}
