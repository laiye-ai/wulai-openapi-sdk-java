package com.wulai.dictionary;

import com.DefaultClient;
import com.alibaba.fastjson.JSONObject;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.response.dictionary.IntentResponse;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CreateEntityIntentValue {
    private int entityId;
    private List<String> synonyms = new ArrayList<String>();

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public void setSynonyms(List<String> synonyms) {
        this.synonyms = synonyms;
    }

    public List<String> getSynonyms() {
        return synonyms;
    }

    public int getEntityId() {
        return entityId;
    }

    public void addSynonyms(String synonym) {
        synonyms.add(synonym);
    }

    public IntentResponse request(DefaultClient defaultClient) throws ServerException, ClientException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("entity_id", entityId);
        params.put("synonyms", synonyms);

        CloseableHttpResponse httpResponse = defaultClient.excuteRequest("/dictionary/entity/intent/value/create", params);
        JSONObject jsonObject = defaultClient.getJsonFromResponse(httpResponse);
        return JSONObject.parseObject(jsonObject.get("intent_entity").toString(), IntentResponse.class);
    }

}
