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
 * Created by xiedong on 2017/4/28.
 */

public class OkUtils {
    private static OkUtils sOkUtils;
    private Handler mHandler;
    private OkHttpClient mClient;


    public static OkUtils getInstance() {
        if (sOkUtils == null) {
            sOkUtils = new OkUtils();
        }
        return sOkUtils;
    }


    private OkUtils() {
        mClient = new OkHttpClient();

        mClient.newBuilder().connectTimeout(10, TimeUnit.SECONDS);
        mClient.newBuilder().readTimeout(10, TimeUnit.SECONDS);
        mClient.newBuilder().writeTimeout(10, TimeUnit.SECONDS);

        mHandler = new Handler(Looper.getMainLooper());
    }


    public Response getSync(String url) {
        return sOkUtils.inner_getSync(url);
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


    public String getSyncString(String url) {
        return sOkUtils.inner_getSyncString(url);
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


    public void getAsync(String url, OKCallBack callBack) {
        sOkUtils.inner_getAsync(url, callBack);

    }

    private void inner_getAsync(String url, final OKCallBack callBack) {
        final Request request = new Request.Builder().url(url).build();
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                requestFailure(request, e, callBack);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = null;
                result = response.body().string();
                requestSuccess(result, callBack);

            }
        });
    }

    private void requestSuccess(final String result, final OKCallBack callBack) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (callBack !=null){
                    try {
                        callBack.onResponse(result);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }


    private void requestFailure(final Request request, final IOException e, final OKCallBack callBack) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (callBack != null){
                    callBack.onError(request,e);
                }
            }
        });

    }

    public interface OKCallBack {
        void onResponse(String result) throws Exception;

        void onError(Request request , Exception e);
    }


}
