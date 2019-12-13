package com.module.request.scene;

import com.module.request.MsgBody;

public class Response {
    private int id ;
    private MsgBody response;
    private int blockId;

    public void setBlockId(int blockId) {
        this.blockId = blockId;
    }

    public int getBlockId() {
        return blockId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setResponse(MsgBody response) {
        this.response = response;
    }

    public int getId() {
        return id;
    }

    public MsgBody getResponse() {
        return response;
    }
}
