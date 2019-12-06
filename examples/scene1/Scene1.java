import com.DefaultClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.request.msg.BotResponseRequest;
import com.module.request.MsgBody;
import com.module.request.Text;
import com.wulai.msg.GetBotResponse;
import com.wulai.user.UserCreate;

import java.util.logging.Logger;

public class Scene1 {
    static Logger logger = Logger.getLogger("Scene1");


    public static void main(String[] args) {
        String userId = "laiye@test";
        UserCreate userCreate = null;
        int result = 0;
        DefaultClient defaultClient=new DefaultClient();
        try {
            userCreate.setUserId("laiye@test");
            result =userCreate.request(defaultClient);
        } catch (ClientException | ServerException e) {
            logger.severe("userCreate error");
        }

        if (200 == result) {
            Text text = new Text("你好");
            MsgBody msgBody = new MsgBody(text);
            BotResponseRequest botResponseRequest = null;
            try {
                GetBotResponse getBotResponse = new GetBotResponse();
                getBotResponse.setUserId("laiye@test");
                getBotResponse.setMsgBody(msgBody);
                getBotResponse.request(defaultClient);
            } catch (ClientException | ServerException e) {
                logger.severe("getBotResponse error");
            }
        } else {
            logger.info("userCreate error, skip getBotResponse ,please check the userCreate response");
        }
    }
}
