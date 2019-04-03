package com.example.ashimghimire.network.model;

import com.example.ashimghimire.network.local.LaunchDatabase;
import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = LaunchDatabase.class)
public class LaunchImages extends BaseModel {

    @Column
    @PrimaryKey
    private int id;

    @Column
    @SerializedName("mission_patch_small")
    private String lunchesSmallImageUrl;

    @Column
    @SerializedName("mission_patch")
    private String lunchesBigImageUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLunchesSmallImageUrl() {
        return lunchesSmallImageUrl;
    }

    public String getLunchesBigImageUrl() {
        return lunchesBigImageUrl;
    }

    public void setLunchesSmallImageUrl(String lunchesSmallImageUrl) {
        this.lunchesSmallImageUrl = lunchesSmallImageUrl;
    }

    public void setLunchesBigImageUrl(String lunchesBigImageUrl) {
        this.lunchesBigImageUrl = lunchesBigImageUrl;
    }
}
