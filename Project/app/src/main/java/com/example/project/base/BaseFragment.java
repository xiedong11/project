package com.example.project.base;

import android.support.v4.app.Fragment;

/**
 * Created by xiedong on 2017/3/15.
 */

public abstract class BaseFragment extends Fragment{

    public abstract void getLayoutId();
    public abstract void setupView();
    public abstract void setContent();
}
