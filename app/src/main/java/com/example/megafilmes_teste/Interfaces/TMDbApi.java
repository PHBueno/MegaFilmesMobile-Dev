package com.example.megafilmes_teste.Interfaces;

import com.example.megafilmes_teste.Models.Filme;
import com.example.megafilmes_teste.Models.FilmeResponse;
import com.example.megafilmes_teste.Models.Serie;
import com.example.megafilmes_teste.Models.SerieResponse;

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

    @GET("tv/{tv_id}")
    Call<Serie> getSerie(
            @Path("tv_id") int id,
            @Query("api_key") String apiKey,
            @Query("language") String language
    );
}
