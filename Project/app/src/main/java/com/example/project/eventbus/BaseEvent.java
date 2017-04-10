package com.example.project.eventbus;

import de.greenrobot.event.EventBus;

/**
 * Created by xiedong on 2017/3/14.
 */

public class BaseEvent {


    private String msg;

    public BaseEvent(String msg) {
        this.msg = msg;
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
