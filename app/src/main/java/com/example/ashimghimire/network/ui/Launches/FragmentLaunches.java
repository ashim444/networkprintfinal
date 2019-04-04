package com.example.ashimghimire.network.ui.Launches;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ashimghimire.network.R;
import com.example.ashimghimire.network.databinding.FragmentLaunchesBinding;
import com.example.ashimghimire.network.local.Repository;
import com.example.ashimghimire.network.model.Launch;
import com.example.ashimghimire.network.model.LaunchImages;
import com.example.ashimghimire.network.ui.InteractionListener;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

public class FragmentLaunches extends Fragment implements TextWatcher {
    private InteractionListener listener;
    private FragmentLaunchesBinding launchesBinding;

    public static FragmentLaunches newInstance() {
        return new FragmentLaunches();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        launchesBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_launches, container, false);
        return launchesBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        Repository repository = new Repository();
        repository.storeInDataBase();
        repository.setSaveListener(() -> generateLunchesList());
    }

    public void generateLunchesList() {
        launchesBinding.launchesSearch.addTextChangedListener(this);
        final List<Launch> list = SQLite.select()
                .from(Launch.class)
                .queryList();
        final List<LaunchImages> list1 = SQLite.select()
                .from(LaunchImages.class)
                .queryList();
        launchesBinding.lunchesRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        LaunchAdapter adapter = new LaunchAdapter(getContext());
        adapter.setListLaunches(list);
        launchesBinding.lunchesRecycler.setAdapter(adapter);
        adapter.setOnItemClickListener(position -> listener.navigateToDetails(list.get(position).getLunchesFlightNumber()));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof InteractionListener) {
            listener = (InteractionListener) context;
        } else {
            throw new RuntimeException() {
            };
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
