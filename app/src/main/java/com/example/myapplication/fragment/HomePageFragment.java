package com.example.myapplication.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.CanteenAdapter;
import com.example.myapplication.db.CanteenDbHelper;
import com.example.myapplication.entity.CanteenInfo;

import java.util.ArrayList;
import java.util.List;

public class HomePageFragment extends Fragment {
    private View rootView;
    private RecyclerView f_homepage_RV;
    private List<CanteenInfo> canteens = new ArrayList<>();
    ;
    private CanteenAdapter canteenAdapter;
    private CanteenDbHelper canteenDbHelper;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_homepage, container, false);
        f_homepage_RV = rootView.findViewById(R.id.f_homepage_RV);
        f_homepage_RV.setLayoutManager(new LinearLayoutManager(getActivity()));
        canteenDbHelper = CanteenDbHelper.getInstance(getContext());
        loadCanteensFromDatabase();
        return rootView;
    }

    // 从数据库加载数据
    private void loadCanteensFromDatabase() {
        // 从数据库中获取
        canteens = canteenDbHelper.queryCanteenListData();

        // 创建适配器并设置到RecyclerView中
        canteenAdapter = new CanteenAdapter(canteens);
        f_homepage_RV.setAdapter(canteenAdapter);
    }
}
