package com.wulai.scene;

import com.DefaultClient;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.request.scene.Slot;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuerySlotList {
    private int sceneId;
    private int page;
    private int pageSize;

    @JSONField(name = "scene_id")
    public void setSceneId(int sceneId) {
        this.sceneId = sceneId;
    }

    @JSONField(name = "scene_id")
    public int getSceneId() {
        return sceneId;
    }


    @JSONField(name = "page_size")
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @JSONField(name = "page_size")
    public int getPageSize() {
        return pageSize;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPage() {
        return page;
    }

    public List<Slot> request(DefaultClient defaultClient) throws ServerException, ClientException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("scene_id", sceneId);
        params.put("page", page);
        params.put("page_size", pageSize);

        CloseableHttpResponse httpResponse = defaultClient.excuteRequest("/scene/slot/list", params);
        JSONObject jsonObject = defaultClient.getJsonFromResponse(httpResponse);
        return JSONObject.parseArray(jsonObject.get("slots").toString(), Slot.class);


    }


}
