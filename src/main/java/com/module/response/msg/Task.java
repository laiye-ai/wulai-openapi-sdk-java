package com.module.response.msg;

import com.alibaba.fastjson.annotation.JSONField;
import com.module.request.nlp.Entities;

import java.util.List;

public class Task {
    private String blockType;
    private int blockId;
    private int taskId;
    private String blockName;
    private List<Entities> entities;
    private String taskName;
    private int robotId;

    @JSONField(name = "block_id")
    public void setBlockId(int blockId) {
        this.blockId = blockId;
    }

    @JSONField(name = "block_name")
    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }

    @JSONField(name = "block_type")
    public void setBlockType(String blockType) {
        this.blockType = blockType;
    }

    public void setEntities(List<Entities> entities) {
        this.entities = entities;
    }

    @JSONField(name = "robot_id")
    public void setRobotId(int robotId) {
        this.robotId = robotId;
    }

    @JSONField(name = "task_id")
    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    @JSONField(name = "task_name")
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    @JSONField(name = "block_id")
    public int getBlockId() {
        return blockId;
    }

    @JSONField(name = "robot_id")
    public int getRobotId() {
        return robotId;
    }

    @JSONField(name = "task_id")
    public int getTaskId() {
        return taskId;
    }

    public List<Entities> getEntities() {
        return entities;
    }

    @JSONField(name = "block_name")
    public String getBlockName() {
        return blockName;
    }

    @JSONField(name = "block_type")
    public String getBlockType() {
        return blockType;
    }

    @JSONField(name = "task_name")
    public String getTaskName() {
        return taskName;
    }

}
