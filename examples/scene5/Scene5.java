import com.DefaultClient;
import com.WulaiClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.request.MsgBody;
import com.module.request.Text;
import com.module.response.msg.HistoryResponse;
import com.wulai.msg.MsgHistory;
import com.wulai.msg.MsgSync;

import java.util.logging.Logger;

public class Scene5 {
    private static WulaiClient wulaiClient;

    private static Logger logger = Logger.getLogger("Scene5");

    public static void main(String[] args) {
        String userId = "laiye@test";
        Text text = new Text("人类的本质就是复读机");
        MsgBody msgBody = new MsgBody(text);
        Long timestamp = System.currentTimeMillis();
        DefaultClient defaultClient=new DefaultClient();
        try {

            MsgSync msgSync=new MsgSync();
            msgSync.setUserId(userId);
            msgSync.setMsgBody(msgBody);
            msgSync.setMsgTimestamp(timestamp);
            msgSync.request(defaultClient);
        } catch (ClientException e) {
            logger.severe("sync params error");
        } catch (ServerException e) {
            logger.severe("sync http error");
        }
        try {
            MsgHistory msgHistory=new MsgHistory();
            msgHistory.setUserId(userId);
            msgHistory.setNum(5);
            HistoryResponse historyResponse=msgHistory.request(defaultClient);

            System.out.println("hashMore:" + historyResponse.isHasMore());
            Object[] objects = historyResponse.getMsg();
            for (Object object : objects) {
                System.out.println(object.toString());
            }
        } catch (ClientException e) {
            logger.severe("history params error");
        } catch (ServerException e) {
            logger.severe("history http error");
        }


    }
}
