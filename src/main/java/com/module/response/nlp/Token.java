package com.module.response.nlp;

public class Token {
    private String text;
    private String pos;
    private int idx_start;

    public void setIdx_start(int idx_start) {
        this.idx_start = idx_start;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getIdx_start() {
        return idx_start;
    }

    public String getPos() {
        return pos;
    }

    public String getText() {
        return text;
    }
}
