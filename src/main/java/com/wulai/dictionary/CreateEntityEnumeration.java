package com.wulai.dictionary;

import com.DefaultClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.request.EnumEntity;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;
import java.util.Map;

public class CreateEntityEnumeration {
    private EnumEntity enumEntity;

    public void setEnumEntity(EnumEntity enumEntity) {
        this.enumEntity = enumEntity;
    }

    public EnumEntity getEnumEntity() {
        return enumEntity;
    }


    public Map request(DefaultClient defaultClient) throws ServerException, ClientException {
        HashMap<String,Object> params=new HashMap<>();

        params.put("enum_entity",enumEntity);

        CloseableHttpResponse httpResponse=defaultClient.excuteRequest("/dictionary/entity/enumeration/create",params);
        return defaultClient.getEntityMapFromResponse(httpResponse);



    }
}
