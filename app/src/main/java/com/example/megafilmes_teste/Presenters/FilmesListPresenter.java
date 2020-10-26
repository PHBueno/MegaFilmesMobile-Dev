package com.example.megafilmes_teste.Presenters;

import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.megafilmes_teste.Adapters.MoviesAdapter;
import com.example.megafilmes_teste.Interfaces.FilmeContract;
import com.example.megafilmes_teste.Interfaces.OnGetMoviesCallback;
import com.example.megafilmes_teste.Interfaces.OnMoviesClickCallback;
import com.example.megafilmes_teste.Models.Filme;
import com.example.megafilmes_teste.Services.MovieServices;

import java.util.List;

public class FilmesListPresenter implements FilmeContract.FilmesListPresenter {

    private static MovieServices filmesAPI;

    private RecyclerView recyclerViewFilmes;
    private MoviesAdapter filmeAdapter;

    public FilmesListPresenter() {
        filmesAPI = new MovieServices().repository;
    }

    public RecyclerView useRecyclerView (
            RecyclerView recyclerView,
            RecyclerView.LayoutManager layout,
            final OnMoviesClickCallback onClickFilme
    ) {

        this.recyclerViewFilmes = recyclerView;
        this.recyclerViewFilmes.setLayoutManager(layout);

        this.filmesAPI.getMovies(new OnGetMoviesCallback() {

            public void onSuccess(List<Filme> filmes) {
                filmeAdapter = new MoviesAdapter(filmes, onClickFilme);
                recyclerViewFilmes.setAdapter(filmeAdapter);
            }

            public void onError() {
                Toast.makeText(
                        null, "Please check your internet connection.",
                        Toast.LENGTH_SHORT
                ).show();
            }

        });

        return recyclerViewFilmes;
    }

    public RecyclerView getRecyclerViewFilmes() {
        return this.recyclerViewFilmes;
    }

}
