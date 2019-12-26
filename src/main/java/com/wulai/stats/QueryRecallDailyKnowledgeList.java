package com.wulai.stats;

import com.DefaultClient;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.response.stats.QARecallDailylKnowledgeList;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;
import java.util.Map;

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

    public QARecallDailylKnowledgeList request(DefaultClient defaultClient) throws ServerException, ClientException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("page", page);
        params.put("page_size", pageSize);
        params.put("start_date", startDate);
        params.put("end_date", endDate);

        CloseableHttpResponse httpResponse = defaultClient.excuteRequest("/stats/qa/recall/daily/knowledge/list", params);

        return defaultClient.getResponse(httpResponse,QARecallDailylKnowledgeList.class,"qa_recall_knowledge_stats");    }
}
