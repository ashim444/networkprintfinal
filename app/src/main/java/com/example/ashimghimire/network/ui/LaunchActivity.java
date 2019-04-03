package com.example.ashimghimire.network.ui;

import android.os.Bundle;

import com.example.ashimghimire.network.R;
import com.example.ashimghimire.network.ui.Launches.FragmentLaunches;
import com.example.ashimghimire.network.ui.launchinfo.FragmentLaunchInfo;

import androidx.appcompat.app.AppCompatActivity;

public class LaunchActivity extends AppCompatActivity implements InteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launches);
        showDefaultFragment();
    }

    @Override
    public void navigateToDetails(int flightNumber) {
        getSupportFragmentManager().beginTransaction().replace(R.id.lunches_fragment_container, FragmentLaunchInfo.newInstance(flightNumber)).addToBackStack(null).commit();
    }

    @Override
    public void showDefaultFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.lunches_fragment_container, FragmentLaunches.newInstance()).commit();
    }

    @Override
    public void pop() {
    }
}
