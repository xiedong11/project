package com.example.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.project.service.MyService;
import com.example.project.viewpager.ViewPagerActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectActivity extends AppCompatActivity {


    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        ButterKnife.bind(this); //一定要先绑定
        intent = new Intent(SelectActivity.this, MyService.class);
        Button btn = (Button) findViewById(R.id.start);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(intent);
            }
        });


        Button btn2 = (Button) findViewById(R.id.stop);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(intent);
            }
        });

        Button btn3 = (Button) findViewById(R.id.viewpager);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SelectActivity.this, ViewPagerActivity.class));
            }
        });
    }

    @OnClick(R.id.start)
    public void startService(View v) {
        System.out.println("------------");
        startService(new Intent(this, MyService.class));
    }

    @OnClick(R.id.stop)
    public void stopService(View v) {
        stopService(new Intent(this, MyService.class));
    }


}