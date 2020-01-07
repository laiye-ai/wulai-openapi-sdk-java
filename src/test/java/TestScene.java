import com.WulaiClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.request.scene.Block;
import com.module.request.scene.*;
import com.module.request.scene.condition.EqualTo;
import com.module.response.scene.*;
import com.util.Credentials;
import com.wulai.scene.*;
import org.junit.AfterClass;
import org.junit.Test;

import java.net.URI;
import java.util.Iterator;
import java.util.List;

public class TestScene {
    //private static WulaiClient wulaiClient = new WulaiClient();
    private static WulaiClient wulaiClient = new WulaiClient(URI.create("http://preopenapi.wul.ai/"), new Credentials());
    private static int blockId;
    private static int scene_id;
    private static int intent_id;
    private static int trigger_id;
    private static int slot_id;
    private static int informId;
    private static int endId;

    @Test
    public void TestScene() throws ServerException, ClientException {
        Scene scene = new Scene();
        scene.setName("测试创建场景");
        scene.setDescription("open api test");
        scene.setIntentSwitchMode("INTENT_SWITCH_MODE_STAY");

        //1.create scene
        System.out.println("+++++测试创建场景++++");
        CreateScene createScene = new CreateScene();
        createScene.setScene(scene);
        Scene scene1 = createScene.request(wulaiClient);
        scene_id = scene1.getId();
        //update scene
        System.out.println("+++++测试更新场景++++");
        UpdateScene updateScene = new UpdateScene();
        scene.setName("测试更新场景");
        scene.setId(scene_id);
        updateScene.setScene(scene);
        Scene scene2 = updateScene.request(wulaiClient);
        if (!scene2.getName().equals("测试更新场景")) {
            throw new ServerException("1", " updateScene error", 1);
        }

        //2.create intent
        System.out.println("+++++测试创建意图++++");
        CreateIntent createIntent = new CreateIntent();
        Intent intent = new Intent();
        intent.setSceneId(scene_id);
        intent.setName("测试创建意图");
        intent.setLifespanMins(2);
        createIntent.setIntent(intent);
        Intent intent1 = createIntent.request(wulaiClient);

        intent_id = intent1.getId();

        System.out.println("+++++测试更新意图++++");
        UpdateIntent updateIntent = new UpdateIntent();
        intent.setId(intent_id);
        intent.setName("询问天气");
        updateIntent.setIntent(intent);
        Intent intent2 = updateIntent.request(wulaiClient);
        if (!intent2.getName().equals("询问天气") || intent2.getId() != intent_id) {
            throw new ServerException("1", " createIntent error", 1);
        }

        //3.create trigger
        System.out.println("+++++测试创建触发器++++");
        CreateIntentTrigger createIntentTrigger = new CreateIntentTrigger();
        IntentTrigger intentTrigger = new IntentTrigger();
        intentTrigger.setIntentId(intent_id);
        intentTrigger.setText("测试创建触发器");
        intentTrigger.setType("TRIGGER_TYPE_EXACT_MATCH_KEYWORD");
        createIntentTrigger.setIntentTrigger(intentTrigger);
        IntentTrigger intentTrigger1 = createIntentTrigger.request(wulaiClient);
        trigger_id = intentTrigger1.getId();

        //update trigger
        System.out.println("+++++测试更新触发器++++");
        UpdateIntentTrigger updateIntentTrigger = new UpdateIntentTrigger();
        intentTrigger.setIntentId(intent_id);
        intentTrigger.setText("天气怎么样");
        intentTrigger.setId(trigger_id);
        updateIntentTrigger.setIntent_trigger(intentTrigger);
        IntentTrigger intentTrigger2 = updateIntentTrigger.request(wulaiClient);
        if (!intentTrigger2.getText().equals("天气怎么样")) {
            throw new ServerException("1", "update IntentTrigger error", 1);
        }

        //4.create slot
        System.out.println("+++++测试创建词槽++++");
        Slot slot = new Slot();
        slot.setSceneId(scene_id);
        slot.setName("测试创建词槽");
        slot.setQuerySlotFilling(false);

        CreateSlot createSlot = new CreateSlot();
        createSlot.setSlot(slot);
        Slot slot1 = createSlot.request(wulaiClient);
        slot_id = slot1.getId();

        System.out.println("+++++测试更新词槽++++");
        UpdateSlot updateSlot = new UpdateSlot();
        slot.setId(slot_id);
        slot.setName("城市");
        updateSlot.setSlot(slot);
        Slot slot2 = updateSlot.request(wulaiClient);
        if (!slot2.getName().equals("城市")) {
            throw new ServerException("1", "update Slot error", 1);
        }

        //5.create RequestBlock
        System.out.println("+++++测试创建询问单元++++");
        CreateRequestBlock createRequestBlock = new CreateRequestBlock();
        Block requestBlock = new Block();
        requestBlock.setName("创建询问填槽单元");
        requestBlock.setIntentID(intent_id);
        requestBlock.setSlotID(slot_id);
        requestBlock.setMode("RESPONSE_RANDOM");

        createRequestBlock.setBlock(requestBlock);
        Block createQuestResponse = createRequestBlock.request(wulaiClient);
        blockId = createQuestResponse.getID();

        System.out.println("+++++测试更新询问单元++++");
        UpdateRequestBlock updateRequestBlock = new UpdateRequestBlock();
        createQuestResponse.setDefaultSlotValue("111");
        createQuestResponse.setName("获取城市信息");
        updateRequestBlock.setBlock(createQuestResponse);
        Block UpdateRequestResponse = updateRequestBlock.request(wulaiClient);
        if (!UpdateRequestResponse.getName().equals("获取城市信息")) {
            throw new ServerException("1", "update BlockRequestBlock error", 1);
        }

        System.out.println("+++++测试创建消息发送单元++++");
        CreateInformBlock createInformBlock = new CreateInformBlock();
        Block informBlock = new Block();
        informBlock.setName("创建消息发送单元");
        informBlock.setIntentID(intent_id);
        informBlock.setMode("RESPONSE_RANDOM");
        informBlock.setDefaultSlotValue("default response");
        createInformBlock.setBlock(informBlock);
        Block createInformResponse = createInformBlock.request(wulaiClient);
        informId = createInformResponse.getID();

        System.out.println("+++++测试更新消息发送单元++++");
        UpdateInformBlock updateInformBlock = new UpdateInformBlock();
        createInformResponse.setDefaultSlotValue("hello ,上海天晴");
        createInformResponse.setName("发送天气信息");
        updateInformBlock.setBlock(createInformResponse);
        Block updateInformResponse = updateInformBlock.request(wulaiClient);
        if (!updateInformResponse.getName().equals("发送天气信息")) {
            throw new ServerException("1", "update BlockInformBlock error", 1);
        }

        System.out.println("++++++获取单元详情++++++");
        GetInformBlock getInformBlock = new GetInformBlock();
        getInformBlock.setId(informId);
        Block informBlockdetail = getInformBlock.request(wulaiClient);
        if (!informBlockdetail.getName().equals("发送天气信息")) {
            throw new ServerException("1", "get InformBlock error", 1);
        }


        Condition condition = new Condition();
        condition.setEqualTo(new EqualTo("测试condition"));
        Connection connection = new Connection();
        connection.setFromBlockID(blockId);
        connection.setToBlockID(informId);
        connection.setCondition(condition);
        Relation relation = new Relation();
        relation.setConnection(connection);
        relation.setIntentID(intent_id);

        System.out.println("+++++测试创建单元关系++++");
        CreateBlockRelation createBlockRelation = new CreateBlockRelation();
        createBlockRelation.setRelation(relation);
        Relation relation1 = createBlockRelation.request(wulaiClient);
        if (relation1.getConnection().getToBlockID() != informId) {
            throw new ServerException("1", "createBlockRelation error", 1);
        }

        System.out.println("+++++测试创建终点单元++++");
        CreateEndBlock createEndBlock = new CreateEndBlock();
        Block endblock = new Block();
        Last last = new Last();
        Action action = new Action();
        action.setLast(last);
        endblock.setAction(action);
        endblock.setName("测试创建意图终点");
        endblock.setIntentID(intent_id);
        createEndBlock.setBlock(endblock);
        Block createEndResponse = createEndBlock.request(wulaiClient);
        endId = createEndResponse.getID();

        System.out.println("+++++测试更新终点单元++++");
        UpdateEndBlock updateEndBlock = new UpdateEndBlock();
        createEndResponse.setName("天气意图结束");
        updateEndBlock.setBlock(createEndResponse);
        Block endBlockResponse = updateEndBlock.request(wulaiClient);
        if (!endBlockResponse.getName().equals("天气意图结束")) {
            throw new ServerException("1", "create BlockEndBlock error", 1);
        }

        condition.setEqualTo(new EqualTo("测试condition2"));
        connection.setFromBlockID(informId);
        connection.setToBlockID(endId);
        connection.setCondition(condition);
        relation.setConnection(connection);
        createBlockRelation.setRelation(relation);
        relation1 = createBlockRelation.request(wulaiClient);
        if (relation1.getConnection().getToBlockID() != endId) {
            throw new ServerException("1", "createBlockRelation error", 1);
        }

//        上线任务机器人
        System.out.println("+++++测试更新意图+++");
        UpdateIntentStatus updateIntentStatus = new UpdateIntentStatus();
        updateIntentStatus.setStatus(true);
        updateIntentStatus.setIntentId(intent_id);
        updateIntentStatus.setFirstBlockId(blockId);
        IntentStatus intentStatus = updateIntentStatus.request(wulaiClient);
        if (intentStatus.getUpdateTime() == null) {
            throw new ServerException("1", "update IntentStatus error", 1);
        }

        System.out.println("+++++查询单元列表++++++");
        QueryBlockList queryBlockList = new QueryBlockList();
        queryBlockList.setIntent_id(intent_id);
        queryBlockList.setPage(1);
        queryBlockList.setPageSize(20);
        List<Block> blockList = queryBlockList.request(wulaiClient);
        Iterator<Block> blockIterator = blockList.iterator();
        while (blockIterator.hasNext()) {
            Block b = blockIterator.next();
            System.out.println("+++++打印单元信息++++++");
            System.out.println(b.getID());
            System.out.println(b.getName());
        }

        System.out.println("+++++获取词槽++++++");
        GetSlot getSlot = new GetSlot();
        getSlot.setId(slot_id);
        Slot slot0 = getSlot.request(wulaiClient);
        if (slot0.getSceneId() != scene_id) {
            throw new ServerException("1", "1", 1);
        }

        System.out.println("+++++获取询问填槽单元++++++");
        GetRequestBlock getRequestBlock = new GetRequestBlock();
        getRequestBlock.setId(blockId);
        Block requestBlock1 = getRequestBlock.request(wulaiClient);
        if (!requestBlock1.getName().equals("获取城市信息")) {
            throw new ServerException("1", "1", 1);
        }

        System.out.println("+++++获取意图终点单元++++++");
        GetEndBlock getEndBlock = new GetEndBlock();
        getEndBlock.setId(endId);
        Block endBlock = getEndBlock.request(wulaiClient);
        if (!endBlock.getName().equals("天气意图结束")) {
            throw new ServerException("1", "1", 1);
        }

        System.out.println("+++++查询词槽列表++++++");
        QuerySlotList querySlotList = new QuerySlotList();
        querySlotList.setSceneId(scene_id);
        querySlotList.setPage(1);
        querySlotList.setPageSize(20);
        List<Slot> slotList = querySlotList.request(wulaiClient);
        Iterator<Slot> slotIterator = slotList.iterator();
        while (slotIterator.hasNext()) {
            Slot s = slotIterator.next();
            System.out.println("+++++打印词槽信息++++++");
            System.out.println(s.getName());
            System.out.println(s.getId());
        }

        System.out.println("+++++查询触发器列表++++++");
        QueryIntentTriggerList queryIntentTriggerList = new QueryIntentTriggerList();
        queryIntentTriggerList.setIntentId(intent_id);
        queryIntentTriggerList.setPage(1);
        queryIntentTriggerList.setPage_size(20);
        List<IntentTrigger> intentTriggerList = queryIntentTriggerList.request(wulaiClient);
        Iterator<IntentTrigger> intentTriggerIterator = intentTriggerList.iterator();

        while (intentTriggerIterator.hasNext()) {
            IntentTrigger i = intentTriggerIterator.next();
            System.out.println("+++++打印触发器信息++++++");
            System.out.println(i.getId());
            System.out.println(i.getText());
        }

        System.out.println("+++++查询词槽数据来源++++++");
        QuerySlotDataSourceList querySlotDataSourceList = new QuerySlotDataSourceList();
        querySlotDataSourceList.setSlotId(slot_id);
        List<DataSource> dataSourceList = querySlotDataSourceList.request(wulaiClient);
        Iterator<DataSource> dataSourceIterator = dataSourceList.iterator();
        while (dataSourceIterator.hasNext()) {
            DataSource dataSource = dataSourceIterator.next();
            System.out.println("+++++打印词槽来源++++++");
            System.out.println(dataSource.getSlotID());
            System.out.println(dataSource.getEntityID());
        }

        System.out.println("+++++查询场景列表++++++");
        QuerySceneList querySceneList = new QuerySceneList();
        List<Scene> sceneList = querySceneList.request(wulaiClient);
        Iterator<Scene> sceneIterator = sceneList.iterator();
        while (sceneIterator.hasNext()) {
            Scene s = sceneIterator.next();
            System.out.println("+++++打印场景信息++++++");
            System.out.println(s.getId());
            System.out.println(s.getName());
        }

        System.out.println("+++++查询意图列表++++++");
        QueryIntentList queryIntentList = new QueryIntentList();
        queryIntentList.setScene_id(scene_id);
        List<Intent> intentList = queryIntentList.request(wulaiClient);
        Iterator<Intent> intentIterator=intentList.iterator();
        while (intentIterator.hasNext()){
            Intent i=intentIterator.next();
            System.out.println("+++++打印意图信息++++++");
            System.out.println(i.getId());
            System.out.println(i.getName());
        }

        System.out.println("+++++查询任务待审核消息列表++++++");
        QueryIntentTriggerLearningList queryIntentTriggerLearningList = new QueryIntentTriggerLearningList();
        queryIntentTriggerLearningList.setPage(1);
        queryIntentTriggerLearningList.setPageSize(20);
        List<QueryItem> queryItems = queryIntentTriggerLearningList.request(wulaiClient);
        Iterator<QueryItem> queryItemIterator=queryItems.iterator();
        while (queryItemIterator.hasNext()){
            QueryItem queryItem =  queryItemIterator.next();
            System.out.println("+++++打印待审核消息++++++");
            System.out.println(queryItem.getID());
            System.out.println(queryItem.getContent());
        }

    }

    @Test
    public void TestQuery() throws ServerException, ClientException {

    }

    @AfterClass
    public static void delete() throws ServerException, ClientException {

        //delete inform block


        //delete request block
        DeleteBlock deleteBlock = new DeleteBlock();
        deleteBlock.setId(blockId);
        if (200 == deleteBlock.request(wulaiClient)) {
            System.out.println("delete request block ok");
        }
        deleteBlock.setId(informId);
        deleteBlock.request(wulaiClient);

        //delete slot
        DeleteSlot deleteSlot = new DeleteSlot();
        deleteSlot.setId(slot_id);

        if (deleteSlot.request(wulaiClient) == 200) {
            System.out.println("delete slot ok");
        }

        //delete trigger
        DeleteIntentTrigger deleteIntentTrigger = new DeleteIntentTrigger();
        deleteIntentTrigger.setId(trigger_id);
        if (200 == deleteIntentTrigger.request(wulaiClient)) {
            System.out.println("delete trigger ok");
        }


        //delete intent
        DeleteIntent deleteIntent = new DeleteIntent();
        deleteIntent.setId(intent_id);

        if (200 == deleteIntent.request(wulaiClient)) {
            System.out.println("delete intent ok");
        }

        //delete scene
        DeleteScene deleteScene = new DeleteScene();
        deleteScene.setId(scene_id);
        if (200 == deleteScene.request(wulaiClient)) {
            System.out.println("delete scene ok");
        }
    }


}
