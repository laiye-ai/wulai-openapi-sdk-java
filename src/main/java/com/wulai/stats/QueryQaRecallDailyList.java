package com.wulai.stats;

import com.WulaiClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.response.stats.QARecallDailyStat;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;
import java.util.List;

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

    public List<QARecallDailyStat> request(WulaiClient wulaiClient) throws ServerException, ClientException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("start_date", start_date);
        params.put("end_date", end_date);

        CloseableHttpResponse httpResponse = wulaiClient.executeRequest("/stats/qa/recall/daily/list", params);
        return wulaiClient.getResponseArray(httpResponse,QARecallDailyStat.class,"qa_recall_daily_stats");

    }
}
