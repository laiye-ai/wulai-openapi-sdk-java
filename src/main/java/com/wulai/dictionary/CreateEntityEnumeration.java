package com.wulai.dictionary;

import com.WulaiClient;
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


    public EnumEntity request(WulaiClient wulaiClient) throws ServerException, ClientException {
        HashMap<String, Object> params = new HashMap<>();

        params.put("enum_entity", enumEntity);

        CloseableHttpResponse httpResponse = wulaiClient.excuteRequest("/dictionary/entity/enumeration/create", params);

        return wulaiClient.getResponse(httpResponse,EnumEntity.class,"enum_entity");

    }
}
