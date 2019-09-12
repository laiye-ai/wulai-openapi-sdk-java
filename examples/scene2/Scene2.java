import exceptions.ClientException;
import exceptions.ServerException;
import request.msg.BotResponseRequest;
import request.msg.MsgBody;
import request.msg.Text;
import request.user.*;
import response.msg.BotResponse;

import java.net.URI;
import java.util.logging.Logger;

public class Scene2 {

    private static WulaiClient wulaiClient;

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
        String name = "laiye@test";
        int result=0;
        try {
            UserCreateRequest userCreateRequest=new UserCreateRequest(name);
            userCreateRequest.setNickname("Laiye");
            userCreateRequest.setAvatarUrl("https://www.laiye.com/static/official-website/logo.png");
            result=wulaiClient.userCreate(userCreateRequest);
        }catch (ClientException  e){
            logger.severe("userCreate params error");
        } catch (ServerException e) {
            logger.severe("userCreate http error");
        }
        int userAttributeCode=0;
        if (result==200){
            try {
                // 吾来平台->对话搭建->个性化体验->用户体验
                UserAttribute userAttribute=new UserAttribute("101283"); // 'is复读机'的属性id
                UserAttributeValue userAttributeValue=new UserAttributeValue("yes"); // 设置用户属性"is复读机"为yes

                UserAttributeUserAttributeValue userAttributeUserAttributeValue=new UserAttributeUserAttributeValue();
                userAttributeUserAttributeValue.setUser_attribute_value(userAttributeValue);
                userAttributeUserAttributeValue.setUser_attribute(userAttribute);

                UserAttributeCreateRequest userAttributeCreateRequest=new UserAttributeCreateRequest(name);
                userAttributeCreateRequest.addUserAttributeUserAttributeValue(userAttributeUserAttributeValue);
                userAttributeCode=wulaiClient.userAttributeCreate(userAttributeCreateRequest);
            } catch (ClientException e) {
                logger.severe("userAttribute params error");
            } catch (ServerException e) {
                logger.severe("userAttribute http error.");
            }
        }
        BotResponse botResponse=null;
        if (userAttributeCode==200){
            Text text=new Text("你是复读机吗"); // 设置对话内容
            MsgBody msgBody=new MsgBody(text);
            try {
                BotResponseRequest botResponseRequest =new BotResponseRequest(name,msgBody);
                botResponse=wulaiClient.getBotResponse(botResponseRequest);
                Object[] objects=botResponse.getSuggestedResponse();
                System.out.println(objects[0].toString());
                /*Console print
                * {"score":1,"is_send":true,"response":[{"enable_evaluate":false,"msg_body":{"text":{"content":"是的，你终于发现了你的本质"}},"delay_ts":0,"similar_response":[]}],"bot":{"qa":{"question":"你是复读机吗？","knowledge_id":887687,"standard_question":"你是复读机吗？"}},"source":"QA_BOT","quick_reply":[]}
                */
            } catch (ClientException e) {
                logger.severe("getBotResponse params error");
            } catch (ServerException e) {
                logger.severe("getBotResponse http error");
            }

        }

    }
}
