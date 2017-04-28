package com.example.project.okhttp;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by xiedong on 2017/4/28.
 */

public class OkUtils {
    private static OkUtils sOkUtils;
    private Handler mHandler;
    private OkHttpClient mClient;


    public static OkUtils getInstance(){
        if(sOkUtils == null){
            sOkUtils = new OkUtils();
        }
        return sOkUtils;
    }


    private OkUtils(){
        mClient = new OkHttpClient();

        mClient.newBuilder().connectTimeout(10, TimeUnit.SECONDS);
        mClient.newBuilder().readTimeout(10,TimeUnit.SECONDS);
        mClient.newBuilder().writeTimeout(10,TimeUnit.SECONDS);

        mHandler = new Handler(Looper.getMainLooper());
    }



}
