package com.example.project.okhttp;

import android.os.Handler;
import android.os.Looper;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by xiedong on 2017/3/15.
 */

public class OkHttpUtils {

    private static OkHttpUtils sOkHttpUtils;
    private OkHttpClient mClient;
    private Handler mHandler;

    public static OkHttpUtils getInstance(){
        if(sOkHttpUtils == null){
            sOkHttpUtils = new OkHttpUtils();
        }
        return sOkHttpUtils;
    }

    private OkHttpUtils(){
        mClient = new OkHttpClient();

        mClient.newBuilder().connectTimeout(10, TimeUnit.SECONDS);
        mClient.newBuilder().readTimeout(10,TimeUnit.SECONDS);
        mClient.newBuilder().writeTimeout(10,TimeUnit.SECONDS);

        mHandler = new Handler(Looper.getMainLooper());
    }


    public static Response getSync(String url){
        return sOkHttpUtils.inner_getSync(url);
    }

    private Response inner_getSync(String url) {
        Request request = new Request.Builder().url(url).build();

        Response response = null;

        try {
            response = mClient.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }



    public static String getSyncString(String url){
        return sOkHttpUtils.inner_getSyncString(url);
    }

    private String inner_getSyncString(String url) {
        String result = null;
        try {
            result = inner_getSync(url).body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;

    }









    public static void getAsync(String url,MyCallBack callBack){
            sOkHttpUtils.inner_getAync(url,callBack);
    }

    private void inner_getAync(String url, final MyCallBack callBack) {
        final Request request = new Request.Builder().url(url).build();
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                onMyFailure(request,e,callBack);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String result = null;
                try {
                    result = response.body().string();
                }catch (IOException e){
                    onMyFailure(request, e,callBack);
                }

                onMySuccess(result,callBack);
            }


        });
    }

    private void onMySuccess(final String result, final MyCallBack callBack) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (callBack!=null){
                    try {
                        callBack.sendSuccess(result);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }


    private void onMyFailure(final Request request, final IOException e, final MyCallBack callBack) {

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (callBack!=null){
                    callBack.sendFailure(request,e);
                }
            }
        });

    }

    public interface MyCallBack{
        void sendFailure(Request request,Exception e);
        void sendSuccess(String url) throws Exception;
    }

}
