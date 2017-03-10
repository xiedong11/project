package com.example.project.view;

import android.content.Context;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.project.R;

/**
 * Created by xiedong on 2017/3/9.
 */

public class CountDownView extends LinearLayout implements View.OnClickListener {

    private Context mContext;
    private LinearLayout mLLConutdownView;
    private TextView mCountdownView;
    private TextView bindView;   //被绑定的view


    private CountDownTimer mCountDownTimer = new CountDownTimer(10*1000 , 1000) {
        @Override
        public void onTick(long millisUntilFinished) {

            mCountdownView.setText(millisUntilFinished / 1000 +"");
        }

        @Override
        public void onFinish() {

            mCountdownView.setText("倒计时完成");
            bindView.setClickable(true);
            bindView.setBackgroundColor(Color.RED);
        }
    };

    public CountDownView(Context context) {
        super(context);
        this.mContext = context;
        initView();
    }

    public CountDownView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initView();
    }

    public CountDownView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();
    }

    private void initView() {
        View view = View.inflate(mContext, R.layout.countdown_view,this);   // 添加进容器
        mLLConutdownView = ((LinearLayout) findViewById(R.id.ll_countdown_view));
        mCountdownView = (TextView) findViewById(R.id.tv_countdown_view);


        mCountdownView.setOnClickListener(this);
    }



    public CountDownView bindCountdown2View(View view){

        this.bindView = (TextView) view;
        bindView.setOnClickListener(this);
        return this;
    }
    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case  R.id.bind_view :
                mCountDownTimer.start();
                bindView.setClickable(false);
                bindView.setBackgroundColor(Color.WHITE);
                break;

        }
    }




}
