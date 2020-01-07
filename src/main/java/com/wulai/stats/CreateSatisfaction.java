package com.wulai.stats;

import com.WulaiClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;

public class CreateSatisfaction {
    private String userId;
    private String msgId;
    private String satisfaction = "DEFAULT_SATISFACTION";
    private Bot botId;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public void setBotId(String knowledge_id) {
        this.botId.setKnowledge_id(knowledge_id);
    }

    public void setSatisfaction(String satisfaction) {
        this.satisfaction = satisfaction;
    }

    public String getUserId() {
        return userId;
    }

    public String getMsgId() {
        return msgId;
    }

    public String getBotId() {
        return botId.getKnowledge_id();
    }

    public String getSatisfaction() {
        return satisfaction;
    }

    public int request(WulaiClient wulaiClient) throws ServerException, ClientException {

        HashMap<String, Object> params = new HashMap<>();
        params.put("user_id", userId);
        params.put("msgId", msgId);
        params.put("satisfaction", satisfaction);
        params.put("bot_id", botId);

        CloseableHttpResponse httpResponse = wulaiClient.executeRequest("/qa/satisfaction/create", params);

        return httpResponse.getStatusLine().getStatusCode();
    }

}

class Bot {
    private String knowledge_id;

    void setKnowledge_id(String knowledge_id) {
        this.knowledge_id = knowledge_id;
    }

    String getKnowledge_id() {
        return knowledge_id;
    }
}