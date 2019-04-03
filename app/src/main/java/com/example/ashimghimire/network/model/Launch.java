package com.example.ashimghimire.network.model;

import com.example.ashimghimire.network.local.LaunchDatabase;
import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;


@Table(database = LaunchDatabase.class)
public class Launch extends BaseModel {

    @PrimaryKey
    @Column
    @SerializedName("flight_number")
    private int lunchesFlightNumber;

    @Column
    @SerializedName("mission_name")
    private String lunchesMissionName;

    @Column
    @ForeignKey(saveForeignKeyModel = true)
    @SerializedName("links")
    private LaunchImages launchImages;

    public LaunchImages getLaunchImages() {
        return launchImages;
    }

    public int getLunchesFlightNumber() {
        return lunchesFlightNumber;
    }

    public String getLunchesMissionName() {
        return lunchesMissionName;
    }

    public void setLunchesFlightNumber(int lunchesFlightNumber) {
        this.lunchesFlightNumber = lunchesFlightNumber;
    }

    public void setLunchesMissionName(String lunchesMissionName) {
        this.lunchesMissionName = lunchesMissionName;
    }

    public void setLaunchImages(LaunchImages launchImages) {
        this.launchImages = launchImages;
    }
}
