package response.user;

import com.alibaba.fastjson.JSONArray;

import java.util.Map;

public class UserAttributeListResponse {
    private int pageCount;
    private Object[] userAttributeUserAttributeValues;

    public UserAttributeListResponse(Map map) {
        setPageCount((int) map.get("page_count"));
        setUserAttributeUserAttributeValues(JSONArray.
                parseArray(map.get("user_attribute_user_attribute_values").toString()).toArray());
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public Object[] getUserAttributeUserAttributeValues() {
        return userAttributeUserAttributeValues;
    }

    public void setUserAttributeUserAttributeValues(Object[] userAttributeUserAttributeValues) {
        this.userAttributeUserAttributeValues = userAttributeUserAttributeValues;
    }
}
