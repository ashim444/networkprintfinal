package com.example.ashimghimire.network.networking;

import com.example.ashimghimire.network.model.Launch;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface LaunchApi {
    @GET(Config.EndpointLaunches.LAUNCHES)
    Call<List<Launch>> getLunches();

    //This function is not used because database is used
//    @GET(Config.EndpointLaunches.LAUNCHES + "/{flight_number}")
//    Call<Launch> getOneLaunch(@Path("flight_number") int flightNumber);
}
