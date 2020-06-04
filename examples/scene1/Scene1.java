import com.WulaiClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.request.msg.MsgBody;
import com.module.request.msg.Text;
import com.module.response.msg.BotResponse;
import com.wulai.msg.GetBotResponse;
import com.wulai.user.CreateUser;

import java.util.logging.Logger;

public class Scene1 {
    static Logger logger = Logger.getLogger("Scene1");


    public static void main(String[] args) {
        String userId = "laiye@test";
        CreateUser userCreate = null;
        int result = 0;
        WulaiClient defaultClient=new WulaiClient();
        try {
            userCreate.setUserId("laiye@test");
            result =userCreate.request(defaultClient);
        } catch (ClientException | ServerException e) {
            logger.severe("userCreate error");
        }

        if (200 == result) {
            Text text = new Text("你好");
            MsgBody msgBody = new MsgBody(text);
            BotResponse botResponse = null;
            try {
                GetBotResponse getBotResponse = new GetBotResponse();
                getBotResponse.setUserId("laiye@test");
                getBotResponse.setMsgBody(msgBody);
                botResponse=getBotResponse.request(defaultClient);
            } catch (ClientException | ServerException e) {
                logger.severe("getBotResponse error");
            }
        } else {
            logger.info("userCreate error, skip getBotResponse ,please check the userCreate dictionary");
        }
    }
}
