package requestentity.user;
import exceptions.ClientException;
import util.ParamsCheck;

import java.io.Serializable;

public class UserAttributeList implements Serializable {

    private static final long serialVersionUID = 4562315503675971054L;

    private Object filter;
    private int page;
    private int pageSize;

    private UserAttributeList(){}

    public UserAttributeList(int page,int pageSize) throws ClientException {
        ParamsCheck.checkPage(page);
        ParamsCheck.checkPageSize(pageSize);
        this.page=page;
        this.pageSize=pageSize;
    }

    public UserAttributeList(int page,int pageSize,Object filter) throws ClientException {
        ParamsCheck.checkPage(page);
        ParamsCheck.checkPageSize(pageSize);
        this.page=page;
        this.pageSize=pageSize;
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
