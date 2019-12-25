package com.wulai.dictionary;

import com.DefaultClient;
import com.alibaba.fastjson.JSONObject;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.request.dictionary.EnumEntity;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;

public class CreateEntityEnumeration {
    private EnumEntity enumEntity;

    public void setEnumEntity(EnumEntity enumEntity) {
        this.enumEntity = enumEntity;
    }

    public EnumEntity getEnumEntity() {
        return enumEntity;
    }


    public EnumEntity request(DefaultClient defaultClient) throws ServerException, ClientException {
        HashMap<String, Object> params = new HashMap<>();

        params.put("enum_entity", enumEntity);

        CloseableHttpResponse httpResponse = defaultClient.excuteRequest("/dictionary/entity/enumeration/create", params);
        JSONObject jsonObject = defaultClient.getJsonFromResponse(httpResponse);
        return JSONObject.parseObject(jsonObject.get("enum_entity").toString(), EnumEntity.class);

    }
}
