package com.wulai.knowledge;

import com.DefaultClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.request.knowledge.KnowledgeTagKnowledge;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;
import java.util.Map;

public class CreateKnowledgeTagKnowledge {
    private KnowledgeTagKnowledge knowledgeTagKnowledge;

    public void setKnowledgeTagKnowledge(KnowledgeTagKnowledge knowledgeTagKnowledge) {
        this.knowledgeTagKnowledge = knowledgeTagKnowledge;
    }

    public KnowledgeTagKnowledge getKnowledgeTagKnowledge() {
        return knowledgeTagKnowledge;
    }

    public Map request(DefaultClient defaultClient) throws ServerException, ClientException {
        HashMap<String,Object> params=new HashMap<>();
        params.put("knowledge_tag_knowledge",knowledgeTagKnowledge);

        CloseableHttpResponse closeableHttpResponse=defaultClient.excuteRequest("/qa/knowledge-tag-knowledge/create",params);
        return defaultClient.getEntityMapFromResponse(closeableHttpResponse);

    }
}
