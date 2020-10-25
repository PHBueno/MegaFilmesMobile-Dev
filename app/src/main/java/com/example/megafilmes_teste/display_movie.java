package com.example.megafilmes_teste;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.megafilmes_teste.Interfaces.OnGetMoviesCallback;
import com.example.megafilmes_teste.Interfaces.OnGetSeriesCallback;
import com.example.megafilmes_teste.Interfaces.OnMoviesClickCallback;
import com.example.megafilmes_teste.Interfaces.OnSeriesClickCallback;
import com.example.megafilmes_teste.MovieService.Service;
import com.example.megafilmes_teste.MovieService.ServiceSerie;
import com.example.megafilmes_teste.RecyclerViewAdapter.MoviesAdapter;
import com.example.megafilmes_teste.RecyclerViewAdapter.SeriesAdapter;
import com.example.megafilmes_teste.model.Filme;
import com.example.megafilmes_teste.model.Serie;

import java.util.List;

public class display_movie extends AppCompatActivity{

    private RecyclerView moviesList;
    private MoviesAdapter adapter;

    private SeriesAdapter adapterSerie;
    private ServiceSerie serieRepository;

    private Service movieRepository;

    OnMoviesClickCallback callback = new OnMoviesClickCallback() {
        @Override
        public void onClick(Filme filme) {
            Intent intent = new Intent(display_movie.this, overview_movie.class);
            intent.putExtra(overview_movie.MOVIE_ID, filme.getId());
            startActivity(intent);
        }
    };

    OnSeriesClickCallback callback_serie = new OnSeriesClickCallback() {
        @Override
        public void onClick(Serie serie) {
            Intent intent = new Intent(display_movie.this, Overview_Series.class);
            //intent.putExtra(overview_movie.MOVIE_ID, filme.getId());
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
                Toast.makeText(display_movie.this, "Please check your internet connection.", Toast.LENGTH_SHORT).show();
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
                adapterSerie = new SeriesAdapter(series, callback_serie);
                seriesList.setAdapter(adapterSerie);
            }

            @Override
            public void onError() {
                Toast.makeText(display_movie.this, "Please check your internet connection.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}