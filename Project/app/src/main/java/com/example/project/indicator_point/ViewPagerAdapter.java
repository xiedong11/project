package com.example.project.indicator_point;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.project.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiedong on 2017/4/7.
 */

public class ViewPagerAdapter extends PagerAdapter {

    private List<View> mList = new ArrayList<>();
    private LayoutInflater mInflater;

    public ViewPagerAdapter(Context context , List<View> list) {
        if (list == null || list.isEmpty()){
            return;
        }
        mInflater = LayoutInflater.from(context);
        mList = list;


//        initView(list);

    }

//    private void initView(List<View> list) {
//      for (View view :list){
//          View item = mInflater.inflate(R.layout.viewpager_item,null);
//          mList.add(item);
//      }
//    }

    @Override
    public int getCount() {
        return null ==mList ? 0 :mList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
       container.removeView(mList.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mList.get(position));
        return mList.get(position);
    }
}
