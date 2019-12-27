import com.DefaultClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.request.msg.MsgBody;
import com.module.request.msg.Text;
import com.module.response.msg.BotResponse;
import com.module.response.stats.QARecallDailyStat;
import com.module.response.stats.SatisfactionDailyKnowledgeList;
import com.wulai.msg.GetBotResponse;
import com.wulai.stats.QueryQaRecallDailyList;
import com.wulai.stats.QuerySatisfactionDailyKnowledgeList;
import org.junit.Test;

import java.util.List;

public class TestStats {


    private DefaultClient defaultClient = new DefaultClient();


    @Test
    public void TestSatisfactionDailyKnowledgeList() throws ServerException, ClientException {
        QuerySatisfactionDailyKnowledgeList qasatisfactionDailyKnowledgeList = new QuerySatisfactionDailyKnowledgeList();
        qasatisfactionDailyKnowledgeList.setPageSize(4);
        qasatisfactionDailyKnowledgeList.setPage(1);
        qasatisfactionDailyKnowledgeList.setStartDate("20191111");
        qasatisfactionDailyKnowledgeList.setEndDate("20191202");

        SatisfactionDailyKnowledgeList satisfactionDailyKnowledgeList = qasatisfactionDailyKnowledgeList.request(defaultClient);

    }

    @Test
    public void TestStatsQaRecallDailyList() throws ServerException, ClientException {

        QueryQaRecallDailyList queryQaRecallDailyList = new QueryQaRecallDailyList();
        queryQaRecallDailyList.setStart_date("20191111");
        queryQaRecallDailyList.setEnd_date("20191121");

        List<QARecallDailyStat> qaRecallDailyStatList = queryQaRecallDailyList.request(defaultClient);
        System.out.println(qaRecallDailyStatList.get(0).getDate());
    }

    @Test
    public void TestQueryQaRecallDailyList() throws ServerException, ClientException {
        QueryQaRecallDailyList qaRecallDailyList = new QueryQaRecallDailyList();
        qaRecallDailyList.setStart_date("20191111");
        qaRecallDailyList.setEnd_date("20191121");

        List<QARecallDailyStat> qaRecallDailyStats = qaRecallDailyList.request(defaultClient);


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
        botResponse = getBotResponse.request(defaultClient);
        System.out.println(botResponse.getSuggestedResponse().get(0).getScore());


    }


}
