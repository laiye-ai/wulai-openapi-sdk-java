import com.DefaultClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.request.dictionary.*;
import com.module.response.dictionary.IntentResponse;
import com.module.response.dictionary.TermListResponse;
import com.wulai.dictionary.*;
import org.junit.AfterClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TestDictionary {

    private static DefaultClient defaultClient = new DefaultClient();
    private static int entityId;
    private static int intentId;
    private static int termId;
    private static Value value;

    //实体类接口测试*(9)
    @Test
    public void TestEntity() throws ServerException, ClientException {

        //1.查询全部实体概要
        QueryEntityList queryEntityList = new QueryEntityList();
        queryEntityList.setPage(1);
        queryEntityList.setPageSize(100);
        List<com.module.response.dictionary.Entity> entities = queryEntityList.request(defaultClient);
        if (entities.get(0)==null) {
            throw new ServerException("1", " QueryEntityList error", 1);
        }
        //2.获取实体详情
        QueryEntity queryEntity = new QueryEntity();
        queryEntity.setId(39750);
        Entity entity = queryEntity.request(defaultClient);
        if (entity.getValue().getEnumerationEntityValue().getValues() == null) {
            throw new ServerException("1", " QueryEntity error", 1);
        }


        //3.创建枚举实体
        EnumEntity enumEntity = new EnumEntity();
        enumEntity.setName("大屏手机");

        CreateEntityEnumeration createEntityEnumeration = new CreateEntityEnumeration();
        createEntityEnumeration.setEnumEntity(enumEntity);
        EnumEntity enumEntity1 = createEntityEnumeration.request(defaultClient);
        if (enumEntity1.getName() == null) {
            throw new ServerException("1", " CreateEntityEnumeration error", 1);
        }
        entityId = enumEntity1.getId();
        //4.创建枚举实体值
        CreateEntityEnumerationValue createEntityEnumerationValue = new CreateEntityEnumerationValue();
        createEntityEnumerationValue.setEntityId(entityId);
        value = new Value();
        value.setStandardValue("智能手机");
        value.addSynonym("苹果");
        value.addSynonym("华为");
        value.addSynonym("三星");
        value.addSynonym("小米");
        createEntityEnumerationValue.setValue(value);
        EnumEntity enumEntity2 = createEntityEnumerationValue.request(defaultClient);
        if (enumEntity2.getValues() == null) {
            throw new ServerException("1", " CreateEntityEnumerationValue error", 1);
        }


        CreateEntityIntent createEntityIntent = new CreateEntityIntent();
        IntentEntity intentEntity = new IntentEntity();
        intentEntity.setName("肯定答复");
        intentEntity.setStandardValue("嗯嗯");
        createEntityIntent.setIntentEntity(intentEntity);
        IntentResponse intentResponse = createEntityIntent.request(defaultClient);
        intentId = intentResponse.getId();
        if (intentResponse.getValue() == null) {
            throw new ServerException("1", "CreateEntityIntent error", 1);
        }

        //8.创建意图实体值相似说法
        CreateEntityIntentValue createEntityIntentValue = new CreateEntityIntentValue();
        createEntityIntentValue.setEntityId(intentId);
        createEntityIntentValue.addSynonyms("好的");
        createEntityIntentValue.addSynonyms("可以");
        createEntityIntentValue.addSynonyms("牛逼");
        createEntityIntentValue.addSynonyms("OJBK");
        IntentResponse intentValue = createEntityIntentValue.request(defaultClient);
        if (intentValue == null) {
            throw new ServerException("1", "CreateEntityIntentValue error", 1);
        }


    }

    //专有词汇接口测试（4）
    @Test
    public void TestTerm() throws ServerException, ClientException {
        //1.创建专有词汇
        CreateTerm createTerm = new CreateTerm();
        Term term = new Term();
        term.setName("CEO");
        TermItem termItem = new TermItem();
        termItem.setTerm(term);
        createTerm.setTermItem(termItem);
        TermItem createTermMap = createTerm.request(defaultClient);

        termId = createTermMap.getTerm().getId();

        //2.更新专有词汇
        term.setId(termId);
        termItem.setTerm(term);
        ArrayList<String> synonyms = new ArrayList<>();
        synonyms.add("老板");
        synonyms.add("老大");
        termItem.setSynonyms(synonyms);
        UpdateTerm updateTerm = new UpdateTerm();
        updateTerm.setTermItem(termItem);

        TermItem termItem1 = updateTerm.request(defaultClient);

        if (termItem1.getSynonyms() == null||termItem1.getTerm()==null) {
            throw new ServerException("1", "UpdateTerm error", 1);
        }


        //4.查询专有词汇列表
        QueryTermList queryTermList = new QueryTermList();
        queryTermList.setPage(1);
        queryTermList.setPageSize(20);
        TermListResponse termListResponse = queryTermList.request(defaultClient);
        if (termListResponse.getTermItem() == null) {
            throw new ServerException("1", "QueryTermList error", 1);
        }

    }

    @AfterClass
    public static void TestDelete() throws ServerException, ClientException {

        //5.删除枚举实体值
        DeleteEntityEnumerationValue deleteEntityEnumerationValue = new DeleteEntityEnumerationValue();
        deleteEntityEnumerationValue.setEntityId(entityId);
        List synonyms = value.getSynonyms();
        synonyms.remove(1);
        synonyms.remove(1);
        value.setSynonyms(synonyms);
        deleteEntityEnumerationValue.setValue(value);
        int httpcode = deleteEntityEnumerationValue.request(defaultClient);
        if (200 != httpcode) {
            throw new ServerException("1", " DeleteEntityEnumerationValue error", 1);
        }

        //删除枚举实体
        DeleteEntity deleteEntity = new DeleteEntity();
        deleteEntity.setId(entityId);
        int code = deleteEntity.request(defaultClient);
        if (200 != code) {
            throw new ServerException("1", " DeleteEntity error", 1);
        }

        //删除意图实体值相似说法
        DeleteEntityIntentValue deleteEntityIntentValue = new DeleteEntityIntentValue();
        deleteEntityIntentValue.setEntityId(intentId);
        deleteEntityIntentValue.addSynonym("可以");
        deleteEntityIntentValue.request(defaultClient);

        //删除实体
        deleteEntity.setId(intentId);
        int code2 = deleteEntity.request(defaultClient);
        if (200 != code2) {
            throw new ServerException("1", "DeleteEntityIntentValue error", 1);
        }

        //删除专有词汇
        DeleteTerm deleteTerm = new DeleteTerm();
        deleteTerm.setId("" + termId);
        int code3 = deleteTerm.request(defaultClient);
        if (200 != code3) {
            throw new ServerException("1", "Delete Term error", 1);
        }

    }


}
