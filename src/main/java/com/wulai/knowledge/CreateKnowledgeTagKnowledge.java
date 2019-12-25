package com.wulai.knowledge;

import com.DefaultClient;
import com.alibaba.fastjson.JSONObject;
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

    public KnowledgeTagKnowledge request(DefaultClient defaultClient) throws ServerException, ClientException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("knowledge_tag_knowledge", knowledgeTagKnowledge);

        CloseableHttpResponse httpResponse = defaultClient.excuteRequest("/qa/knowledge-tag-knowledge/create", params);
        JSONObject response = defaultClient.getJsonFromResponse(httpResponse);

        KnowledgeTagKnowledge knowledgeTagKnowledge = JSONObject.parseObject(response.get("knowledge_tag_knowledge").toString(), KnowledgeTagKnowledge.class);

        return knowledgeTagKnowledge;
    }
}
