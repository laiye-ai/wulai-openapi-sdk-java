package com.module.response.knowledge;

import com.alibaba.fastjson.annotation.JSONField;
import com.module.request.knowledge.UserAttributeGroupItem;

import java.util.List;

public class UserAttributeGroupItemsList {
    private int pageCount;

    private List<UserAttributeGroupItem> userAttributeGroupItems;


    @JSONField(name = "user_attribute_group_items")
    public void setUserAttributeGroupItems(List<UserAttributeGroupItem> userAttributeGroupItems) {
        this.userAttributeGroupItems = userAttributeGroupItems;
    }

    @JSONField(name = "user_attribute_group_items")
    public List<UserAttributeGroupItem> getUserAttributeGroupItems() {
        return userAttributeGroupItems;
    }

    @JSONField(name = "page_count")
    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    @JSONField(name = "page_count")
    public int getPageCount() {
        return pageCount;
    }

}
