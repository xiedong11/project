package com.example.project.eventbus;

import android.view.View;
import android.widget.Button;

import com.example.project.R;
import com.example.project.base.BaseActivity;

import de.greenrobot.event.EventBus;

/**
 * Created by xiedong on 2017/3/15.
 */

public class EventBusSend extends BaseActivity {
    private Button mButton;

    @Override
    public void onClick(View v) {
        EventBus.getDefault().post(new BaseEvent("通过Event发送来的消息"));
    }

    @Override
    public void setContent() {
        setContentView(R.layout.event_send);
    }

    @Override
    public void setupView() {

        mButton = (Button) findViewById(R.id.btn_send);
        mButton.setOnClickListener(this);

    }
}
