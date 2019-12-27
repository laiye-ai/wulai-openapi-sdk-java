package com.wulai.dictionary;

import com.WulaiClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DeleteEntityIntentValue {
    private int entityId;

    private List<String> synonyms = new ArrayList<>();

    public void setSynonyms(List<String> synonyms) {
        this.synonyms = synonyms;
    }

    public List<String> getSynonyms() {
        return synonyms;
    }

    public void addSynonym(String synonym) {
        synonyms.add(synonym);
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public int getEntityId() {
        return entityId;
    }

    public int request(WulaiClient wulaiClient) throws ServerException, ClientException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("entity_id", entityId);
        params.put("synonyms", synonyms);

        CloseableHttpResponse httpResponse = wulaiClient.excuteRequest("/dictionary/entity/intent/value/delete", params);
        return httpResponse.getStatusLine().getStatusCode();

    }

}
