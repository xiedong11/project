package com.example.project.recyclerview;

import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.project.R;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;


/**
 * RecyclerView结合Design包中的新控件配合使用
 */
public class RecyclerView_2 extends AppCompatActivity {
    private List<String> mDatas = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private MyRecyclerViewAdapter myRecyclerViewAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private int lastVisibleItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_2);

        initData();
        initView();
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadMoreData();
            }
        });

        //调整SwipeRefreshLayout的位置
        mSwipeRefreshLayout.setProgressViewOffset(false, 0,  (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60, getResources().getDisplayMetrics()));

        //设置圆环动画的颜色 最多可设置4中颜色
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        myRecyclerViewAdapter = new MyRecyclerViewAdapter();
        mRecyclerView.setAdapter(myRecyclerViewAdapter);
        mLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);


        //recyclerview滚动监听
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                //0：当前屏幕停止滚动；1时：屏幕在滚动 且 用户仍在触碰或手指还在屏幕上；2时：随用户的操作，屏幕上产生的惯性滑动；
                // 滑动状态停止并且剩余少于两个item时，自动加载下一页 (预加载)

                System.out.println("__________ " + lastVisibleItem + "----------" + mLinearLayoutManager.getItemCount());
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 2 == mLinearLayoutManager.getItemCount()) {

                    System.out.println("__________ load more data");
                    loadMoreData();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //获取加载的最后一个可见视图在适配器的位置。
                lastVisibleItem = mLinearLayoutManager.findLastVisibleItemPosition();
            }
        });
    }


    private int mCount = 0;

    private void initData() {
        for (int i = 0; i < 50; i++) {
            mDatas.add(" " + mCount++);

        }

    }


    private void loadMoreData() {

        mSwipeRefreshLayout.setRefreshing(true);
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                for (int i = 0; i < 50; i++) {
                    mDatas.add(" " + mCount++);

                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        myRecyclerViewAdapter.notifyDataSetChanged();
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                });

            }
        }).start();


    }


       /* **********************************************      RecyclerView_2的Adapter类         *************************************** */

    public class MyRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


        /**
         * @param parent
         * @param viewType 通过getItemViewType返回的业务类型
         * @return
         */
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


            System.out.println(viewType + "-------------------------");
            if (viewType == 1) {

                View view = LayoutInflater.from(RecyclerView_2.this).inflate(R.layout.my_recyclerview_2, null);
                MyViewHolder holder = new MyViewHolder(view);
                return holder;

            } else {

                View view = LayoutInflater.from(RecyclerView_2.this).inflate(R.layout.my_recyclerview_2_img, null);
                MyViewHolder2 holder2 = new MyViewHolder2(view);
                return holder2;

            }
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


            if (holder instanceof MyViewHolder) {
                ((MyViewHolder) holder).tv.setText(position + "type---1");
            } else if (holder instanceof MyViewHolder2) {
                ((MyViewHolder2) holder).tv.setText(position + "type--2---");
            }

        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }


        /**
         * 判断Adapter中，Item的类型
         *
         * @param position
         * @return
         */
        @Override
        public int getItemViewType(int position) {
            if (position % 2 == 0) {
                return 1;
            } else {
                return 2;
            }
        }


    }


    //    ************************************** 根据业务类型不同，定义不同的Holder   ***********************************
    public class MyViewHolder extends RecyclerView.ViewHolder {


        TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv = ((TextView) itemView.findViewById(R.id.textview));
        }
    }

    public class MyViewHolder2 extends RecyclerView.ViewHolder {


        TextView tv;

        public MyViewHolder2(View itemView) {
            super(itemView);
            tv = ((TextView) itemView.findViewById(R.id.textview));
        }
    }


}
