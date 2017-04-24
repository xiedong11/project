package com.example.project.eventbus;

/**
 * Created by xiedong on 2017/4/24.
 */

public class ChangeDataEvent extends BaseEventT {
    public String msg;

    public ChangeDataEvent(Class<?> from, String msg) {
        super(from);
        this.msg = msg;
    }
}
