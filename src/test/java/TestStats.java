import com.WulaiClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.request.msg.MsgBody;
import com.module.request.msg.Text;
import com.module.response.msg.BotResponse;
import com.module.response.stats.QARecallDailyStat;
import com.module.response.stats.QASatisfactionKnowledgeStats;
import com.module.response.stats.SatisfactionDailyKnowledgeList;
import com.util.Credentials;
import com.wulai.msg.GetBotResponse;
import com.wulai.stats.QueryQaRecallDailyList;
import com.wulai.stats.QueryRecallDailyKnowledgeList;
import com.wulai.stats.QuerySatisfactionDailyKnowledgeList;
import org.junit.Test;

import java.util.List;

public class TestStats {


    private WulaiClient wulaiClient = new WulaiClient();



    //查询问答召回数统计列表（知识点粒度，日报）
    @Test
    public void TestSatisfactionDailyKnowledgeList() throws ServerException, ClientException {
        Credentials credentials=new Credentials("9HnxSzRs9BW8wmy67SPPbxJ3SZCFgSTG006f0e1d7227aff0ba","FGk9dJPlznna6K9xwDEe");
        WulaiClient wulaiClient =new WulaiClient();
        wulaiClient.setCredentials(credentials);
        QuerySatisfactionDailyKnowledgeList qasatisfactionDailyKnowledgeList = new QuerySatisfactionDailyKnowledgeList();
        qasatisfactionDailyKnowledgeList.setPageSize(4);
        qasatisfactionDailyKnowledgeList.setPage(1);
        qasatisfactionDailyKnowledgeList.setStartDate("20191111");
        qasatisfactionDailyKnowledgeList.setEndDate("20191202");

        SatisfactionDailyKnowledgeList satisfactionDailyKnowledgeList = qasatisfactionDailyKnowledgeList.request(wulaiClient);

        if (satisfactionDailyKnowledgeList.getQARecallKnowledgeStats() == null) {
            throw new ServerException("1", "satisfactionDailyKnowledgeList error", 1);
        }
    }


    //查询问答召回数统计列表（日报）
    @Test
    public void TestStatsQaRecallDailyList() throws ServerException, ClientException {

        QueryQaRecallDailyList queryQaRecallDailyList = new QueryQaRecallDailyList();
        queryQaRecallDailyList.setStart_date("20191111");
        queryQaRecallDailyList.setEnd_date("20191121");

        List<QARecallDailyStat> qaRecallDailyStatList = queryQaRecallDailyList.request(wulaiClient);
        if (qaRecallDailyStatList.get(0).getQARecallStats()==null){
            throw new ServerException("1","QueryQaRecallDailyList error",1);
        }
    }

    //查询问答满意度评价统计列表（知识点粒度，日报）
    @Test
    public void TestQueryQaRecallDailyList() throws ServerException, ClientException {
        Credentials credentials=new Credentials("9HnxSzRs9BW8wmy67SPPbxJ3SZCFgSTG006f0e1d7227aff0ba","FGk9dJPlznna6K9xwDEe");
        WulaiClient wulaiClient =new WulaiClient();
        wulaiClient.setCredentials(credentials);
        QueryRecallDailyKnowledgeList queryRecallDailyKnowledgeList =new QueryRecallDailyKnowledgeList();
        queryRecallDailyKnowledgeList.setStartDate("20191111");
        queryRecallDailyKnowledgeList.setEndDate("20191121");
        queryRecallDailyKnowledgeList.setPage(1);
        queryRecallDailyKnowledgeList.setPageSize(5);
        QASatisfactionKnowledgeStats qaSatisfactionKnowledgeStats=queryRecallDailyKnowledgeList.request(wulaiClient);
        if (qaSatisfactionKnowledgeStats.getQASatisfactionKnowledgeStats()==null){
            throw new ServerException("1","QASatisfactionKnowledgeStats error ",1);
        }



    }

    @Test
    public void TestCreateSatisfaction() throws ServerException, ClientException {
        Text text = new Text("怀孕适合去哪里旅游");
        MsgBody msgBody = new MsgBody();
        msgBody.setText(text);

        GetBotResponse getBotResponse = new GetBotResponse();
        getBotResponse.setUserId("zhangtao@test");
        getBotResponse.setMsgBody(msgBody);
        BotResponse botResponse = null;
        botResponse = getBotResponse.request(wulaiClient);
        if (botResponse.getSuggestedResponse().get(0).getResponse() == null) {
            throw new ServerException("1", "CreateSatisfaction error", 1);
        }


    }


}
