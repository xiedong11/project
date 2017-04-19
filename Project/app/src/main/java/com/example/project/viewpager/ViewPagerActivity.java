package com.example.project.viewpager;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.project.R;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerActivity extends AppCompatActivity {

    private TabLayout mTablayout;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        initView();
    }

    private void initView() {
        mTablayout = (TabLayout) findViewById(R.id.tablayout);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);

        List<String> titleList = new ArrayList<>();
        titleList.add("我关注的人");
        titleList.add("关注我的人");

        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new MyFragment());
        fragmentList.add(new MyFragment());

        ViewPagerAdapter vpAdapter = new ViewPagerAdapter(getSupportFragmentManager(),fragmentList,titleList);

        mViewPager.setAdapter(vpAdapter);
        mTablayout.setupWithViewPager(mViewPager);
    }
}
