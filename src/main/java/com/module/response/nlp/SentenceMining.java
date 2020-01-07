package com.module.response.nlp;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class SentenceMining {
    private String status;
    private List<Cluster> clusters;
    private long pageCount;

    @JSONField( name="status")
    public String getStatus() { return status; }
    @JSONField( name="status")
    public void setStatus(String value) { this.status = value; }

    @JSONField( name="clusters")
    public List<Cluster> getClusters() { return clusters; }
    @JSONField( name="clusters")
    public void setClusters(List<Cluster> value) { this.clusters = value; }

    @JSONField( name="page_count")
    public long getPageCount() { return pageCount; }
    @JSONField( name="page_count")
    public void setPageCount(long value) { this.pageCount = value; }


}
