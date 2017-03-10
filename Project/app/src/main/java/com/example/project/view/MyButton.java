package com.example.project.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

/**
 * Created by xiedong on 2017/3/10.
 */

public class MyButton extends Button {

    private Context mContext;

    public MyButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;  //  接收上下文参数

    }

    public MyButton(Context context) {
        super(context);
        this.mContext = context;
    }

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;

    }



    private MyOnClickListener listener;
    public void setMyOnClickListener(MyOnClickListener listener){
        this.listener = listener;
    }
    public interface MyOnClickListener{
        void myClick( );
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(event.getAction()==MotionEvent.ACTION_DOWN) {   //判断用户点击事件为Down时，注册点击事件
            listener.myClick();
        }
        return true;

    }
}
