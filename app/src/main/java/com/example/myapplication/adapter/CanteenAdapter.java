package com.example.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.entity.CanteenInfo;

import java.util.List;

public class CanteenAdapter extends RecyclerView.Adapter<CanteenAdapter.CanteenViewHolder> {
    private List<CanteenInfo> canteenInfoList;

    public CanteenAdapter(List<CanteenInfo> canteenInfoList) {
        this.canteenInfoList = canteenInfoList;
    }

    @NonNull
    @Override
    public CanteenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =
            LayoutInflater.from(parent.getContext()).inflate(R.layout.canteens_item, parent, false);
        return new CanteenViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CanteenViewHolder holder, int position) {
        CanteenInfo canteenInfo = canteenInfoList.get(position);
        holder.bindData(canteenInfo);
    }

    @Override
    public int getItemCount() {
        return canteenInfoList.size();
    }

    static class CanteenViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView;

        public CanteenViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.canteens_item_canteenName_TV);
        }

        public void bindData(CanteenInfo canteenInfo) {
            titleTextView.setText(canteenInfo.getCanteen_name());
        }
    }
}
