package com.example.project.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.project.R;

/**
 * Created by xiedong on 2017/4/19.
 */

public class MyEditText extends LinearLayout {
    private  String mEditTextStr;
    private Context mContext;
    private EditText mEditText;

    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);

        mContext = context;
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MyEditText);
        mEditTextStr = ta.getString(R.styleable.MyEditText_myText);
        ta.recycle();

        initView();
    }

    private void initView() {
        View.inflate(mContext,R.layout.my_edittext_view,this);
        mEditText = (EditText) findViewById(R.id.my_edittext);
    }
}
