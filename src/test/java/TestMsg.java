import com.WulaiClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.request.msg.MsgBody;
import com.module.request.msg.Text;
import com.module.response.msg.*;
import com.wulai.msg.*;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

public class TestMsg {
    private WulaiClient wulaiClient = new WulaiClient();

    @Test
    public void TestMsg() throws ServerException, ClientException {
        String userId="zhangtao@test";
        String question="怀孕适合去哪里旅游";
        GetBotResponse getBotResponse = new GetBotResponse();
        getBotResponse.setUserId(userId);
        getBotResponse.setMsgBody(new MsgBody(new Text(question)));
        BotResponse botResponse = getBotResponse.request(wulaiClient);
        if (botResponse.getMsgId() == null||botResponse.getSuggestedResponse()==null) {
            throw new ServerException("1", "get botresponse error", 1);
        }

        GetKeywordBotResponse getKeywordBotResponse = new GetKeywordBotResponse();
        getKeywordBotResponse.setMsgBody(new MsgBody(new Text("你好")));
        getKeywordBotResponse.setUserId("zhangtao@test");
        KeywordResponse keywordResponse = getKeywordBotResponse.request(wulaiClient);
        if (keywordResponse.getMsgId() == null||keywordResponse.getKeywordSuggestedResponse()==null) {
            throw new ServerException("1", "get botresponse error", 1);
        }

        GetQABotResponse getQABotResponse=new GetQABotResponse();
        getQABotResponse.setMsgBody(new MsgBody(new Text(question)));
        getQABotResponse.setUserId(userId);
        QAResponse qaResponse=getQABotResponse.request(wulaiClient);
        if (qaResponse.getMsgId()==null||qaResponse.getQASuggestedResponse()==null){
            throw new ServerException("1","GetQABotResponse error",1);
        }

        GetTaskBotResponse getTaskBotResponse =new GetTaskBotResponse();
        getTaskBotResponse.setUserId(userId);
        getTaskBotResponse.setMsgBody(new MsgBody(new Text("hi")));
        TaskResponse taskResponse=getTaskBotResponse.request(wulaiClient);
        if (taskResponse.getMsgId()==null||taskResponse.getTaskSuggestedResponse()==null){
            throw  new ServerException("","getTaskBotResponse error",1);
        }

        QueryMsgHistory queryMsgHistory=new QueryMsgHistory();
        queryMsgHistory.setUserId(userId);
        queryMsgHistory.setDirection(QueryMsgHistory.Direction.BACKWARD);
        queryMsgHistory.setNum(4);
        MsgHistory msgHistory=queryMsgHistory.request(wulaiClient);
        if (msgHistory.getMsg()==null){
            throw new ServerException("1","QueryMsgHistory error",1);
        }

        SyncMsg syncMsg=new SyncMsg();
        syncMsg.setUserID(userId);
        syncMsg.setMsgBody(new MsgBody(new Text("你好，我是小来")));
        syncMsg.setMsgTs("1577412789000");
        String sync=syncMsg.request(wulaiClient);
        if (sync==null){
            throw new ServerException("1","sync msg error",1);
        }

        SendMsg sendMsg=new SendMsg();
        sendMsg.setUserID(userId);
        sendMsg.setMsgBody(new MsgBody(new Text("测试 send msg interface")));
        String send=sendMsg.request(wulaiClient);
        if (send==null) {
            throw new ServerException("1", "send msg error", 1);
        }

        ReceiveMsg receiveMsg=new ReceiveMsg();
        receiveMsg.setUserId(userId);
        receiveMsg.setMsgBody(new MsgBody(new Text("你好")));
        String receive=receiveMsg.request(wulaiClient);
        if (receive == null) {
            throw  new ServerException("1","receive msg error",1);
        }
        GetUserSuggestion getUserSuggestion=new GetUserSuggestion();
        getUserSuggestion.setUserId(userId);
        getUserSuggestion.setQuery("怀孕");
        List<String> userSuggestions=getUserSuggestion.request(wulaiClient);

        if (userSuggestions.iterator().next()==null){
            throw new ServerException("1","userSuggestions error",1);
        }




    }
}
