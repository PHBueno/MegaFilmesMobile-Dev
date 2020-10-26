package com.example.megafilmes_teste.Interfaces;

import androidx.recyclerview.widget.RecyclerView;

import com.example.megafilmes_teste.Models.Filme;

public interface FilmeContract {

    interface FilmesListPresenter {
        RecyclerView useRecyclerView(
                RecyclerView recyclerViewFilmes,
                RecyclerView.LayoutManager layout,
                final OnMoviesClickCallback onClickFilme
        );
    }

    interface FilmeOverviewPresenter {
        void getFilmeInfos(int filmeId, OnGetMovieCallback callback);
    }
}
