package com.example.project.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.project.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {

    private List<String> mDatas = new ArrayList<>();
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        initView();

        initData();

        mRecyclerView.setAdapter(new RecyclerAdapter(this,mDatas));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initData() {
        for (int i = 0; i < 30; i++) {
            mDatas.add("条目 ——"+i);
        }
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
    }
}
