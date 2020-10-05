package com.example.megafilmes_teste;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.megafilmes_teste.Interfaces.OnGetMoviesCallback;
import com.example.megafilmes_teste.MovieService.Service;
import com.example.megafilmes_teste.RecyclerViewAdapter.MoviesAdapter;
import com.example.megafilmes_teste.model.Filme;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    private RecyclerView moviesList;
    private MoviesAdapter adapter;

    private Service movieRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieRepository = Service.getInstance();

        List<Filme> filmes = new ArrayList<>();

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

    }

}