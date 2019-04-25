package com.example.ashimghimire.network.ui.Launches;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.bumptech.glide.Glide;
import com.example.ashimghimire.network.R;
import com.example.ashimghimire.network.databinding.ListItemLaunchesBinding;
import com.example.ashimghimire.network.model.Launch;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class LaunchAdapter extends RecyclerView.Adapter<LaunchAdapter.ViewHolder> implements Filterable {
    private List<Launch> listLaunches = new ArrayList<>();
    private List<Launch> copyLaunchList;
    private Context context;
    private OnItemClickListener onItemClickListener;

    void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void getClickLunches(int position);
    }

    LaunchAdapter(Context context) {
        this.context = context;
    }

    @Override
    public LaunchAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder( DataBindingUtil
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
        holder.itemLaunchesBinding.getRoot().setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.getClickLunches(currentLaunch.getLunchesFlightNumber());
            }
        });
    }

    void setListLaunches(List<Launch> listLaunches) {
        this.listLaunches = listLaunches;
        copyLaunchList = new ArrayList<>(listLaunches);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listLaunches.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ListItemLaunchesBinding itemLaunchesBinding;

        private ViewHolder(@NonNull ListItemLaunchesBinding itemLaunchesBinding) {
            super(itemLaunchesBinding.getRoot());
            this.itemLaunchesBinding = itemLaunchesBinding;
        }
    }

    @Override
    public Filter getFilter() {
        return copyLaunchFilter;
    }

    private Filter copyLaunchFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Launch> filterList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filterList.addAll(copyLaunchList);
            } else {
                String filterString = constraint.toString().toLowerCase().trim();
                filterList = copyLaunchList.stream().filter(l -> l.getLunchesMissionName().toLowerCase().startsWith(filterString)).collect(Collectors.toList());
            }
            FilterResults results = new FilterResults();
            results.values = filterList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            listLaunches.clear();
            listLaunches.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}



