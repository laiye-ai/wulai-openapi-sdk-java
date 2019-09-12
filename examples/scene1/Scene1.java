import exceptions.ClientException;
import exceptions.ServerException;
import request.msg.BotResponseRequest;
import request.msg.MsgBody;
import request.msg.Text;
import request.user.UserCreateRequest;

import java.net.URI;
import java.util.logging.Logger;

public class Scene1 {
    private static WulaiClient wulaiClient;

    static Logger logger=Logger.getLogger("Scene1");

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
        UserCreateRequest userCreateRequest= null;
        int result= 0;
        try {
            userCreateRequest = new UserCreateRequest("");
            result = wulaiClient.userCreate(userCreateRequest);
        } catch (ClientException | ServerException e) {
            logger.severe("userCreate error");
        }

        if (200==result){
            Text text =new Text("你好");
            MsgBody msgBody=new MsgBody(text);
            BotResponseRequest botResponseRequest=null;
            try {
                botResponseRequest=new BotResponseRequest(userId,msgBody);
                wulaiClient.getBotResponse(botResponseRequest);
            } catch (ClientException | ServerException e) {
                logger.severe("getBotResponse error");
            }
        }else {
            logger.info("userCreate error, skip getBotResponse ,please check the userCreate response");
        }
    }
}
