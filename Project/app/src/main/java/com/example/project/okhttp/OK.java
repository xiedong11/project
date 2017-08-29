package com.example.project.okhttp;

import android.os.Handler;
import android.os.Looper;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by xiedong on 2017/8/18.
 */

public class OK {
    private static OK mOk;
    private OkHttpClient mClient;
    private Handler mHandler;

    public static OK getInstance() {
        if (mOk == null) {
            mOk = new OK();
        }

        return mOk;
    }

    public OK() {
        mClient = new OkHttpClient();
        mClient.newBuilder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS);
        mHandler = new Handler(Looper.getMainLooper());
    }

    public Response getSync(String url, Object tag) {
        Request request = new Request.Builder().url(url).tag(tag).build();
        Response response = null;

        try {
            response = mClient.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    public String getSyncString(String url,Object tag){
        String result=null;
        try {
            result = getSync(url,tag).body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


    public void getAync(String url, Object tag, final OkCallback okCallback){
        final Request request = new Request.Builder().url(url).tag(tag).build();
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                sendFailure(request,e,okCallback);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                sendSuccess(response,okCallback);
            }
        });

    }

    private void sendSuccess(final Response response, final OkCallback okCallback) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (okCallback!=null){
                    try {
                        okCallback.Success(response.body().string());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void sendFailure(final Request request, final IOException e, final OkCallback okCallback) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (okCallback!=null){
                    okCallback.Failure(request,e);
                }
            }
        });
    }

    private interface OkCallback {
        void Success(String result)throws Exception;
        void Failure(Request request,Exception e);
    }

    public void cancelTag(Object tag){
        for (Call call:mClient.dispatcher().queuedCalls()){
            if (tag.equals(call.request().tag())){
                call.cancel();
            }
        }
        for (Call call:mClient.dispatcher().runningCalls()){
            if (tag.equals(call.request().tag())){
                call.cancel();
            }
        }

    }
}
