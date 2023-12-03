package com.example.myapplication.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

//适配器,接受一个类型，指定适配器将使用的参数,我理解的适配器的适配是与数据适配
//ViewHolder用于缓存View对象,滚动列表时快速设置值

public class LeftListAdapter extends RecyclerView.Adapter<LeftListAdapter.MyHolder> {
    private List<String> dataList = new ArrayList<>();//存储食堂信息
    private int currentIndex = 0;//哪一个项被选中了，需要由recipesFragment告诉adapter
    private LeftListOnClickItemListener myLeftListOnClickItemListener;

    public LeftListAdapter(List<String> dataList) {
        this.dataList = dataList;
    }

    //没有ViewHolder实例重用时创建
    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate将布局文件转换为View对象

        View view =
            LayoutInflater.from(parent.getContext()).inflate(R.layout.recipes_left_list_item, null);
        return new MyHolder(view);
    }

    //将数据绑定到ViewHolder
    @Override
    public void onBindViewHolder(@NonNull MyHolder holder,
                                 @SuppressLint("RecyclerView") int position) {
        String diningName = dataList.get(position);
        holder.diningHall_name.setText(diningName);//这步就把数据绑定到MyHolder了

        //也可绑定点击事件,此处是分类的点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (myLeftListOnClickItemListener != null) {
                    myLeftListOnClickItemListener.onItemClick(position);//回调
                }
            }
        });
        if (currentIndex == position) {
            holder.itemView.setBackgroundResource(R.drawable.type_selcetor_bg);
            //如果当前item被选中了，设置
        } else {
            holder.itemView.setBackgroundResource(R.drawable.type_selector_normal_bg);
        }
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
        //数据集发生改变时，通知适配器更改视图
        notifyDataSetChanged();
    }

    //返回数据集大小
    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void setDataList(List<String> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
        //数据集发生变化时，需要通知
    }

    public void setMyLeftListOnClickItemListener(
        LeftListOnClickItemListener myLeftListOnClickItemListener) {
        this.myLeftListOnClickItemListener = myLeftListOnClickItemListener;
    }

    //定义了一个接口
    public interface LeftListOnClickItemListener {
        void onItemClick(int position);//接收点某个具体项的操作函数
    }

    //MyHolder其实就是一个View,也就是要展示的布局
    static class MyHolder extends RecyclerView.ViewHolder {
        TextView diningHall_name;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            //初始化控件
            diningHall_name = itemView.findViewById(R.id.diningName);
        }
    }

}
