package com.wulai.scene;

import com.DefaultClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;
import java.util.Map;

public class QuerySlotList {
    private int scene_id;
    private int page;
    private int page_size;

    public void setScene_id(int scene_id) {
        this.scene_id = scene_id;
    }

    public int getScene_id() {
        return scene_id;
    }

    public void setPage_size(int page_size) {
        this.page_size = page_size;
    }

    public int getPage_size() {
        return page_size;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPage() {
        return page;
    }

    public Map request(DefaultClient defaultClient) throws ServerException, ClientException {
        HashMap<String,Object> params=new HashMap<>();
        params.put("scene_id",scene_id);
        params.put("page",page);
        params.put("page_size",page_size);

        CloseableHttpResponse httpResponse=defaultClient.excuteRequest("/scene/slot/list",params);
        return  defaultClient.getEntityMapFromResponse(httpResponse);


    }


}
