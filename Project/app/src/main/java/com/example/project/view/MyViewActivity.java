package com.example.project.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.project.R;
import com.example.project.recyclerview.RecyclerViewActivity;

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
}