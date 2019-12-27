import com.DefaultClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.request.MsgBody;
import com.module.request.Text;
import com.module.request.user.*;
import com.module.response.msg.BotResponse;
import com.wulai.msg.GetBotResponse;
import com.wulai.user.UserAttributeCreate;
import com.wulai.user.UserCreate;

import java.util.logging.Logger;

public class Scene2 {
    static Logger logger = Logger.getLogger("Scene1");


    public static void main(String[] args) {
        String name = "laiye@test";
        int result = 0;

        DefaultClient defaultClient=new DefaultClient();

        try {
            UserCreate userCreate=new UserCreate();
            userCreate.setUserId(name);
            userCreate.setAvatarUrl("https://www.laiye.com/static/official-website/logo.png");
            result = userCreate.request(defaultClient);
        } catch (ClientException e) {
            logger.severe("userCreate params error");
        } catch (ServerException e) {
            logger.severe("userCreate http error");
        }
        int userAttributeCode = 0;
        if (result == 200) {
            try {
                // 吾来平台->对话搭建->个性化体验->用户体验
                UserAttribute userAttribute = new UserAttribute("101283"); // 'is复读机'的属性id
                UserAttributeValue userAttributeValue = new UserAttributeValue("yes"); // 设置用户属性"is复读机"为yes

                UserAttributeUserAttributeValue userAttributeUserAttributeValue = new UserAttributeUserAttributeValue();
                userAttributeUserAttributeValue.setUser_attribute_value(userAttributeValue);
                userAttributeUserAttributeValue.setUser_attribute(userAttribute);

                UserAttributeCreateRequest userAttributeCreateRequest = new UserAttributeCreateRequest(name);
                userAttributeCreateRequest.addUserAttributeUserAttributeValue(userAttributeUserAttributeValue);
                UserAttributeCreate userAttributeCreate=new UserAttributeCreate();
                userAttributeCode=userAttributeCreate.request(defaultClient);

            } catch (ClientException e) {
                logger.severe("userAttribute params error");
            } catch (ServerException e) {
                logger.severe("userAttribute http error.");
            }
        }
        BotResponse botResponse = null;
        if (userAttributeCode == 200) {
            Text text = new Text("你是复读机吗"); // 设置对话内容
            MsgBody msgBody = new MsgBody(text);
            try {
                GetBotResponse getBotResponse =new GetBotResponse();
                getBotResponse.setUserId(name);
                getBotResponse.setMsgBody(msgBody);
                botResponse =getBotResponse.request(defaultClient);

                Object[] objects = botResponse.getSuggestedResponse();
                System.out.println(objects[0].toString());
                /*Console print
                 * {"score":1,"is_send":true,"dictionary":[{"enable_evaluate":false,"msg_body":{"text":{"content":"是的，你终于发现了你的本质"}},"delay_ts":0,"similar_response":[]}],"bot":{"qa":{"question":"你是复读机吗？","knowledge_id":887687,"standard_question":"你是复读机吗？"}},"source":"QA_BOT","quick_reply":[]}
                 */
            } catch (ClientException e) {
                logger.severe("getBotResponse params error");
            } catch (ServerException e) {
                logger.severe("getBotResponse http error");
            }

        }

    }
}
