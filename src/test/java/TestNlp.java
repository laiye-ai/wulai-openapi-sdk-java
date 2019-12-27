import com.WulaiClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.response.nlp.EntityElement;
import com.module.response.nlp.Token;
import com.wulai.nlp.ExtractEntities;
import com.wulai.nlp.Tokenize;
import org.junit.Test;

import java.util.List;

public class TestNlp {


    WulaiClient wulaiClient = new WulaiClient();

    @Test
    public void TestTokenize() throws ServerException, ClientException {

        Tokenize tokenize = new Tokenize();
        tokenize.setQuery("周杰伦");

        List<Token> tokens = tokenize.request(wulaiClient);
        System.out.println(tokens.get(0).getText());
    }

    @Test
    public void TestExtractEntities() throws ServerException, ClientException {

        ExtractEntities extractEntities = new ExtractEntities();
        extractEntities.setQuery("张涛");

        List<EntityElement> entityElements = extractEntities.request(wulaiClient);
        if (entityElements.get(0).getEntity()==null){
            throw new ServerException("1","ExtractEntities error",1);
        }

    }
}
