package com.example.project.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.project.R;

import java.util.List;

/**
 * Created by xiedong on 2017/3/10.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<MyViewHolder>{

    private List<String> mDatas;
    private Context mContext;
    private LayoutInflater mInflater;

    public RecyclerAdapter(Context mContext,List<String> mDatas) {
        this.mDatas = mDatas;
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }


    /**
     * 绑定布局文件，返回一个viewHolder
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = mInflater.inflate(R.layout.recyclerview_item,null);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }


    /**
     * 填充onCreaterViewHolder方法中返回对holder中对控件
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.textView.setText(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
}


/**
 * 在Holder中对控件findviewbyid
 */
class MyViewHolder extends RecyclerView.ViewHolder{

    TextView textView;

    public MyViewHolder(View itemView) {
        super(itemView);

        textView = ((TextView) itemView.findViewById(R.id.tv_content));
    }
}