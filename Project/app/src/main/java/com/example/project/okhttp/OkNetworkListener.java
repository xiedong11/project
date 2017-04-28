package com.example.project.okhttp;

/**
 * Created by xiedong on 2017/4/28.
 */

public interface OkNetworkListener<T> {
    void onNetworkReceived (T received);
    void onNetworkError(Exception e);

}
