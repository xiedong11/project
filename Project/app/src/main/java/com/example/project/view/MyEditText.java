package com.example.project.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.provider.ContactsContract;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.project.R;

/**
 * Created by xiedong on 2017/4/19.
 */

public class MyEditText extends LinearLayout {
    private  int ImgResc;
    private  String mEditTextStr;
    private Context mContext;
    private EditText mEditText;
    private ImageView mImageView;

    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);

        mContext = context;
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MyEditText);
        mEditTextStr = ta.getString(R.styleable.MyEditText_myText);
        ImgResc = ta.getResourceId(R.styleable.MyEditText_myImgResc,R.mipmap.ic_launcher);
        ta.recycle();

        initView();
    }

    private void initView() {
        View.inflate(mContext,R.layout.my_edittext_view,this);
        mEditText = (EditText) findViewById(R.id.my_edittext);
        mImageView = (ImageView) findViewById(R.id.img);

        mEditText.setHint(mEditTextStr);
        mImageView.setImageResource(ImgResc);
        mImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditText.setText("");
            }
        });
    }
}
