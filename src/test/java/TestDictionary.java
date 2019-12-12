import com.DefaultClient;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.request.dictionary.*;
import com.wulai.dictionary.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TestDictionary {

    DefaultClient defaultClient = new DefaultClient();

    //实体类接口测试*(9)
    @Test
    public void TestEntity() throws ServerException, ClientException {

        //1.查询全部实体概要
        QueryEntityList queryEntityList = new QueryEntityList();
        queryEntityList.setPage(1);
        queryEntityList.setPageSize(100);
        Map map = queryEntityList.request(defaultClient);

        for (Object o : map.keySet()
        ) {
            System.out.println(o);
            System.out.println(map.get(o));

        }
        JSONArray jsonArray = JSON.parseArray(map.get("entities").toString());
        System.out.println(jsonArray.size());

        //2.获取实体详情
        GetEntity getEntity = new GetEntity();
        getEntity.setId(39750);
        Map getentity = getEntity.request(defaultClient);
        System.out.println(getentity);

        //3.创建枚举实体
        EnumEntity enumEntity = new EnumEntity();
        enumEntity.setName("大屏手机");

        CreateEntityEnumeration createEntityEnumeration = new CreateEntityEnumeration();
        createEntityEnumeration.setEnumEntity(enumEntity);
        Map createEntityEnumerationMap = createEntityEnumeration.request(defaultClient);
        System.out.println(createEntityEnumerationMap);
        Object o=createEntityEnumerationMap.get("enum_entity");
        JSONObject json=JSONObject.parseObject(o.toString());
        int entityId=Integer.valueOf(json.get("id").toString());
        //4.创建枚举实体值
        CreateEntityEnumerationValue createEntityEnumerationValue=new CreateEntityEnumerationValue();
        createEntityEnumerationValue.setEntityId(entityId);
        Value value=new Value();
        value.setStandard_value("智能手机");
        value.addSynonym("苹果");
        value.addSynonym("华为");
        value.addSynonym("三星");
        value.addSynonym("小米");
        createEntityEnumerationValue.setValue(value);
        Map createEntityEnumerationValueMap=createEntityEnumerationValue.request(defaultClient);
        System.out.println(createEntityEnumerationValueMap);

        //5.删除枚举实体值
        DeleteEntityEnumerationValue deleteEntityEnumerationValue=new DeleteEntityEnumerationValue();
        deleteEntityEnumerationValue.setEntityId(entityId);
        List synonyms=value.getSynonyms();
        synonyms.remove(1);
        synonyms.remove(1);
        value.setSynonyms(synonyms);
        deleteEntityEnumerationValue.setValue(value);
        deleteEntityEnumerationValue.request(defaultClient);


        //6.删除实体
        DeleteEntity deleteEntity=new DeleteEntity();
        deleteEntity.setId(entityId);
        int code=deleteEntity.request(defaultClient);
        if (code==200){
            System.out.println("delete enum success");
        }

        //7.创建意图实体


        CreateEntityIntent createEntityIntent=new CreateEntityIntent();
        IntentEntity intentEntity=new IntentEntity();
        intentEntity.setName("肯定答复");
        intentEntity.setStandard_value("嗯嗯");
        createEntityIntent.setIntentEntity(intentEntity);
        Map intentMap=createEntityIntent.request(defaultClient);

        Object intentObj=intentMap.get("intent_entity");
        JSONObject intentjson=JSONObject.parseObject(intentObj.toString());
        int intentId=Integer.valueOf(intentjson.get("id").toString());

        //8.创建意图实体值相似说法
        CreateEntityIntentValue createEntityIntentValue=new CreateEntityIntentValue();
        createEntityIntentValue.setEntityId(intentId);
        createEntityIntentValue.addSynonyms("好的");
        createEntityIntentValue.addSynonyms("可以");
        createEntityIntentValue.addSynonyms("牛逼");
        createEntityIntentValue.addSynonyms("OJBK");
        Map intentValueMap=createEntityIntentValue.request(defaultClient);
        System.out.println(intentValueMap);

        //9.删除意图实体值相似说法
        DeleteEntityIntentValue deleteEntityIntentValue=new DeleteEntityIntentValue();
        deleteEntityIntentValue.setEntityId(intentId);
        deleteEntityIntentValue.addSynonym("可以");
        deleteEntityIntentValue.request(defaultClient);

        //删除实体
        deleteEntity.setId(intentId);
        int intentcode=deleteEntity.request(defaultClient);
        if (intentcode==200){
            System.out.println("delete intentEntity success");
        }
    }

    //专有词汇接口测试（4）
    @Test
    public void TestTerm() throws ServerException,ClientException{
        //1.创建专有词汇
        CreateTerm createTerm=new CreateTerm();
        Term term=new Term();
        term.setName("CEO");
        TermItem termItem =new TermItem();
        termItem.setTerm(term);
        createTerm.setTermItem(termItem);
        Map createTermMap=createTerm.request(defaultClient);
        System.out.println(createTermMap);
        String term_item=createTermMap.get("term_item").toString();
        JSONObject jsonObject = JSONObject.parseObject(term_item);
        String termstr=jsonObject.get("term").toString();
        JSONObject json=JSONObject.parseObject(termstr);
        String termId = json.get("id").toString();
        //2.更新专有词汇
        term.setId(Integer.valueOf(termId));
        termItem.setTerm(term);
        ArrayList<String> synonyms=new ArrayList<>();
        synonyms.add("老板");
        synonyms.add("老大");
        termItem.setSynonyms(synonyms);
        UpdateTerm updateTerm =new UpdateTerm();
        updateTerm.setTermItem(termItem);

        Map map1=updateTerm.request(defaultClient);
        System.out.println(map1);

        //3.删除专有词汇
        DeleteTerm deleteTerm=new DeleteTerm();
        deleteTerm.setId(termId);
        int code =deleteTerm.request(defaultClient);
        if (code==200){
            System.out.println("delete term ok");
        }
        //4.查询专有词汇列表
        QueryTermList queryTermList=new QueryTermList();
        queryTermList.setPage(1);
        queryTermList.setPageSize(20);
        Map map = queryTermList.request(defaultClient);
        System.out.println(map);
    }


}
