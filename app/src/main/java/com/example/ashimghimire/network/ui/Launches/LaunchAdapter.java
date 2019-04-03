package com.example.ashimghimire.network.ui.Launches;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.ashimghimire.network.R;
import com.example.ashimghimire.network.databinding.ListItemLaunchesBinding;
import com.example.ashimghimire.network.model.Launch;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class LaunchAdapter extends RecyclerView.Adapter<LaunchAdapter.ViewHolder> {
    private List<Launch> listLaunches = new ArrayList<>();
    private Context context;

    public LaunchAdapter(Context context) {
        this.context = context;
    }

    @Override
    public LaunchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder((ListItemLaunchesBinding) DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.list_item_launches, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LaunchAdapter.ViewHolder holder, int position) {
        final Launch currentLaunch = listLaunches.get(position);
        holder.itemLaunchesBinding
                .itemLunchesFlightDetails.setText(String.valueOf(currentLaunch.getLunchesFlightNumber()));
        holder.itemLaunchesBinding.itemLunchesFlightDetails.setText(currentLaunch.getLunchesMissionName());
        Glide.with(context).load(currentLaunch.getLaunchImages()
                .getLunchesSmallImageUrl()).placeholder(R.drawable.ic_launcher_background)
                .into(holder.itemLaunchesBinding.itemLunchesImage);
        holder.itemLaunchesBinding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.getClickLunches(currentLaunch.getLunchesFlightNumber());
                }
            }
        });
    }

    public void setListLaunches(List<Launch> listLaunches) {
        this.listLaunches = listLaunches;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listLaunches.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ListItemLaunchesBinding itemLaunchesBinding;

        public ViewHolder(@NonNull ListItemLaunchesBinding itemLaunchesBinding) {
            super(itemLaunchesBinding.getRoot());
            this.itemLaunchesBinding = itemLaunchesBinding;
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void getClickLunches(int position);
    }
}



