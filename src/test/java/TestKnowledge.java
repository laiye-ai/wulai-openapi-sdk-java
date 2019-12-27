import com.WulaiClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.request.knowledge.*;
import com.module.request.msg.MsgBody;
import com.module.request.msg.Text;
import com.module.request.user.UserAttribute;
import com.module.request.user.UserAttributeUserAttributeValue;
import com.module.request.user.UserAttributeValue;
import com.module.response.knowledge.*;
import com.wulai.knowledge.*;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class TestKnowledge {

    private static WulaiClient wulaiClient = new WulaiClient();
    @Test
    public void TestCreateSimilarQuestionList() throws ServerException, ClientException {

        System.out.println("SimilarQuestion  start");

        //QuerySimilarQuestionList
        QuerySimilarQuestionList querySimilarQuestionList = new QuerySimilarQuestionList();
        querySimilarQuestionList.setPage(1);
        querySimilarQuestionList.setPageSize(3);
        querySimilarQuestionList.setKnowledgeId("991640");

        SimilarQuestionList similarQuestionList = querySimilarQuestionList.request(wulaiClient);
        if (similarQuestionList.getSimilarQuestions() == null) {
            throw new ServerException("1", "QuerySimilarQuestionList error", 500);
        }

        String k_id="1583014";
        // CreateSimilarQuestion
        CreateSimilarQuestion createSimilarQuestion = new CreateSimilarQuestion();
        SimilarQuestion similarQuestion = new SimilarQuestion();
        similarQuestion.setKnowledgeId(k_id);
        similarQuestion.setQuestion("nihaowa");
        createSimilarQuestion.setSimilarQuestion(similarQuestion);
        SimilarQuestion similarQuestion1 = createSimilarQuestion.request(wulaiClient);
        if (!similarQuestion1.getKnowledgeId().equals("1583014")) {
            throw new ServerException("1", " CreateSimilarQuestion error", 500);
        }
        String similarQuestionId = similarQuestion1.getId();


        similarQuestion.setId(similarQuestionId);
        similarQuestion.setQuestion("buhaowan");

        //UpdateSimilarQuestion
        UpdateSimilarQuestion updateSimilarQuestion = new UpdateSimilarQuestion();
        updateSimilarQuestion.setSimilarQuestion(similarQuestion);
        SimilarQuestion similarQuestion2 = updateSimilarQuestion.request(wulaiClient);
        if (!similarQuestion2.getKnowledgeId().equals("1583014")) {
            throw new ServerException("1", "UpdateSimilarQuestion error", 500);
        }

        //deleteSimilarQuestion
        DeleteSimilarQuestion deleteSimilarQuestion = new DeleteSimilarQuestion();
        deleteSimilarQuestion.setId(similarQuestionId);
        int code2 = deleteSimilarQuestion.request(wulaiClient);
        if (200 != code2) {
            throw new ServerException("1", "deleteSimilarQuestion error", 500);
        } else {
            System.out.println("delete SimilarQuestion success");
        }

    }


    //TestKnowledgeTagKnowledge(5)
    @Test
    public void TestKnowledgeTagKnowledge() throws ServerException, ClientException {
        System.out.println("Knowledge interface start 。。。");
        //1.QueryKnowledgeTagsList
        QueryKnowledgeTagsList queryKnowledgeTagsList = new QueryKnowledgeTagsList();
        queryKnowledgeTagsList.setPage(1);
        queryKnowledgeTagsList.setPageSize(5);
        queryKnowledgeTagsList.setParentKTagId("116469");
        KnowledgeTagsList knowledgeTagsList = queryKnowledgeTagsList.request(wulaiClient);
        if (knowledgeTagsList.getKnowledgeTags() == null) {
            throw new ServerException("1", "QueryKnowledgeTagsList error", 1);
        }


        //2.QueryKnowledgeItemsList
        QueryKnowledgeItemsList queryKnowledgeItemsList = new QueryKnowledgeItemsList();
        queryKnowledgeItemsList.setPage(1);
        queryKnowledgeItemsList.setPageSize(200);


        KnowledgeItemsList knowledgeItemsList = queryKnowledgeItemsList.request(wulaiClient);
        if (knowledgeItemsList.getKnowledgeItems() == null) {
            throw new ServerException("1", "QueryKnowledgeItemsList error", 1);
        }


        Knowledge knowledge = new Knowledge();
        knowledge.setStatus(true);
        knowledge.setRespondAll(true);
        knowledge.setStandardQuestion("测试默认知识点接口813");
        knowledge.setMaintainedByUserAttributeGroup(false);
        KnowledgeTagKnowledge knowledgeTagKnowledge = new KnowledgeTagKnowledge();
        knowledgeTagKnowledge.setKnowledge(knowledge);
        knowledgeTagKnowledge.setKnowledgeTagId("116470");

        //3.create knowledge
        CreateKnowledgeTagKnowledge createKnowledgeTagKnowledge = new CreateKnowledgeTagKnowledge();
        createKnowledgeTagKnowledge.setKnowledgeTagKnowledge(knowledgeTagKnowledge);


        KnowledgeTagKnowledge knowledgeTagKnowledge1 = createKnowledgeTagKnowledge.request(wulaiClient);
        if (knowledgeTagKnowledge1.getKnowledge() == null) {
            throw new ServerException("1", "create KnowledgeItemsList error", 1);
        }
        String id = knowledgeTagKnowledge1.getKnowledge().getId();
        int knowledgeId = Integer.valueOf(id);

        //4.update knowledge
        knowledge.setRespondAll(false);
        knowledge.setStandardQuestion("测试默认知识点接口833");

        knowledge.setId(knowledgeId + "");
        UpdateKnowledge updateKnowledge = new UpdateKnowledge();
        updateKnowledge.setKnowledge(knowledge);
        Knowledge knowledge1 = updateKnowledge.request(wulaiClient);
        if (!knowledge1.getId().equals(knowledgeId + "")) {
            throw new ServerException("1", "update knowledge error", 1);
        }

        DeleteKnowledge deleteKnowledge = new DeleteKnowledge();
        deleteKnowledge.setId(knowledgeId);
        int code1 = deleteKnowledge.request(wulaiClient);
        if (200 != code1) {
            throw new ServerException("1", "delete knowledge error", 1);
        } else {
            System.out.println("delete knowledge success");
        }

        System.out.println("Test knowledge finished");
    }


    //用户属性组接口测试(3)
    @Test
    public void TestUserAttributeGroupItems() throws ServerException, ClientException {
        System.out.println("UserAttributeGroupItems start");
        //1.QueryUserAttributeGroupItemsList
        QueryUserAttributeGroupItemsList queryUserAttributeGroupItemsList = new QueryUserAttributeGroupItemsList();
        queryUserAttributeGroupItemsList.setPageSize(20);
        queryUserAttributeGroupItemsList.setPage(1);
        UserAttributeGroupItemsList userAttributeGroupItemsList = queryUserAttributeGroupItemsList.request(wulaiClient);
        if (userAttributeGroupItemsList.getUserAttributeGroupItems() == null) {
            throw new ServerException("1", "Query UserAttributeGroupItemsList error", 1);
        }
        List<UserAttributeGroupItem> userAttributeGroupItems = userAttributeGroupItemsList.getUserAttributeGroupItems();
        UserAttributeGroupItem userAttributeGroupItem = userAttributeGroupItems.get(0);
        String usergroup_id = userAttributeGroupItem.getUserAttributeGroup().getId();

        UserAttributeGroup userAttributeGroup2 = userAttributeGroupItem.getUserAttributeGroup();


        //2.创建用户属性组
        //创建用户属性id
        UserAttribute userAttribute = new UserAttribute();
        userAttribute.setId("101946");
        //创建用户属性值
        UserAttributeValue userAttributeValue = new UserAttributeValue();
        userAttributeValue.setName("吃肉"+new Date().getTime());
        //创建用户属性组对象
        UserAttributeUserAttributeValue userAttributeUserAttributeValue = new UserAttributeUserAttributeValue();

        userAttributeUserAttributeValue.setUserAttribute(userAttribute);
        userAttributeUserAttributeValue.setUserAttributeValue(userAttributeValue);


        UserAttributeGroupItem userAttributeGroupItem1 = new UserAttributeGroupItem();
        UserAttributeGroup userAttributeGroup = new UserAttributeGroup();
        userAttributeGroup.setName("接口测试"+new Date().getTime());

        userAttributeGroupItem1.setUserAttributeGroup(userAttributeGroup);
        userAttributeGroupItem1.addUserAttributeUserAttributeValue(userAttributeUserAttributeValue);

        CreateUserAttributeGroupItems createUserAttributeGroupItems = new CreateUserAttributeGroupItems();
        createUserAttributeGroupItems.setUserattributeGroupItem(userAttributeGroupItem1);

        UserAttributeGroupItem userAttributeGroupItem3 = createUserAttributeGroupItems.request(wulaiClient);
        if (userAttributeGroupItem3.getUserAttributeGroup() == null) {
            throw new ServerException("1", "Create UserAttributeGroupItems error", 1);
        }


        //3.更新属性组
        UpdateUserAttributeGroupItems updateUserAttributeGroupItems = new UpdateUserAttributeGroupItems();
        userAttributeGroupItem.setUserAttributeGroup(userAttributeGroup2);
        updateUserAttributeGroupItems.setUser_attribute_group_item(userAttributeGroupItem);
        UserAttributeGroupItem userAttributeGroupItem2 = updateUserAttributeGroupItems.request(wulaiClient);
        if (userAttributeGroupItem2.getUserAttributeGroup() == null) {
            throw new ServerException("1", "Update UserAttributeGroupItems error", 1);
        }

    }

    //属性组回复接口(4)
    @Test
    public void TestQueryUserAttributeGroupAnswersList() throws ServerException, ClientException {


        //1.QueryUserAttributeGroupAnswersList
        QueryUserAttributeGroupAnswersList queryUserAttributeGroupAnswersList = new QueryUserAttributeGroupAnswersList();
        queryUserAttributeGroupAnswersList.setPageSize(1);
        queryUserAttributeGroupAnswersList.setPage(1);
        Filter filter = new Filter();
        filter.setKnowledgeId("990308");
        filter.setUserAttributeGroupId("0");
        queryUserAttributeGroupAnswersList.setFilter(filter);
        UserAttributeGroupAnswersList attributeGroupAnswersList = queryUserAttributeGroupAnswersList.request(wulaiClient);
        if (attributeGroupAnswersList.getUser_attribute_group_answers() == null) {
            throw new ServerException("1", "Query UserAttributeGroupAnswersList error", 1);
        }


        //2.CreateUserAttributeGroupAnswer
        Text text = new Text("测试回复");
        MsgBody msgBody = new MsgBody();
        msgBody.setText(text);
        Answer answer = new Answer();
        answer.setMsgBody(msgBody);
        answer.setKnowledgeId("990308");

        UserAttributeGroupAnswer userAttributeGroupAnswer = new UserAttributeGroupAnswer();
        userAttributeGroupAnswer.setUserAttributeGroupId("0");
        userAttributeGroupAnswer.setAnswer(answer);

        CreateUserAttributeGroupAnswer createUserAttributeGroupAnswer = new CreateUserAttributeGroupAnswer();
        createUserAttributeGroupAnswer.setUserAttributeGroupAnswer(userAttributeGroupAnswer);
        UserAttributeGroupAnswer userAttributeGroupAnswer1 = createUserAttributeGroupAnswer.request(wulaiClient);
        if (userAttributeGroupAnswer1.getAnswer() == null) {
            throw new ServerException("1", "Create UserAttributeGroupAnswer error", 1);
        }


        //3.UpdateUserAttributeGroupAnswer
        UpdateUserAttributeGroupAnswer updateUserAttributeGroupAnswer = new UpdateUserAttributeGroupAnswer();
        answer.setId(userAttributeGroupAnswer1.getAnswer().getId());
        answer.setKnowledgeId(userAttributeGroupAnswer1.getAnswer().getKnowledgeId());
        MsgBody msgBody1=new MsgBody();
        msgBody1.setText(new Text("更新测试回复"));
        answer.setMsgBody(msgBody1);
        userAttributeGroupAnswer.setAnswer(answer);
        updateUserAttributeGroupAnswer.setUser_attribute_group_answer(userAttributeGroupAnswer);

        UserAttributeGroupAnswer userAttributeGroupAnswer2 = updateUserAttributeGroupAnswer.request(wulaiClient);
        if (userAttributeGroupAnswer2.getAnswer() == null) {
            throw new ServerException("1", "Update UserAttributeGroupAnswer error", 1);
        }
        String answerId = userAttributeGroupAnswer2.getAnswer().getId();

        // 3.DeleteUserAttriButeGroupAnswer
        DeleteUserAttriButeGroupAnswer deleteUserAttriButeGroupAnswer = new DeleteUserAttriButeGroupAnswer();
        deleteUserAttriButeGroupAnswer.setId(answerId);
        int code3 = deleteUserAttriButeGroupAnswer.request(wulaiClient);
        if (200 != code3) {
            throw new ServerException("1", "Delete UserAttributeGroupAnswer error", 1);
        } else {
            System.out.println("delete UserAttributeGroupAnswer success");
        }

    }


}
