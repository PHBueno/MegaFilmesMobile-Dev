package com.example.megafilmes_teste.Interfaces;

import com.example.megafilmes_teste.model.FilmeResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TMDbApi {

    @GET("movie/popular")
    Call<FilmeResponse> getPopularMovies(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int page
    );
}
