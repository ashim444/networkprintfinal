package com.example.ashimghimire.network.local;

import com.example.ashimghimire.network.model.Launch;
import com.example.ashimghimire.network.model.LaunchImages;
import com.example.ashimghimire.network.networking.LaunchApiRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {
    private iDataSaveListener saveListener;

    public interface iDataSaveListener {
        void dataSaved();
    }

    public void setSaveListener(iDataSaveListener saveListener) {
        this.saveListener = saveListener;
    }

    public void storeInDataBase() {
        Call<List<Launch>> lunches = LaunchApiRepository.getLaunchApi().getLunches();
        lunches.enqueue(new Callback<List<Launch>>() {
            @Override
            public void onResponse(Call<List<Launch>> call, Response<List<Launch>> response) {
                if (!response.isSuccessful()) {
                    return;
                }

                for (int i = 0; i < response.body().size(); i++) {
                    LaunchImages launchImages = response.body().get(i).getLaunchImages();
                    launchImages.setId(response.body().get(i).getLunchesFlightNumber());
                    launchImages.save();
                    response.body().get(i).save();
                }
                saveListener.dataSaved();
            }

            @Override
            public void onFailure(Call<List<Launch>> call, Throwable t) {
                saveListener.dataSaved();
            }
        });
    }
}
