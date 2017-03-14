package com.example.project.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.project.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {

    private List<String> mDatas = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private RecyclerAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        initView();


        initData();
        recyclerAdapter = new RecyclerAdapter(this,mDatas);
        mRecyclerView.setAdapter(recyclerAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
//        mRecyclerView.setLayoutManager(new GridLayoutManager(this,3));
//        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));


        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));  //设置间隔（可选水平或者垂直）
        recyclerAdapter.setOnMyItemClickListener(new RecyclerAdapter.OnMyItemClickListener() {
            @Override
            public void myClick(View v, int pos) {
                Toast.makeText(RecyclerViewActivity.this,"onClick---"+pos,Toast.LENGTH_LONG);
                System.out.println("onClick---"+pos);
                recyclerAdapter.addItem(pos);
            }

            @Override
            public void mLongClick(View v, int pos) {
                Toast.makeText(RecyclerViewActivity.this,"onLongClick---"+pos,Toast.LENGTH_LONG);
                System.out.println("onLongClick---"+pos);
                recyclerAdapter.removeData(pos);
            }
        });


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
