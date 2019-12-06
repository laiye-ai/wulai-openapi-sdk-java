import com.DefaultClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.wulai.nlp.ExtractEntities;
import com.wulai.nlp.Tokenize;
import org.junit.Test;

import java.util.Map;

public class TestNlp {


    DefaultClient defaultClient=new DefaultClient();

    @Test
    public void TestTokenize(){
        DefaultClient defaultClient=new DefaultClient();
        Tokenize tokenize=new Tokenize();
        tokenize.setQuery("小周");
        try {
            Map map=tokenize.request(defaultClient);
            for (Object o :map.keySet()
            ) {
                System.out.println(o);
                System.out.println(map.get(o));
            }
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }


    }


    @Test
    public void TestExtractEntities(){
        DefaultClient defaultClient=new DefaultClient();
        ExtractEntities extractEntities=new ExtractEntities();
        extractEntities.setQuery("张涛");
        try {
            Map map=extractEntities.request(defaultClient);
            for (Object o :map.keySet()
            ) {
                System.out.println(o);
                System.out.println(map.get(o));
            }
        } catch (ClientException e){

            System.out.println(e.getErrCode());
            System.out.println(e.getStackTrace());

        }catch (ServerException e ){

            System.out.println(e.getErrCode());
            System.out.println("httpcode:"+e.getHttp_code());
            System.out.println(e.getMessage());

        }


    }
}
