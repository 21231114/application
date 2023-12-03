package com.example.myapplication.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.LeftListAdapter;
import com.example.myapplication.db.CanteenDbHelper;
import com.example.myapplication.entity.CanteenInfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RecipesFragment extends Fragment{
    private View rootView; //rootView就是当前recipe的界面
    private RecyclerView leftRecyclerView;
    private LeftListAdapter myListAdapter;//初始化控件时，也需要该适配器
    private List<String> leftDataList = new ArrayList<>();//食堂名数据
    private AddCanteenOnClickItemListener myAddCanteenOnClickItemListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //该方法将一个布局文件转化为view（为了生成fragment）,第一个参数是要转换的布局，第二个参数是视图的父级容器，第三个参数是否将生成的布局添加到布局容器
        rootView = inflater.inflate(R.layout.fragment_recipes, container, false);


        //初始化控件
        leftRecyclerView = rootView.findViewById(R.id.leftRecyclerView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        leftDataList.add("学一食堂");
//        leftDataList.add("学二食堂");
//        leftDataList.add("学三食堂");
//        leftDataList.add("学四食堂");
//        leftDataList.add("学五食堂");
//        leftDataList.add("学六食堂");
//        leftDataList.add("教工餐厅");
//        leftDataList.add("清真餐厅");
        //从数据库中设置数据
        myListAdapter = new LeftListAdapter(leftDataList);//适配器需要数据接口
        loadData();
        leftRecyclerView.setAdapter(myListAdapter);//为当前左面列表设置适配器

        //recycleView的点击事件
        myListAdapter.setMyLeftListOnClickItemListener(
            new LeftListAdapter.LeftListOnClickItemListener() {
                @Override
                public void onItemClick(int position) {
                    myListAdapter.setCurrentIndex(position);//当发生点击事件时，需要更改页面
                }
            });

        //食堂添加按钮点击
        rootView.findViewById(R.id.add_canteen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (myAddCanteenOnClickItemListener != null) {
                    myAddCanteenOnClickItemListener.AddCanteenOnClick();
//                    loadData();
                }
            }
        });
    }

    public void loadData() {
        leftDataList.clear();
        List<CanteenInfo> canteenInfoList =
            CanteenDbHelper.getInstance(getActivity()).queryCanteenListData();
        for (int i = 0; i < canteenInfoList.size(); i++) {
            leftDataList.add(canteenInfoList.get(i).getCanteen_name());
        }
        myListAdapter.setDataList(leftDataList);
    }

    public void setMyAddCanteenOnClickItemListener(
        AddCanteenOnClickItemListener myAddCanteenOnClickItemListener) {
        this.myAddCanteenOnClickItemListener = myAddCanteenOnClickItemListener;
    }

    //点击添加食堂按钮的逻辑
    public interface AddCanteenOnClickItemListener {
        void AddCanteenOnClick();//点击了添加按钮
    }
}