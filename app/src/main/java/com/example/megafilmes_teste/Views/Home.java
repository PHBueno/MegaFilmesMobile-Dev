package com.example.megafilmes_teste.Views;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.Intent;
import android.icu.text.CaseMap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.megafilmes_teste.Adapters.MoviesAdapter;
import com.example.megafilmes_teste.Interfaces.OnGetMoviesPresenterCallback;
import com.example.megafilmes_teste.Interfaces.OnGetSeriesPresenterCallback;
import com.example.megafilmes_teste.Interfaces.OnMoviesClickCallback;
import com.example.megafilmes_teste.Interfaces.OnSeriesClickCallback;
import com.example.megafilmes_teste.Presenters.FilmesListPresenter;
import com.example.megafilmes_teste.Presenters.SeriesListPresenter;
import com.example.megafilmes_teste.R;
import com.example.megafilmes_teste.Services.SerieServices;
import com.example.megafilmes_teste.Adapters.SeriesAdapter;
import com.example.megafilmes_teste.Models.Filme;
import com.example.megafilmes_teste.Models.Serie;
import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity {


    private SeriesAdapter adapterSerie;
    private SerieServices serieRepository;

    private FilmesListPresenter filmesListPresenter;
    private SeriesListPresenter seriesListPresenter;



    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Criando Title
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Início");
        // Combinar o Toolbar com o Menu
        toolbar.inflateMenu(R.menu.main_menu);

        /********************************
         *  SETA ADAPTER PARA FILMES...  *
         ********************************/

        final RecyclerView filmesRecyclerView = findViewById(R.id.recyclerview_filmes);

        RecyclerView.LayoutManager layoutFilmes = new LinearLayoutManager(
                this, LinearLayoutManager.HORIZONTAL,
                false
        );

        filmesRecyclerView.setLayoutManager(layoutFilmes);

        OnGetMoviesPresenterCallback onGetMoviesPresenter = new OnGetMoviesPresenterCallback() {
            public void onSuccess(MoviesAdapter filmesAdapter) {
                filmesRecyclerView.setAdapter(filmesAdapter);
            }
        };

        OnMoviesClickCallback onClickMovie = new OnMoviesClickCallback() {
            public void onClick(Filme filme) {
                Intent intent = new Intent(Home.this, OverviewMovie.class);
                intent.putExtra(OverviewMovie.MOVIE_ID, filme.getId());
                startActivity(intent);
            }
        };

        filmesListPresenter = new FilmesListPresenter(onGetMoviesPresenter, onClickMovie);


        /********************************
         * SETA ADAPTER PARA SÉRIES...  *
         ********************************/

        final RecyclerView seriesRecyclerView = findViewById(R.id.recyclerview_series);

        RecyclerView.LayoutManager layoutSeries = new LinearLayoutManager(
                this, LinearLayoutManager.HORIZONTAL,
                false
        );

        seriesRecyclerView.setLayoutManager(layoutSeries);

        OnGetSeriesPresenterCallback onGetSeriesPresenter = new OnGetSeriesPresenterCallback() {
            public void onSuccess(SeriesAdapter seriesAdapter) {
                seriesRecyclerView.setAdapter(seriesAdapter);
            }
        };

        OnSeriesClickCallback onClickSerie = new OnSeriesClickCallback() {
            public void onClick(Serie serie) {
                Intent intent = new Intent(Home.this, OverviewSeries.class);
                intent.putExtra(OverviewSeries.SERIE_ID, serie.getId());
                startActivity(intent);
            }
        };



        seriesListPresenter = new SeriesListPresenter(
                onGetSeriesPresenter,
                onClickSerie
        );


    }
    public void quitApp(MenuItem v)
    {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), Login.class));
        finish();
    }




}