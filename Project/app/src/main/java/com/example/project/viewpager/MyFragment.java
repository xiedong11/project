package com.example.project.viewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.project.R;

/**
 * Created by xiedong on 2017/4/19.
 */

public class MyFragment extends Fragment {
    private TextView mTextView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_content,null);
        mTextView = (TextView) view.findViewById(R.id.textview);
        mTextView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                System.out.println("----------get------"+event.getX()+"---"+event.getY());
                System.out.println("----------getRaw------"+event.getRawX()+"---"+event.getRawY());

                return true;
            }
        });
        return view;
    }
}
