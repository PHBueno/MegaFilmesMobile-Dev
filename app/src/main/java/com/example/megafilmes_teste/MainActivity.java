package com.example.megafilmes_teste;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.megafilmes_teste.Interfaces.OnGetMoviesCallback;
import com.example.megafilmes_teste.Interfaces.OnGetSeriesCallback;
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

    private RecyclerView moviesList;
    private MoviesAdapter adapter;

    private SeriesAdapter adapterSerie;
    private ServiceSerie serieRepository;

    private Service movieRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieRepository = Service.getInstance();

        moviesList = findViewById(R.id.recyclerview_filmes);
        moviesList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));


        movieRepository.getMovies(new OnGetMoviesCallback() {
            @Override
            public void onSuccess(List<Filme> movies) {
                adapter = new MoviesAdapter(movies);
                moviesList.setAdapter(adapter);
            }

            @Override
            public void onError() {
                Toast.makeText(MainActivity.this, "Please check your internet connection.", Toast.LENGTH_SHORT).show();
            }
        });

        //SETA ADAPTER PARA RECYCLERVIEW DE SERIES.

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
        //rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        //List<Serie> series = new ArrayList<>();
        //adapterSerie = new SeriesAdapter(series);
        //rv.setAdapter(adapterSerie);

    }

}