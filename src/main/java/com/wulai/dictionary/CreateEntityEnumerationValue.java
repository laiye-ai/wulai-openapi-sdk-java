package com.wulai.dictionary;

import com.DefaultClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.request.dictionary.Value;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;
import java.util.Map;

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

    public Map request(DefaultClient defaultClient) throws ServerException, ClientException {
        HashMap<String,Object> params=new HashMap<>();

        params.put("entity_id",entityId);
        params.put("value",value);

        CloseableHttpResponse httpResponse=defaultClient.excuteRequest("/dictionary/entity/enumeration/value/create",params);
        return defaultClient.getEntityMapFromResponse(httpResponse);


    }




}
