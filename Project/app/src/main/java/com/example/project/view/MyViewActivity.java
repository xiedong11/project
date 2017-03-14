package com.example.project.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.project.R;
import com.example.project.okhttp.OKHttpTest;
import com.example.project.recyclerview.RecyclerViewActivity;
import com.example.project.recyclerview.RecyclerView_2;

import java.io.IOException;

public class MyViewActivity extends AppCompatActivity {

    private TitleView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_view);
        title = (TitleView) findViewById(R.id.title);
        title.setLeftButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MyViewActivity.this, RecyclerViewActivity.class));
                System.out.println("-------------按钮被点击————————————");
            }
        });

        title.setTitleText("自定义TitleBar");
    }


    //按钮 用于打开RecyclerView界面
    public void RecyclerView_BTN(View v){
        startActivity(new Intent(MyViewActivity.this, RecyclerView_2.class));
    }


    //按钮 用于打开RecyclerView界面
    public void OKHttp(View v){
        startActivity(new Intent(MyViewActivity.this, OKHttpTest.class));
    }

}
