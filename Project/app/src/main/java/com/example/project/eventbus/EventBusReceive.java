package com.example.project.eventbus;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.project.R;
import com.example.project.base.BaseActivity;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;

/**
 * Created by xiedong on 2017/3/15.
 */

public class EventBusReceive extends BaseActivity {
    private Button mButton;
    private TextView mTextView;


    @Override
    public void onClick(View v) {
        startActivity(new Intent(EventBusReceive.this,EventBusSend.class));
    }

    @Override
    public void setContent() {

        setContentView(R.layout.receive_event);
    }

    @Override
    public void setupView() {
        mButton = (Button) findViewById(R.id.btn_open);
        mButton.setOnClickListener(this);
        mTextView = (TextView) findViewById(R.id.textview);

        EventBus.getDefault().register(this);

    }




    @Subscribe
    public void onEventMainThread(BaseEvent event){
        String msg = event.getMsg();
        mTextView.setText(msg);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
       EventBus.getDefault().unregister(this);
    }


}
