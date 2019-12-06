package com.wulai.stats;

import com.DefaultClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;
import java.util.Map;

public class QueryQaRecallDailyList {
    private String start_date;
    private String end_date;

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public String getStart_date() {
        return start_date;
    }

    public Map request(DefaultClient defaultClient) throws ServerException, ClientException {
        HashMap<String,Object> params =new HashMap<>();
        params.put("start_date",start_date);
        params.put("end_date",end_date);
        CloseableHttpResponse httpResponse=defaultClient.excuteRequest("/stats/qa/recall/daily/list",params);

        return defaultClient.getEntityMapFromResponse(httpResponse);
    }
}