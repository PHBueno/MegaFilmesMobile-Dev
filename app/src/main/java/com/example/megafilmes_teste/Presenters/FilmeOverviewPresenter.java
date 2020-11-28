package com.example.megafilmes_teste.Presenters;

import com.example.megafilmes_teste.Interfaces.FilmeContract;
import com.example.megafilmes_teste.Interfaces.OnGetMovieCallback;
import com.example.megafilmes_teste.Models.Filme;
import com.example.megafilmes_teste.Services.MovieServices;

public class FilmeOverviewPresenter implements FilmeContract.FilmeOverviewPresenter {

    private static MovieServices filmeAPI;

    private Filme filme;

    public FilmeOverviewPresenter() {

        filmeAPI = new MovieServices().repository;
    }

    public void getFilmeInfos(int filmeId, final OnGetMovieCallback callback) {

         this.filmeAPI.getMovie(filmeId, new OnGetMovieCallback() {

             public void onSuccess(Filme filmeResponse) {
                setFilme(filmeResponse);
                callback.onSuccess(filmeResponse);
             }

            public void onError() {
                callback.onError();
            }
        });

    }

    private void setFilme (Filme filme) {
        this.filme = filme;
    }

    public Filme getFilme() {
        System.out.println("Get Filme: ");

        return this.filme;
    }
}
