package com.example.project.okhttp;

/**
 * Created by xiedong on 2017/4/28.
 */

public interface OkNetworkListener2<T1,T2> {
    void onNetworkReceived(T1 rec1,T2 rec2);
    void onNetError(Exception e);
}
