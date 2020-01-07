package com.wulai.scene;

import com.WulaiClient;
import com.alibaba.fastjson.annotation.JSONField;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.request.scene.Block;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;
import java.util.List;

public class QueryBlockList {

    private int intent_id;
    private int page;
    private int pageSize;

    public void setPage(int page) {
        this.page = page;
    }

    public int getPage() {
        return page;
    }

    public void setIntent_id(int intent_id) {
        this.intent_id = intent_id;
    }

    public int getIntent_id() {
        return intent_id;
    }

    @JSONField(name = "page_size")
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @JSONField(name = "page_size")
    public int getPageSize() {
        return pageSize;
    }

    public List<Block> request(WulaiClient wulaiClient) throws ServerException, ClientException {
        HashMap<String,Object> params=new HashMap<>();
        params.put("intent_id",intent_id);
        params.put("page",page);
        params.put("page_size",pageSize);

        CloseableHttpResponse httpResponse = wulaiClient.executeRequest("/scene/block/list",params);
        return wulaiClient.getResponseArray(httpResponse,Block.class,"blocks");

    }

}
