import com.DefaultClient;
import com.alibaba.fastjson.JSONObject;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.request.scene.*;
import com.wulai.scene.*;
import org.junit.Test;

import java.util.Map;

public class TestScene {
    DefaultClient defaultClient = new DefaultClient();

    @Test
    public void TestScene() throws ServerException, ClientException {
        Scene scene = new Scene();
        scene.setName("测试场景");
        scene.setDescription("open api test");
        scene.setIntent_switch_mode("INTENT_SWITCH_MODE_STAY");

        //1.create scene
        CreateScene createScene = new CreateScene();
        createScene.setScene(scene);
        Map map = createScene.request(defaultClient);
        System.out.println(map);
        JSONObject jsonObject = JSONObject.parseObject(map.get("scene").toString());
        int scene_id = Integer.valueOf(jsonObject.get("id").toString());
        //2.create intent
        CreateIntent createIntent = new CreateIntent();
        Intent intent = new Intent();
        intent.setScene_id(scene_id);
        intent.setName("测试意图");
        intent.setLifespan_mins(2);
        createIntent.setIntent(intent);
        Map map1 = createIntent.request(defaultClient);
        System.out.println(map1);
        JSONObject json = JSONObject.parseObject(map1.get("intent").toString());
        int intent_id = Integer.valueOf(json.get("id").toString());


        //3.create trigger
        CreateIntentTrigger createIntentTrigger = new CreateIntentTrigger();
        IntentTrigger intentTrigger = new IntentTrigger();
        intentTrigger.setIntent_id(intent_id);
        intentTrigger.setText("测试触发器");
        intentTrigger.setType("TRIGGER_TYPE_EXACT_MATCH_KEYWORD");
        createIntentTrigger.setIntentTrigger(intentTrigger);
        IntentTrigger intentTrigger1 = createIntentTrigger.request(defaultClient);
        int t_id = intentTrigger1.getId();

        //4.create slot
        Slot slot = new Slot();
        slot.setScene_id(scene_id);
        slot.setName("测试词槽");
        slot.setQuery_slot_filling(false);


        CreateSlot createSlot = new CreateSlot();
        createSlot.setSlot(slot);
        Slot slot1 = createSlot.request(defaultClient);

        //5.create RequestBlock
        CreateBlockRequestBlock createBlockRequestBlock = new CreateBlockRequestBlock();
        Block block = new Block();
        block.setName("request1");
        block.setIntent_id(intent_id);
        block.setSlot_id(slot1.getId());
        block.setMode("RESPONSE_RANDOM");

        createBlockRequestBlock.setBlock(block);
        Block block1 = createBlockRequestBlock.request(defaultClient);


        //delete request block
        DeleteBlock deleteBlock = new DeleteBlock();
        deleteBlock.setId(block1.getId());
        if (200 == deleteBlock.request(defaultClient)) {
            System.out.println("delete request block ok");
        }

        //delete slot
        DeleteSlot deleteSlot = new DeleteSlot();
        deleteSlot.setId(slot1.getId());

        if (deleteSlot.request(defaultClient) == 200) {
            System.out.println("delete slot ok");
        }

        //delete trigger
        DeleteIntentTrigger deleteIntentTrigger = new DeleteIntentTrigger();
        deleteIntentTrigger.setId(t_id);
        if (200 == deleteIntentTrigger.request(defaultClient)) {
            System.out.println("delete trigger ok");
        }


        //delete intent
        DeleteIntent deleteIntent = new DeleteIntent();
        deleteIntent.setId(intent_id);

        if (200 == deleteIntent.request(defaultClient)) {
            System.out.println("delete intent ok");
        }

        //delete scene
        DeleteScene deleteScene = new DeleteScene();
        deleteScene.setId(scene_id);
        if (200 == deleteScene.request(defaultClient)) {
            System.out.println("delete scene ok");
        }

    }


}
