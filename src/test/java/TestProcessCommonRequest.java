import com.WulaiClient;
import com.alibaba.fastjson.JSONObject;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import org.junit.Test;

public class TestProcessCommonRequest {
    private WulaiClient wulaiClient = new WulaiClient();

    @Test
    public void TestProcessCommonRequest() throws ServerException, ClientException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("user_id", "zhangtao@test");

        JSONObject jsonObject1 = wulaiClient.processCommonRequest("/user/create", jsonObject);
        System.out.println(jsonObject1.toString());

    }
}
