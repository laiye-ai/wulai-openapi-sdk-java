import com.DefaultClient;
import com.alibaba.fastjson.JSONObject;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.request.*;
import com.module.request.knowledge.*;
import com.module.request.user.UserAttribute;
import com.module.request.user.UserAttributeUserAttributeValue;
import com.module.request.user.UserAttributeValue;
import com.wulai.knowledge.*;
import org.junit.Test;
import java.util.Map;


public class TestKnowledge {

    DefaultClient defaultClient=new DefaultClient();

    //相似问接口测试(4)
    @Test
    public void TestCreateSimilarQuestionList() throws ServerException,ClientException{

        System.out.println("相似问接口测试。。。");

        //查询相似问
        QuerySimilarQuestionList querySimilarQuestionList=new QuerySimilarQuestionList();
        querySimilarQuestionList.setPage(1);
        querySimilarQuestionList.setPageSize(3);
        querySimilarQuestionList.setKnowledgeId("991640");

        Map map4 = querySimilarQuestionList.request(defaultClient);
        int i =0;
        for (Object o :map4.keySet()
        ) {
            System.out.println(o);
            System.out.println(map4.get(o));
        }


        //创建相似问
        CreateSimilarQuestion createSimilarQuestion=new CreateSimilarQuestion();
        SimilarQuestion similarQuestion=new SimilarQuestion();
        similarQuestion.setKnowledge_id("1583014");
        similarQuestion.setQuestion("nihaowa");
        createSimilarQuestion.setSimilarQuestion(similarQuestion);
        Map map= createSimilarQuestion.request(defaultClient);
        for (Object o :map.keySet()
        ) {
            System.out.println(o);
            System.out.println(map.get(o));
        }
        JSONObject json=(JSONObject) JSONObject.parse(map.get("similar_question").toString());

        similarQuestion.setId(json.get("id").toString());
        similarQuestion.setQuestion("buhaowa");

        //修改相似问
        UpdateSimilarQuestion updateSimilarQuestion=new UpdateSimilarQuestion();
        updateSimilarQuestion.setSimilarQuestion(similarQuestion);
        Map map1= updateSimilarQuestion.request(defaultClient);
        for (Object o1 :map1.keySet()
        ) {
            System.out.println(o1);
            System.out.println(map.get(o1));
        }

        //删除相似问
        DeleteSimilarQuestion deleteSimilarQuestion=new DeleteSimilarQuestion();
        deleteSimilarQuestion.setId(json.get("id").toString());
        int code=deleteSimilarQuestion.request(defaultClient);
        System.out.println(code);

        System.out.println("相似问接口测试完成");
    }


    //知识点接口测试(5)
    @Test
    public void TestKnowledgeTagKnowledge() throws ServerException,ClientException{
        System.out.println("知识点接口测试。。。");
        //1.查询知识点分类列表
        QueryKnowledgeTagsList queryKnowledgeTagsList=new QueryKnowledgeTagsList();
        queryKnowledgeTagsList.setPage(1);
        queryKnowledgeTagsList.setPageSize(5);
        queryKnowledgeTagsList.setParentKTagId("116469");
        Map map6=null;
        try {
            map6=queryKnowledgeTagsList.request(defaultClient);
        }catch (ServerException|ClientException e){

            e.printStackTrace();
        }
        for (Object o :map6.keySet()
        ) {
            System.out.println(map6.get(o).toString());
        }


        //2.查询知识点
        QueryKnowledgeItemsList queryKnowledgeItemsList=new QueryKnowledgeItemsList();
        queryKnowledgeItemsList.setPage(1);
        queryKnowledgeItemsList.setPageSize(200);


        Map map5 =queryKnowledgeItemsList.request(defaultClient);
        for (Object p:map5.keySet()
        ) {
            System.out.println(map5.get(p));
        }


        Knowledge knowledge=new Knowledge();
        knowledge.setStatus(true);
        knowledge.setRespond_all(true);
        knowledge.setStandard_question("测试默认知识点接口813");
        knowledge.setMaintained_by_user_attribute_group(false);
        KnowledgeTagKnowledge knowledgeTagKnowledge=new KnowledgeTagKnowledge();
        knowledgeTagKnowledge.setKnowledge(knowledge);
        knowledgeTagKnowledge.setKnowledge_tag_id("116470");

        //3.创建知识点
        CreateKnowledgeTagKnowledge createKnowledgeTagKnowledge=new CreateKnowledgeTagKnowledge();
        createKnowledgeTagKnowledge.setKnowledgeTagKnowledge(knowledgeTagKnowledge);
        Map map=null;

        map=createKnowledgeTagKnowledge.request(defaultClient);
        for (Object o:map.keySet()
             ) {
            System.out.println(o);
            System.out.println(map.get(o));
        }
        JSONObject jsonObject = (JSONObject) JSONObject.parse(map.get("knowledge_tag_knowledge").toString());
        JSONObject jsonObject1 =  JSONObject.parseObject(jsonObject.get("knowledge").toString());
        String id =jsonObject1.get("id").toString();

        //4.更新知识点
        knowledge.setRespond_all(false);
        knowledge.setStandard_question("测试默认知识点接口833");
        knowledge.setId(id);
        UpdateKnowledge updateKnowledge=new UpdateKnowledge();
        updateKnowledge.setKnowledge(knowledge);
        Map map1 =updateKnowledge.request(defaultClient);
        System.out.println("hh"+map1.values());


        //5.删除知识点
        DeleteKnowledge deleteKnowledge =new DeleteKnowledge();
        deleteKnowledge.setId(Integer.valueOf(id));
        int i =deleteKnowledge.request(defaultClient);
        System.out.println(i);

        System.out.println("知识点接口测试完成");
    }


    //用户属性组接口测试(3)
    @Test
    public void TestUserAttributeGroupItems() throws ServerException ,ClientException{
        System.out.println("用户属性组接口测试");
        //1.查询用户属性组列表
        QueryUserAttributeGroupItemsList queryUserAttributeGroupItemsList=new QueryUserAttributeGroupItemsList();
        queryUserAttributeGroupItemsList.setPageSize(20);
        queryUserAttributeGroupItemsList.setPage(1);
        Map map=queryUserAttributeGroupItemsList.request(defaultClient);
        for (Object o : map.keySet()
             ) {
            System.out.println(map.get(o));
        }

        //2.创建用户属性组
            //创建用户属性id
        UserAttribute userAttribute=new UserAttribute("101945");
            //创建用户属性值
        UserAttributeValue userAttributeValue=new UserAttributeValue("测试测试");
            //创建用户属性组对象
        UserAttributeUserAttributeValue userAttributeUserAttributeValue=new UserAttributeUserAttributeValue();

        userAttributeUserAttributeValue.setUser_attribute(userAttribute);
        userAttributeUserAttributeValue.setUser_attribute_value(userAttributeValue);


        UserAttributeGroupItem userAttributeGroupItem=new UserAttributeGroupItem();
        UserAttributeGroup userAttributeGroup=new UserAttributeGroup();
        userAttributeGroup.setName("接口测试");

        userAttributeGroupItem.setUser_attribute_group(userAttributeGroup);
        userAttributeGroupItem.addUserAttributeUserAttributeValue(userAttributeUserAttributeValue);

        CreateUserAttributeGroupItems createUserAttributeGroupItems=new CreateUserAttributeGroupItems();
        createUserAttributeGroupItems.setUserattributeGroupItem(userAttributeGroupItem);

        Map map1=createUserAttributeGroupItems.request(defaultClient);

        for (Object o : map1.keySet()
        ) {
            System.out.println(map1.get(o));
        }

        //3.更新属性组
        UpdateUserAttributeGroupItems updateUserAttributeGroupItems=new UpdateUserAttributeGroupItems();
        updateUserAttributeGroupItems.setUser_attribute_group_item(userAttributeGroupItem);
        Map map2=updateUserAttributeGroupItems.request(defaultClient);
        for (Object o:map2.keySet()
             ) {
            System.out.println(map.get(o));
        }

    }

    //属性组回复接口(4)
    @Test
    public void TestQueryUserAttributeGroupAnswersList() throws  ServerException,ClientException{

        //1.查询属性组回复列表
        QueryUserAttributeGroupAnswersList queryUserAttributeGroupAnswersList=new QueryUserAttributeGroupAnswersList();
        queryUserAttributeGroupAnswersList.setPageSize(1);
        queryUserAttributeGroupAnswersList.setPage(1);
        Filter filter =new Filter();
        filter.setKnowledge_id("990308");
        filter.setUser_attribute_group_id("0");
        queryUserAttributeGroupAnswersList.setFilter(filter);
        Map map=queryUserAttributeGroupAnswersList.request(defaultClient);
        System.out.println("查询属性组回复列表");
        for (Object o :map.keySet()
             ) {

            System.out.println(o);
            System.out.println(map.get(o));
        }


        //2.创建属性组回复
        Text text=new Text("测试回复");
        MsgBody msgBody=new MsgBody(text);
        Answer answer=new Answer();
        answer.setId("3482630");
        answer.setMsg_body(msgBody);
        answer.setKnowledge_id("990308");

        UserAttributeGroupAnswer userAttributeGroupAnswer=new UserAttributeGroupAnswer();
        userAttributeGroupAnswer.setUser_attribute_group_id("0");
        userAttributeGroupAnswer.setAnswer(answer);


        CreateUserAttributeGroupAnswer createUserAttributeGroupAnswer=new CreateUserAttributeGroupAnswer();
        createUserAttributeGroupAnswer.setUserAttributeGroupAnswer(userAttributeGroupAnswer);

//        Map map1=createUserAttributeGroupAnswer.request(defaultClient);
//        System.out.println("创建属性组回复");
//        for (Object o :map1.keySet()
//             ) {
//            System.out.println(map1.get(o));
//        }


        //3.更新属性组回复
        UpdateUserAttributeGroupAnswer updateUserAttributeGroupAnswer =new UpdateUserAttributeGroupAnswer();
        updateUserAttributeGroupAnswer.setUser_attribute_group_answer(userAttributeGroupAnswer);

        Map map2=updateUserAttributeGroupAnswer.request(defaultClient);
        System.out.println("更新属性组回复");
        for (Object o :map2.keySet()
             ) {
            System.out.println("map2");
            System.out.println(o);
            System.out.println(map2.get(o));
        }

        JSONObject json=JSONObject.parseObject(map2.get("user_attribute_group_answer").toString());
        System.out.println(json);
        JSONObject jsonObject=JSONObject.parseObject(json.get("answer").toString());


        //4.删除属性组回复
//        DeleteUserAttriButeGroupAnswer deleteUserAttriButeGroupAnswer=new DeleteUserAttriButeGroupAnswer();
//        deleteUserAttriButeGroupAnswer.setId(jsonObject.get("id").toString());
//        int code= deleteUserAttriButeGroupAnswer.request(defaultClient);
//        System.out.println(code);

    }



}
