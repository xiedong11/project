package com.example.project.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.project.R;

/**
 * 使用组合方式完成自定义View
 * Created by xiedong on 2017/3/10.
 */

public class TitleView extends LinearLayout{
    private ImageView titleBtn;
    private TextView titleText;
    private Context mContext;

    public TitleView(Context context) {
        super(context);
        this.mContext = context;
        initView();
    }

    public TitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();
    }

    public TitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initView();
    }



    private void initView() {

//        View view = View.inflate(mContext,R.layout.titleview_layout,this);
        LayoutInflater.from(mContext).inflate(R.layout.titleview_layout,this);

        titleBtn = (ImageView) findViewById(R.id.title_btn);
        titleText = (TextView) findViewById(R.id.title_text);
    }


    /**
     * 设置左上角箭头的监听事件
     * @param listener
     */
    public void setLeftButtonListener(OnClickListener listener){
        titleBtn.setOnClickListener(listener);
    }


    //设置标题
    public void setTitleText(String title){
        titleText.setText(title);
    }
}
