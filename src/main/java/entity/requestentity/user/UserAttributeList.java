package entity.user;

import java.io.Serializable;

public class UserAttributeList implements Serializable {

    private static final long serialVersionUID = 4562315503675971054L;

    private Object filter;
    private int page;
    private int pageSize;

    public Object getFilter() {
        return filter;
    }

    public void setFilter(Object filter) {
        this.filter = filter;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
