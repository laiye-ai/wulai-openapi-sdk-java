package com.wulai.scene;

import com.WulaiClient;
import com.alibaba.fastjson.annotation.JSONField;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.response.scene.DataSource;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;


//创建词槽数据来源
public class CreateSlotDataSource {
    private DataSource dataSource;

    @JSONField(name = "data_source")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @JSONField(name = "data_source")
    public DataSource getDataSource() {
        return dataSource;
    }

    public DataSource request(WulaiClient wulaiClient) throws ServerException, ClientException {
        HashMap<String,Object> params=new HashMap<>();
        params.put("data_source",dataSource);
        CloseableHttpResponse httpResponse=wulaiClient.executeRequest("/scene/slot/data-source/create",params);
        return wulaiClient.getResponse(httpResponse,DataSource.class);
    }
}
