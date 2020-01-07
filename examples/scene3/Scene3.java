import com.WulaiClient;
import com.exceptions.ClientException;

import java.net.URI;
import java.util.logging.Logger;

public class Scene3 {
    private static WulaiClient wulaiClient;

    private static Logger logger = Logger.getLogger("Scene1");

    static {
        try {
            // 创建client 传入正确的验证信息
            wulaiClient = new WulaiClient(System.getenv("pubkey"),
                    System.getenv("secret"), "v2");
            // 设置正确的域名
            wulaiClient.setEndpoint(URI.create("https://openapi.wul.ai/"));
        } catch (ClientException e) {
            logger.severe("init error");
        }
    }

    public static void main(String[] args) {
        String userId = "laiye@test";


    }


}
