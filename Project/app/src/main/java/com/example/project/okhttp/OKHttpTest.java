package com.example.project.okhttp;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.project.R;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OKHttpTest extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp_test);



    }



    public void HttpGet(View v) {


//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                String s = OkHttpManager.getInstance().getSyncString("http://blog.csdn.net/wuyinlei/article/details/50598783");
//                System.out.println("------------------" + s);
//
//            }
//        }).start();


        OkHttpManager.getInstance().getAsync("http://blog.csdn.net/wuyinlei/article/details/50598783", new OkHttpManager.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {

                System.out.println("failure--------------");
            }

            @Override
            public void requestSuccess(String result) throws Exception {

                System.out.println("333333333333333"+result);
            }
        });


        Map<String,String> params = new HashMap<>();
        params.put("username","xie");
        params.put("password","123");



//        post 提交数据

        OkHttpManager.getInstance().postAsync("", params, new OkHttpManager.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {

            }

            @Override
            public void requestSuccess(String result) throws Exception {

            }
        });




    }


}
