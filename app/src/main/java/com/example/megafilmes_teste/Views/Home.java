package com.example.megafilmes_teste.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.megafilmes_teste.Interfaces.OnMoviesClickCallback;
import com.example.megafilmes_teste.Interfaces.OnSeriesClickCallback;
import com.example.megafilmes_teste.Presenters.FilmesListPresenter;
import com.example.megafilmes_teste.Presenters.SeriesListPresenter;
import com.example.megafilmes_teste.R;
import com.example.megafilmes_teste.Services.SerieServices;
import com.example.megafilmes_teste.Adapters.SeriesAdapter;
import com.example.megafilmes_teste.Models.Filme;
import com.example.megafilmes_teste.Models.Serie;

public class Home extends AppCompatActivity{

    private SeriesAdapter adapterSerie;
    private SerieServices serieRepository;

    private FilmesListPresenter filmesListPresenter;
    private SeriesListPresenter seriesListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /********************************
        *  SETA ADAPTER PARA FILMES...  *
         ********************************/

        filmesListPresenter = new FilmesListPresenter();

        RecyclerView.LayoutManager layoutFilmes = new LinearLayoutManager(
                this, LinearLayoutManager.HORIZONTAL,
                false
        );

        OnMoviesClickCallback onClickMovie = new OnMoviesClickCallback() {
            public void onClick(Filme filme) {
                Intent intent = new Intent(Home.this, OverviewMovie.class);
                intent.putExtra(OverviewMovie.MOVIE_ID, filme.getId());
                startActivity(intent);
            }
        };

        RecyclerView filmesRecyclerView = findViewById(R.id.recyclerview_filmes);

        filmesListPresenter.useRecyclerView(
                filmesRecyclerView,
                layoutFilmes,
                onClickMovie
        );

        /********************************
         * SETA ADAPTER PARA SÉRIES...  *
         ********************************/

        seriesListPresenter = new SeriesListPresenter();

        RecyclerView.LayoutManager layoutSeries = new LinearLayoutManager(
                this, LinearLayoutManager.HORIZONTAL,
                false
        );

        OnSeriesClickCallback onClickSerie = new OnSeriesClickCallback() {
            @Override
            public void onClick(Serie serie) {
                Intent intent = new Intent(Home.this, OverviewSeries.class);
                intent.putExtra(OverviewSeries.SERIE_ID, serie.getId());
                startActivity(intent);
            }
        };

        RecyclerView seriesRecyclerView = findViewById(R.id.recyclerview_series);

        seriesListPresenter.useRecyclerView(
                seriesRecyclerView,
                layoutSeries,
                onClickSerie
        );

    }
}