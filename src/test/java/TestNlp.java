import com.DefaultClient;
import com.alibaba.fastjson.JSONObject;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.response.nlp.Token;
import com.wulai.nlp.ExtractEntities;
import com.wulai.nlp.Tokenize;
import org.junit.Test;

import java.util.List;

public class TestNlp {


    DefaultClient defaultClient = new DefaultClient();

    public void TestTokenize() throws ServerException, ClientException {
        DefaultClient defaultClient = new DefaultClient();
        Tokenize tokenize = new Tokenize();
        tokenize.setQuery("周杰伦");

        List<Token> tokens = tokenize.request(defaultClient);
        System.out.println(tokens.get(0).getText());
    }


    @Test
    public void TestExtractEntities() throws ServerException, ClientException {
        DefaultClient defaultClient = new DefaultClient();
        ExtractEntities extractEntities = new ExtractEntities();
        extractEntities.setQuery("张涛");

        JSONObject jsonObject = extractEntities.request(defaultClient);

    }
}
