import exceptions.ClientException;
import exceptions.ServerException;
import request.msg.HistoryRequest;
import request.msg.MsgBody;
import request.msg.SyncRequest;
import request.msg.Text;
import response.msg.HistoryResponse;

import java.net.URI;
import java.sql.Timestamp;
import java.util.logging.Logger;

public class Scene5 {private static WulaiClient wulaiClient;

    private static Logger logger=Logger.getLogger("Scene1");

    static {
        try {
            // 创建client 传入正确的验证信息
            wulaiClient = new WulaiClient(System.getenv("pubkey"),
                    System.getenv("secret"),"v2",false);
            // 设置正确的域名
            wulaiClient.setEndpoint(URI.create("https://openapi.wul.ai/"));
        } catch (ClientException e) {
            logger.severe("init error");
        }
    }

    public static void main(String[] args) {
        String userId="laiye@test";
        Text text=new Text("人类的本质就是复读机");
        MsgBody msgBody=new MsgBody(text);
        Long timestamp=System.currentTimeMillis();
        try {
            SyncRequest syncRequest=new SyncRequest(userId,msgBody,timestamp.toString());
            wulaiClient.msgSync(syncRequest);
        } catch (ClientException e) {
            logger.severe("sync params error");
        } catch (ServerException e) {
            logger.severe("sync http error");
        }
        try {
            HistoryRequest historyRequest=new HistoryRequest(userId,5);
            HistoryResponse historyResponse=wulaiClient.msgHistory(historyRequest);
            System.out.println("hashMore:"+historyResponse.isHasMore());
            Object[] objects=historyResponse.getMsg();
            for (Object object:objects){
                System.out.println(object.toString());
            }
        } catch (ClientException e) {
            logger.severe("history params error");
        } catch (ServerException e) {
            logger.severe("history http error");
        }


    }
}
