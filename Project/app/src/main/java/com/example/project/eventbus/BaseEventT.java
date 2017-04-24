package com.example.project.eventbus;


/**
 * Created by xiedong on 2017/4/24.
 */

public abstract class BaseEventT {
    public Class <?>  mFrom;
    public Object mDatas;
    public int mEventType;
    public  String mMsg;

    public BaseEventT (Class<?> from){
        mFrom = from;
    }


    @Override
    public String toString() {
        return getClass().getSimpleName() + ",from" +mFrom+" , eventtype"+mEventType + ", mDatas" +mDatas;
    }

}
