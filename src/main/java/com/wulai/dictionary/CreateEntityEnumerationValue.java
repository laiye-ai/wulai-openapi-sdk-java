package com.wulai.dictionary;

import com.WulaiClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.request.dictionary.EnumEntity;
import com.module.request.dictionary.Value;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;

public class CreateEntityEnumerationValue {
    private int entityId;
    private Value value;

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public int getEntityId() {
        return entityId;
    }

    public Object getValue() {
        return value;
    }

    public EnumEntity request(WulaiClient wulaiClient) throws ServerException, ClientException {
        HashMap<String, Object> params = new HashMap<>();

        params.put("entity_id", entityId);
        params.put("value", value);

        CloseableHttpResponse httpResponse = wulaiClient.executeRequest("/dictionary/entity/enumeration/value/create", params);

        return wulaiClient.getResponse(httpResponse,EnumEntity.class,"enum_entity");


    }


}
