package com.wulai.dictionary;

import com.DefaultClient;
import com.alibaba.fastjson.JSONObject;
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

    public EnumEntity request(DefaultClient defaultClient) throws ServerException, ClientException {
        HashMap<String, Object> params = new HashMap<>();

        params.put("entity_id", entityId);
        params.put("value", value);

        CloseableHttpResponse httpResponse = defaultClient.excuteRequest("/dictionary/entity/enumeration/value/create", params);
        JSONObject jsonObject = defaultClient.getJsonFromResponse(httpResponse);

        return JSONObject.parseObject(jsonObject.get("enum_entity").toString(), EnumEntity.class);


    }


}
