package com.example.megafilmes_teste.Presenters;

import android.widget.Toast;

import com.example.megafilmes_teste.Adapters.MoviesAdapter;
import com.example.megafilmes_teste.Interfaces.OnGetMoviesCallback;
import com.example.megafilmes_teste.Interfaces.OnGetMoviesPresenterCallback;
import com.example.megafilmes_teste.Interfaces.OnMoviesClickCallback;
import com.example.megafilmes_teste.Models.Filme;
import com.example.megafilmes_teste.Services.MovieServices;

import java.util.List;

public class FilmesListPresenter {

    private static MovieServices filmesAPI;

    private MoviesAdapter filmeAdapter;



    public FilmesListPresenter (
            final OnGetMoviesPresenterCallback onGetFilmes,
            final OnMoviesClickCallback onClickFilme
    ) {
        filmesAPI = new MovieServices().repository;

        this.filmesAPI.getMovies(new OnGetMoviesCallback() {

            public void onSuccess(List<Filme> filmes) {
                filmeAdapter = new MoviesAdapter(filmes, onClickFilme);

                onGetFilmes.onSuccess(filmeAdapter);
            }

            public void onError() {
                Toast.makeText(
                        null, "Please check your internet connection.",
                        Toast.LENGTH_SHORT
                ).show();
            }

        });
    }

}
