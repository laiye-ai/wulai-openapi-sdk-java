import com.WulaiClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.request.scene.*;
import com.wulai.scene.*;
import org.junit.AfterClass;
import org.junit.Test;

public class TestScene {
    private static WulaiClient wulaiClient = new WulaiClient();
    private static int blockId;
    private static int scene_id;
    private static int intent_id;
    private static int trigger_id;
    private static int slot_id;

    @Test
    public void TestScene() throws ServerException, ClientException {
        Scene scene = new Scene();
        scene.setName("测试场景");
        scene.setDescription("open api test");
        scene.setIntentSwitchMode("INTENT_SWITCH_MODE_STAY");

        //1.create scene
        CreateScene createScene = new CreateScene();
        createScene.setScene(scene);
        Scene scene1 = createScene.request(wulaiClient);

        scene_id = scene1.getId();
        //2.create intent
        CreateIntent createIntent = new CreateIntent();
        Intent intent = new Intent();
        intent.setSceneId(scene_id);
        intent.setName("测试意图");
        intent.setLifespanMins(2);
        createIntent.setIntent(intent);
        Intent intent1 = createIntent.request(wulaiClient);

        intent_id = intent1.getId();


        //3.create trigger
        CreateIntentTrigger createIntentTrigger = new CreateIntentTrigger();
        IntentTrigger intentTrigger = new IntentTrigger();
        intentTrigger.setIntentId(intent_id);
        intentTrigger.setText("测试触发器");
        intentTrigger.setType("TRIGGER_TYPE_EXACT_MATCH_KEYWORD");
        createIntentTrigger.setIntentTrigger(intentTrigger);
        IntentTrigger intentTrigger1 = createIntentTrigger.request(wulaiClient);
        trigger_id = intentTrigger1.getId();

        //4.create slot
        Slot slot = new Slot();
        slot.setSceneId(scene_id);
        slot.setName("测试词槽");
        slot.setQuerySlotFilling(false);


        CreateSlot createSlot = new CreateSlot();
        createSlot.setSlot(slot);
        Slot slot1 = createSlot.request(wulaiClient);
        slot_id=slot1.getId();


        //5.create RequestBlock
        CreateBlockRequestBlock createBlockRequestBlock = new CreateBlockRequestBlock();
        Block block = new Block();
        block.setName("request1");
        block.setIntentID(intent_id);
        block.setSlotID(slot_id);
        block.setMode("RESPONSE_RANDOM");

        createBlockRequestBlock.setBlock(block);
        Block block1 = createBlockRequestBlock.request(wulaiClient);
        blockId=block1.getID();




    }

    @AfterClass
    public static void delete() throws ServerException,ClientException{
        //delete request block
        DeleteBlock deleteBlock = new DeleteBlock();
        deleteBlock.setId(blockId);
        if (200 == deleteBlock.request(wulaiClient)) {
            System.out.println("delete request block ok");
        }

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
