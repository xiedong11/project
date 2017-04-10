package com.example.project.okhttp;

import android.os.Handler;
import android.os.Looper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * http://blog.csdn.net/wuyinlei/article/details/50598783
 * Created by xiedong on 2017/3/14.
 */

public class OkHttpManager {

    private static OkHttpManager sOkHttpManager;
    private OkHttpClient mOkHttpClient;
    private Handler mHandler;


    /**
     * 单例模式，获取OkHttpManager实例
     *
     * @return
     */
    public static OkHttpManager getInstance() {
        if (sOkHttpManager == null) {
            sOkHttpManager = new OkHttpManager();
        }

        return sOkHttpManager;
    }

    private OkHttpManager() {
        mOkHttpClient = new OkHttpClient();

        mOkHttpClient.newBuilder().connectTimeout(10, TimeUnit.SECONDS);
        mOkHttpClient.newBuilder().readTimeout(10, TimeUnit.SECONDS);
        mOkHttpClient.newBuilder().writeTimeout(10, TimeUnit.SECONDS);

        mHandler = new Handler(Looper.getMainLooper());

    }


    public static Response getSync(String url) {
        return sOkHttpManager.inner_getSync(url);
    }


    private Response inner_getSync(String url) {
        Request request = new Request.Builder().url(url).build();
        Response response = null;
        try {
            response = mOkHttpClient.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return response;
    }

    public static String getSyncString(String url) {
        return sOkHttpManager.inner_getSyncString(url);
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








    public static void getAsync(String url, DataCallBack callBack){
        sOkHttpManager.inner_getAsync(url,callBack);
    }



    private void inner_getAsync(String url, final DataCallBack callBack) {
        final Request request = new Request.Builder().url(url).build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                sendRequestFailure(request, e, callBack);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = null;
                try {
                    result = response.body().string();
                } catch (IOException e) {
                    sendRequestFailure(request,e,callBack);
                }

                sendRequestSuccess(result,callBack);
            }
        });

    }

    private void sendRequestSuccess(final String result, final DataCallBack callBack) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if(callBack != null){
                    try {
                        callBack.requestSuccess(result);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void sendRequestFailure(final Request request, final IOException e, final DataCallBack callBack) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (callBack != null) {
                    callBack.requestFailure(request, e);
                }
            }
        });
    }

    public interface DataCallBack {
        void requestFailure(Request request, IOException e);

        void requestSuccess(String result) throws Exception;
    }






    public static void postAsync(String url , Map<String ,String> params, DataCallBack callBack){
        sOkHttpManager.inner_postAsync(url,params,callBack);
    }

    private void inner_postAsync(String url, Map<String, String> params, final DataCallBack callBack) {


        RequestBody requestBody = null;
        if(params == null){
            params = new HashMap<>();
        }


        FormBody.Builder builder = new FormBody.Builder();


        for (Map.Entry<String ,String> map :params.entrySet()){
            String key = map.getKey().toString();
            String value =null;

            if (map.getValue() == null){
                value = "";
            }else {
                value = map.getValue();
            }

            builder.add(key,value);
        }

        requestBody = builder.build();


        //结果返回
        final Request request = new Request.Builder().url(url).post(requestBody).build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                sendRequestFailure(request,e,callBack);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String result = response.body().toString();
                sendRequestSuccess(result,callBack);
            }
        });


    }

}
