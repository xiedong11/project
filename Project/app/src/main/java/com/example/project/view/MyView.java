package com.example.project.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.project.R;

/**
 * Created by xiedong on 2017/3/10.
 */

public class MyView extends LinearLayout {
    private TextView clickTV;
    private Context mContext;

    public MyView(Context context) {
        super(context);
        this.mContext = context;
        initView();
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initView();
    }

    private void initView() {
        clickTV = (TextView) findViewById(R.id.click_text);
        View view = View.inflate(mContext,R.layout.myview_layout,this);
    }


    private MyViewClickListener listener;
    public void setMyViewClickListener (MyViewClickListener listener){
        this.listener = listener;
    }
    public interface MyViewClickListener{
        void myClick();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN){
            listener.myClick();
        }

        return true;
    }
}
