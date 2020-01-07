package com.wulai.knowledge;

import com.WulaiClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.request.knowledge.KnowledgeTagKnowledge;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;

public class CreateKnowledgeTagKnowledge {
    private KnowledgeTagKnowledge knowledgeTagKnowledge;

    public void setKnowledgeTagKnowledge(KnowledgeTagKnowledge knowledgeTagKnowledge) {
        this.knowledgeTagKnowledge = knowledgeTagKnowledge;
    }

    public KnowledgeTagKnowledge getKnowledgeTagKnowledge() {
        return knowledgeTagKnowledge;
    }

    public KnowledgeTagKnowledge request(WulaiClient wulaiClient) throws ServerException, ClientException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("knowledge_tag_knowledge", knowledgeTagKnowledge);

        CloseableHttpResponse httpResponse = wulaiClient.executeRequest("/qa/knowledge-tag-knowledge/create", params);

        return wulaiClient.getResponse(httpResponse,KnowledgeTagKnowledge.class,"knowledge_tag_knowledge");

    }
}
