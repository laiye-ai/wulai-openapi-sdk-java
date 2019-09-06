package request.user;
import exceptions.ClientException;
import util.ParamsCheck;

import java.io.Serializable;

public class UserAttributeListRequest implements Serializable {

    private static final long serialVersionUID = 4562315503675971054L;

    private Object filter;
    private Integer page;
    private Integer pageSize;

    private UserAttributeListRequest(){}

    public UserAttributeListRequest(Integer page, Integer pageSize) throws ClientException {
        ParamsCheck.checkPage(page);
        ParamsCheck.checkPageSize(pageSize);
        this.page=page;
        this.pageSize=pageSize;
    }

    public void setFilter(Object filter) {
        if (filter instanceof Boolean) {
            this.filter = filter;
        }
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
