
import exceptions.ClientException;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.URI;
import java.util.HashMap;

public class TestWulaiClient {
    private static WulaiClient wulaiClient;
    private static String name;

    private static String usercreate;
    private static String botresponse;
    private HashMap<String,Integer> hashMap=new HashMap<String,Integer>();



    @Before
    public void setEnv() throws ClientException {
        wulaiClient=new WulaiClient(System.getenv("pubkey"),
                System.getenv("secret"), "v2", false);
        name="zhangtao@test";
        usercreate=String.format("{\"user_id\":\"%s\"}", name);
        botresponse = String.format("{\"user_id\":\"%s\",\"msg_body\":{\"text\":{\"content\":\"%s\"}},\"extra\":\"%s\"}", name, "你是谁", "");
        hashMap.put("timeout",3);
        System.out.println("setEnv");
    }

    @Test
    public void testErrorEnv()  {
        try {
            wulaiClient=new WulaiClient(System.getenv("LOGNAME"),System.getenv("LOGNAME"),"v2",false);
            wulaiClient.processCommonRequest("/user/create", usercreate, hashMap);
        }catch (ClientException e){
            System.out.println("test捕获异常:"+e.getMessage());
        }
    }


    @Test
    public void testSetPool(){
        try {
            PoolingHttpClientConnectionManager cm =new PoolingHttpClientConnectionManager();
            cm.setDefaultMaxPerRoute(20);
            cm.setMaxTotal(20);
            wulaiClient.setPools(cm,1);
            wulaiClient.processCommonRequest("/user/create", usercreate, hashMap);
            wulaiClient.processCommonRequest("/msg/bot-response",botresponse, hashMap);


        } catch (ClientException e) {
            System.out.println("testProcessCommonRequest捕获异常"+e.getMessage());
        }
    }

    @Test
    public void testNormal() throws ClientException {
            wulaiClient.processCommonRequest("/user/create", usercreate, hashMap);
            wulaiClient.processCommonRequest("/msg/bot-response",botresponse, hashMap);
    }

    @Test
    public void test404(){
        try {
            wulaiClient.processCommonRequest("/user/createaaaaa", usercreate, hashMap);
        } catch (ClientException e) {
            System.out.println("test404捕获异常"+e.getMessage());
        }
    }



    @Test
    public void testApiVersion(){
        try {
            wulaiClient= new WulaiClient(System.getenv("pubkey"),
                    System.getenv("secret"), "v1", false);
        } catch (ClientException e) {
            System.out.println("testApiVersion捕获异常:"+e.getMessage());
        }


        try {
            wulaiClient.processCommonRequest("/user/create",usercreate,hashMap);
        } catch (ClientException e) {
            System.out.println("testApiVersion捕获异常:"+e.getMessage());
        }
    }



    @Test
    public void testErrorSecret(){
        try {
            wulaiClient=new WulaiClient(System.getenv("pubkey"),
                    System.getenv("secret").substring(1,4), "v2", false);
            wulaiClient.processCommonRequest("/msg/bot-response",botresponse, hashMap);
        } catch (ClientException e) {
            System.out.println("testErrorSecret捕获异常:"+e.getMessage());
        }
    }
    @Test
    public void testUnreacheableUrl(){
        wulaiClient.setEndpoint(URI.create("https://www.google.com/"));
        try {
            wulaiClient.processCommonRequest("/msg/bot-response",botresponse, hashMap);
        } catch (ClientException e) {
            System.out.println("testUnreacheableUrl捕获异常:"+e.getMessage());
        }

    }

    @Test
    public void testPubkeyisNull() {
        WulaiClient wulaiClient2 = null;
        try {

            wulaiClient2=new WulaiClient(System.getenv(""),System.getenv(""),"v2",false);
        }catch (ClientException e ){
            System.out.println("testPubkeyisNull捕获异常:"+e.getMessage());
        }
        try {
            wulaiClient2.processCommonRequest("/user/create", usercreate, hashMap);
            wulaiClient2.processCommonRequest("/msg/bot-response",botresponse, hashMap);
        } catch (ClientException e) {
            System.out.println("testPubkeyisNull捕获异常:"+e.getMessage());
        }catch (NullPointerException e){
            System.out.println("testPubkeyisNull捕获异常:"+e.getMessage());
        }


    }

}




