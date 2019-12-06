package com.wulai.knowledge;

import com.DefaultClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;

import java.util.HashMap;

public class DeleteKnowledge {
    private int id ;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int request(DefaultClient defaultClient) throws ServerException, ClientException {
        HashMap<String ,Object> params=new HashMap<>();
        params.put("id",id);

        return defaultClient.excuteRequest("/qa/knowledge/delete",params).getStatusLine().getStatusCode();

    }

}
