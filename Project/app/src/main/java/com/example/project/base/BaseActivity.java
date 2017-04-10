package com.example.project.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.project.R;

/**
 * Created by xiedong on 2017/3/15.
 */

public abstract class BaseActivity extends Activity implements View.OnClickListener ,ActivityPageSetting {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContent();
        setupView();
    }



}
