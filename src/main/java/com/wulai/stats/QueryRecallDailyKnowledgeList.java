package com.wulai.stats;

import com.WulaiClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.response.stats.QASatisfactionKnowledgeStats;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;

public class QueryRecallDailyKnowledgeList {
    private String endDate;
    private String startDate;
    private int page;
    private int pageSize;


    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public int getPage() {
        return page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public QASatisfactionKnowledgeStats request(WulaiClient wulaiClient) throws ServerException, ClientException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("page", page);
        params.put("page_size", pageSize);
        params.put("start_date", startDate);
        params.put("end_date", endDate);

        CloseableHttpResponse httpResponse = wulaiClient.executeRequest("/stats/qa/satisfaction/daily/knowledge/list", params);

        return wulaiClient.getResponse(httpResponse, QASatisfactionKnowledgeStats.class);
    }
}
