package com.example.megafilmes_teste.Interfaces;

import com.example.megafilmes_teste.model.Filme;
import com.example.megafilmes_teste.model.FilmeResponse;
import com.example.megafilmes_teste.model.SerieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TMDbApi {

    @GET("movie/popular")
    Call<FilmeResponse> getPopularMovies(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int page
    );

    @GET("tv/popular")
    Call<SerieResponse> getPopularSeries(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int page
    );

    @GET("movie/{movie_id}")
    Call<Filme> getMovie(
            @Path("movie_id") int id,
            @Query("api_key") String apiKEy,
            @Query("language") String language
    );
}
