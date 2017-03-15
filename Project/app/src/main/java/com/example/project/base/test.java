package com.example.project.base;

import android.provider.ContactsContract;
import android.view.View;
import android.widget.ImageView;

import com.example.project.R;
import com.example.project.base.BaseActivity;

/**
 * Created by xiedong on 2017/3/15.
 */

public class test extends BaseActivity {


    private ImageView imageview;

    @Override
    public void onClick(View v) {

        System.out.println("dddfdfsfdsfsdfdsfsdfsadfdsfsdafsdvasvasdfsdasfdsfsdfsafsdf");
    }

    @Override
    public void setContent() {

        setContentView(R.layout.main);
    }

    @Override
    public void setupView() {

        imageview = (ImageView) findViewById(R.id.id_text);
        imageview.setOnClickListener(this);
    }
}
