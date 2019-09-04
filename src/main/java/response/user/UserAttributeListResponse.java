package response.user;

public class UserAttributeListResponse {
    private int pageCount;
    private Object[] userAttributeUserAttributeValues;

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setUserAttributeUserAttributeValues(Object[] userAttributeUserAttributeValues) {
        this.userAttributeUserAttributeValues = userAttributeUserAttributeValues;
    }

    public Object[] getUserAttributeUserAttributeValues() {
        return userAttributeUserAttributeValues;
    }
}
