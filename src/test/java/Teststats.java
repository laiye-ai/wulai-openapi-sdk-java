import com.DefaultClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.request.MsgBody;
import com.module.request.Text;
import com.module.response.msg.BotResponse;
import com.wulai.msg.GetBotResponse;
import com.wulai.stats.QuerySatisfactionDailyKnowledgeList;
import com.wulai.stats.QueryQaRecallDailyList;
import org.junit.Test;

import java.util.Map;

public class Teststats {



    DefaultClient defaultClient=new DefaultClient();




    @Test
    public void TestSatisfactionDailyKnowledgeList(){
        QuerySatisfactionDailyKnowledgeList satisfactionDailyKnowledgeList=new QuerySatisfactionDailyKnowledgeList();
        satisfactionDailyKnowledgeList.setPageSize(4);
        satisfactionDailyKnowledgeList.setPage(1);
        satisfactionDailyKnowledgeList.setStartDate("20191111");
        satisfactionDailyKnowledgeList.setEndDate("20191202");
        try {
            Map map=satisfactionDailyKnowledgeList.request(defaultClient);

            for (Object o :map.keySet()
                 ) {
                System.out.println(o);
                System.out.println(map.get(o));
            }
        } catch (ServerException | ClientException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TestStatsQaRecallDailyList(){

        QueryQaRecallDailyList queryQaRecallDailyList =new QueryQaRecallDailyList();
        queryQaRecallDailyList.setStart_date("20191111");
        queryQaRecallDailyList.setEnd_date("20191121");
        try {
            Map map= queryQaRecallDailyList.request(defaultClient);
            for (Object o :map.keySet()
            ) {
                System.out.println(o);
                System.out.println(map.get(o));
            }
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TestQueryQaRecallDailyList(){
        QueryQaRecallDailyList qaRecallDailyList=new QueryQaRecallDailyList();
        qaRecallDailyList.setStart_date("20191111");
        qaRecallDailyList.setEnd_date("20191121");
        try {
            Map map =qaRecallDailyList.request(defaultClient);
            for (Object o :map.keySet()
            ) {
                System.out.println(o);
                System.out.println(map.get(o));
            }
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }


    }
    @Test
    public void TestCreateSatisfaction(){
        Text text=new Text("怀孕适合去哪里旅游");
        MsgBody msgBody=new MsgBody(text);

        GetBotResponse getBotResponse=new GetBotResponse();
        getBotResponse.setUserId("zhangtao@test");
        getBotResponse.setMsgBody(msgBody);
        BotResponse botResponse=null;
        try {
            botResponse=getBotResponse.request(defaultClient);
        }catch (ServerException|ClientException e){
            e.printStackTrace();
        }

        for (Object o:botResponse.getSuggestedResponse()
        ) {
            System.out.println(o);
        }

        System.out.println("hello");

    }



}
