package com.wulai.dictionary;

import com.WulaiClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.request.dictionary.Value;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;

public class DeleteEntityEnumerationValue {

    private int entityId;
    private Value value;

    public void setValue(Value value) {
        this.value = value;
    }

    public int getEntityId() {
        return entityId;
    }


    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public Value getValue() {
        return value;
    }

    public int request(WulaiClient wulaiClient) throws ServerException, ClientException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("entity_id", entityId);
        params.put("value", value);

        CloseableHttpResponse httpResponse = wulaiClient.excuteRequest("/dictionary/entity/enumeration/value/delete", params);
        return httpResponse.getStatusLine().getStatusCode();
    }

}
