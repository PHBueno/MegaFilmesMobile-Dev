package com.example.megafilmes_teste.Services;

import com.example.megafilmes_teste.Interfaces.OnGetEpCallback;
import com.example.megafilmes_teste.Interfaces.OnGetSerieCallback;
import com.example.megafilmes_teste.Interfaces.OnGetSeriesCallback;
import com.example.megafilmes_teste.Interfaces.TMDbApi;
import com.example.megafilmes_teste.Models.Serie;
import com.example.megafilmes_teste.Models.SerieResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SerieServices {
    public static final String URLAPI = "https://api.themoviedb.org/3/";
    public static final String URLAPIKEY = "b881ca47490d5f5879a4cbd0a0b3a94c";

    public static SerieServices repository;
    private static TMDbApi api_serie;

    private SerieServices(TMDbApi api_serie){this.api_serie = api_serie;}

    public SerieServices () {
        if (repository == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(URLAPI)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            repository = new SerieServices(retrofit.create(TMDbApi.class));
        }
    }

    public void getSeries(final OnGetSeriesCallback callback){
        api_serie.getPopularSeries(URLAPIKEY, "pt-BR", 1)
                .enqueue(new Callback<SerieResponse>() {
                    @Override
                    public void onResponse(Call<SerieResponse> call, Response<SerieResponse> response) {
                        if (response.isSuccessful()) {
                            SerieResponse seriesResponse = response.body();
                            if (seriesResponse != null && seriesResponse.getSeries() != null) {
                                callback.onSuccess(seriesResponse.getSeries());
                            } else {
                                callback.onError();
                            }
                        } else {
                            callback.onError();
                        }
                    }

                    @Override
                    public void onFailure(Call<SerieResponse> call, Throwable t) {
                        callback.onError();
                    }
                });
    }
    public void getSerie(int serieId, final OnGetSerieCallback callback) {
        api_serie.getSerie(serieId, URLAPIKEY , "pt-BR").enqueue(new Callback<Serie>() {
            @Override
            public void onResponse(Call<Serie> call, Response<Serie> response) {

                if (response.isSuccessful()) {
                    Serie serie = response.body();
                    if (serie != null) {
                        callback.onSuccess(serie);
                    } else {
                        callback.onError();
                    }
                } else {
                    callback.onError();
                }
            }

            @Override
            public void onFailure(Call<Serie> call, Throwable t) {
                callback.onError();
            }
        });


    /*public void getEP(int serieId, int temp, final OnGetEpCallback callback) {
        api_serie.getEP(serieId, temp, URLAPIKEY, "pt-BR")
                .enqueue(new Callback<Serie>() {
                    @Override
                    public void onResponse(Call<Serie> call, Response<Serie> response) {
                        if (response.isSuccessful()){
                            Serie ep = response.body();
                            if (ep != null) {
                                callback.onSuccess(ep);
                            } else {
                                callback.onError();
                            }
                        } else {
                            callback.onError();
                        }
                    }

                    @Override
                    public void onFailure(Call<Serie> call, Throwable t) {
                        callback.onError();

                    }
                });*/

    }

}
