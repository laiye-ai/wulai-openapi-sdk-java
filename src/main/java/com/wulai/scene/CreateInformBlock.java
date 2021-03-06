package com.wulai.scene;

import com.WulaiClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.request.scene.Block;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;


//创建消息发送单元
public class CreateInformBlock {
    private Block block;

    public void setBlock(Block block) {
        this.block = block;
    }

    public Block getBlock() {
        return block;
    }

    public Block request(WulaiClient wulaiClient) throws ServerException, ClientException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("block", block);

        CloseableHttpResponse httpResponse = wulaiClient.executeRequest("/scene/block/inform-block/create", params);

        return wulaiClient.getResponse(httpResponse,Block.class,"block");


    }
}
