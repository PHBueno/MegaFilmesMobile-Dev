package com.example.megafilmes_teste.MovieService;

import com.example.megafilmes_teste.Interfaces.OnGetMoviesCallback;
import com.example.megafilmes_teste.Interfaces.TMDbApi;
import com.example.megafilmes_teste.model.FilmeResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Service {
    public static final String URLAPI = "https://api.themoviedb.org/3/";
    public static final String URLAPIKEY = "b881ca47490d5f5879a4cbd0a0b3a94c";

    private static Service repository;

    private static TMDbApi api;

    private Service(TMDbApi api) {
        this.api = api;
    }

    public static Service getInstance() {
        if (repository == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(URLAPI)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            repository = new Service(retrofit.create(TMDbApi.class));
        }

        return repository;
    }

    public void getMovies(final OnGetMoviesCallback callback) {
        api.getPopularMovies(URLAPIKEY, "pt-BR", 1)
                .enqueue(new Callback<FilmeResponse>() {
                    @Override
                    public void onResponse(Call<FilmeResponse> call, Response<FilmeResponse> response) {
                        if (response.isSuccessful()) {
                            FilmeResponse moviesResponse = response.body();
                            if (moviesResponse != null && moviesResponse.getMovies() != null) {
                                callback.onSuccess(moviesResponse.getMovies());
                            } else {
                                callback.onError();
                            }
                        } else {
                            callback.onError();
                        }
                    }

                    @Override
                    public void onFailure(Call<FilmeResponse> call, Throwable t) {
                        callback.onError();
                    }
                });
    }


}
