package com.module.request.scene;

import com.module.response.scene.End;
import com.module.response.scene.Last;
import com.module.response.scene.Specified;

public class Action {
    private Last last;
    private End end;
    private Specified specified;

    public void setLast(Last last) {
        this.last = last;
    }

    public void setEnd(End end) {
        this.end = end;
    }

    public void setSpecified(Specified specified) {
        this.specified = specified;
    }

    public End getEnd() {
        return end;
    }

    public Last getLast() {
        return last;
    }

    public Specified getSpecified() {
        return specified;
    }
}
