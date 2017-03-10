package com.example.project;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.project.view.CountDownView;

public class MainActivity extends AppCompatActivity {

    private TextView bindView;
    private CountDownView countDownView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindView = (TextView) findViewById(R.id.bind_view);
        countDownView = new CountDownView(this);
        countDownView.bindCountdown2View(bindView);

        addContentView(countDownView,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));



    }
}
