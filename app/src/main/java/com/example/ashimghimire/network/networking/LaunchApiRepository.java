package com.example.ashimghimire.network.networking;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LaunchApiRepository {
    private static LaunchApi launchApi;

    public static LaunchApi getLaunchApi() {
        if (launchApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Config.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            launchApi = retrofit.create(LaunchApi.class);
        }
        return launchApi;
    }
}
