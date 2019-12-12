package com.wulai.dictionary;

import com.DefaultClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateEntityIntentValue {
    private int entityId;
    private List<String> synonyms=new ArrayList<String>();

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

    public void addSynonyms(String synonym){
        synonyms.add(synonym);
    }

    public Map request(DefaultClient defaultClient) throws ServerException, ClientException {
        HashMap<String,Object> params=new HashMap<>();
        params.put("entity_id",entityId);
        params.put("synonyms",synonyms);

        CloseableHttpResponse httpResponse=defaultClient.excuteRequest("/dictionary/entity/intent/value/create",params);
        return defaultClient.getEntityMapFromResponse(httpResponse);

    }

}
