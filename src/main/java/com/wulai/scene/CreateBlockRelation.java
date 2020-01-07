package com.wulai.scene;

import com.WulaiClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.request.scene.Relation;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;

public class CreateBlockRelation {
    private Relation relation;

    public void setRelation(Relation relation) {
        this.relation = relation;
    }

    public Relation getRelation() {
        return relation;
    }

    public Relation request(WulaiClient wulaiClient) throws ServerException, ClientException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("relation", relation);

        CloseableHttpResponse httpResponse = wulaiClient.executeRequest("/scene/block/relation/create", params);
        return wulaiClient.getResponse(httpResponse, Relation.class,"relation");

    }
}
