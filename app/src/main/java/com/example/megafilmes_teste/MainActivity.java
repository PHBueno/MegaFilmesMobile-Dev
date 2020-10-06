package com.example.megafilmes_teste;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.megafilmes_teste.Interfaces.OnGetMoviesCallback;
import com.example.megafilmes_teste.Interfaces.OnGetSeriesCallback;
import com.example.megafilmes_teste.Interfaces.OnMoviesClickCallback;
import com.example.megafilmes_teste.MovieService.Service;
import com.example.megafilmes_teste.MovieService.ServiceSerie;
import com.example.megafilmes_teste.RecyclerViewAdapter.MoviesAdapter;
import com.example.megafilmes_teste.RecyclerViewAdapter.SeriesAdapter;
import com.example.megafilmes_teste.model.Filme;
import com.example.megafilmes_teste.model.Serie;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity{
    private Button button;

    private RecyclerView moviesList;
    private MoviesAdapter adapter;

    private SeriesAdapter adapterSerie;
    private ServiceSerie serieRepository;

    private Service movieRepository;

    OnMoviesClickCallback callback = new OnMoviesClickCallback() {
        @Override
        public void onClick(Filme filme) {
            Intent intent = new Intent(MainActivity.this, overview_movie.class);
            intent.putExtra(overview_movie.MOVIE_ID, filme.getId());
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /********************************
        *  SETA ADAPTER PARA FILMES...  *
         ********************************/

        movieRepository = Service.getInstance();

        moviesList = findViewById(R.id.recyclerview_filmes);
        moviesList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));


        movieRepository.getMovies(new OnGetMoviesCallback() {
            @Override
            public void onSuccess(List<Filme> movies) {
                    adapter = new MoviesAdapter(movies, callback);
                    moviesList.setAdapter(adapter);
            }

            @Override
            public void onError() {
                Toast.makeText(MainActivity.this, "Please check your internet connection.", Toast.LENGTH_SHORT).show();
            }
        });


        /********************************
         * SETA ADAPTER PARA SÉRIES...  *
         ********************************/

        serieRepository = ServiceSerie.getInstance();

        final RecyclerView seriesList = findViewById(R.id.recyclerview_series);
        seriesList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        serieRepository.getSeries(new OnGetSeriesCallback() {
            @Override
            public void onSuccess(List<Serie> series) {
                adapterSerie = new SeriesAdapter(series);
                seriesList.setAdapter(adapterSerie);
            }

            @Override
            public void onError() {
                Toast.makeText(MainActivity.this, "Please check your internet connection.", Toast.LENGTH_SHORT).show();
            }
        });
    }
    //TODO: Verificar porque o botão não está sendo clicado;
}