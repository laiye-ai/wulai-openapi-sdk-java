package requestBean.user;
import exceptions.ClientException;
import util.ParamsCheck;

import java.io.Serializable;

public class UserAttributeListRequest implements Serializable {

    private static final long serialVersionUID = 4562315503675971054L;

    private Object filter;
    private int page;
    private int pageSize;

    private UserAttributeListRequest(){}

    public UserAttributeListRequest(int page, int pageSize) throws ClientException {
        ParamsCheck.checkPage(page);
        ParamsCheck.checkPageSize(pageSize);
        this.page=page;
        this.pageSize=pageSize;
    }

    public void setFilter(Object filter) {
        this.filter = filter;
    }

    public Object getFilter() {
        return filter;
    }

    public int getPage() {
        return page;
    }

    public int getPageSize() {
        return pageSize;
    }
}
