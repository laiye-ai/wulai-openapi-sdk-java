package com.wulai.scene;

import com.DefaultClient;
import com.alibaba.fastjson.JSONObject;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.request.scene.Block;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;

public class CreateBlockRequestBlock {
    private Block block;

    public void setBlock(Block block) {
        this.block = block;
    }

    public Block getBlock() {
        return block;
    }

    public Block request(DefaultClient defaultClient) throws ServerException, ClientException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("block", block);

        CloseableHttpResponse httpResponse = defaultClient.excuteRequest("/scene/block/request-block/create", params);
        JSONObject jsonObject = defaultClient.getJsonFromResponse(httpResponse);

        return JSONObject.parseObject(jsonObject.get("block").toString(), Block.class);

    }

}
