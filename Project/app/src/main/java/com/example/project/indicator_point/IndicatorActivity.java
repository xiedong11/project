package com.example.project.indicator_point;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.example.project.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiedong on 2017/4/7.
 */

public class IndicatorActivity extends Activity {

    private ViewPager mViewPager;
    private LinearLayout mIndicator;
    private List<View> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indicator);

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mIndicator = (LinearLayout) findViewById(R.id.indicator);

        for (int i = 0; i < 4; i++) {
            View item = LayoutInflater.from(this).inflate(R.layout.viewpager_item,null);
            mList.add(item);
        }

        mViewPager.setAdapter(new ViewPagerAdapter(this,mList));
        initPageIndicator(4);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                System.out.println(position+"-----------");
                for (int i = 0; i < 4; i++) {
                    setIndicator(i, i == position);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }



    private void setIndicator(int i, boolean enabled) {
        View view = mIndicator.getChildAt(i);
        if (view != null) {
            view.setEnabled(enabled);
        }
    }

    private void initPageIndicator(int size) {
        if (size <= 1) {
            return;
        }
        for (int i = 0; i < size; i++) {
            addPageIndicator(i);
        }

        setIndicator(0, true);
    }

    private void addPageIndicator(int i) {
        View dot = new View(this);
        dot.setBackgroundResource(R.drawable.dot_bg_black_selector);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20, 20);
        if (i != 0) {
            params.leftMargin = 40;
        }
        params.topMargin = 20;
        params.bottomMargin = 10;
        dot.setEnabled(false);
        dot.setLayoutParams(params);
        mIndicator.addView(dot);
    }
}
