package com.example.project.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * 自绘控件
 * Created by xiedong on 2017/3/10.
 */

public class MyOwnView extends View implements View.OnClickListener{

    private Paint mPaint;
    private Rect mRect;
    private int colors []={Color.RED,Color.BLUE,Color.GREEN};
    private int i=0;

    public MyOwnView(Context context) {
        super(context);

        initView();
    }

    public MyOwnView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initView();
    }

    public MyOwnView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView();
    }

    private void initView() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRect = new Rect();
        setOnClickListener(this);  //实现自身点击事件
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setColor(colors[i%colors.length]);
        canvas.drawRect(0,0,getWidth(),getHeight(),mPaint);


        float textWidth = mRect.width();
        float textHeight = mRect.height();
        mPaint.setColor(Color.WHITE);
        mPaint.setTextSize(30);
        canvas.drawText("点击可变颜色",getWidth()/4, getHeight()/2 + textHeight/2,mPaint);
    }

    @Override
    public void onClick(View v) {

        i++;
        invalidate(); //通知重新绘制布局
    }
}
