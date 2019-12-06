package com.wulai.stats;

import com.DefaultClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;
import java.util.Map;

public class QuerySatisfactionDailyKnowledgeList {
    private String startDate;
    private String endDate;
    private int page ;
    private int pageSize;

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getPage() {
        return page;
    }
    public Map request(DefaultClient defaultClient) throws ServerException, ClientException {
        HashMap<String,Object> params=new HashMap<>();
        params.put("start_date",startDate);
        params.put("end_date",endDate);
        params.put("page",page);
        params.put("page_size",pageSize);

        CloseableHttpResponse httpResponse=defaultClient.excuteRequest("/stats/qa/satisfaction/daily/knowledge/list",params);

        return defaultClient.getEntityMapFromResponse(httpResponse);

    }
}
