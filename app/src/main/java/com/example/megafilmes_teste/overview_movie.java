package com.example.megafilmes_teste;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.megafilmes_teste.Interfaces.OnGetMovieCallback;
import com.example.megafilmes_teste.MovieService.Service;
import com.example.megafilmes_teste.model.Filme;

public class overview_movie extends AppCompatActivity {
    public static String MOVIE_ID = "movie_id";
    private static String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w780";

    private TextView movieOverview;
    private ImageView movieBackdrop;

    private Service moviesRepository;
    private int movieId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview_movie);

        movieId = getIntent().getIntExtra(MOVIE_ID, movieId);
        moviesRepository = Service.getInstance();
        initUI();
        getMovie();
    }
    private void initUI(){
        movieBackdrop = findViewById(R.id.overview_poster);
        movieOverview = findViewById(R.id.overview_text);
    }

    private void getMovie(){
        moviesRepository.getMovie(movieId, new OnGetMovieCallback() {
            @Override
            public void onSuccess(Filme filme) {
                movieOverview.setText(filme.getOverview());
                if (!isFinishing()){
                    Glide.with(overview_movie.this)
                            .load(IMAGE_BASE_URL + filme.getBackdrop())
                            .apply(RequestOptions.placeholderOf(R.color.colorPrimary))
                            .into(movieBackdrop);
                }
            }

            @Override
            public void onError() {
                finish();
            }
        });
    }
    private void showError() {
        Toast.makeText(overview_movie.this, "Please check your internet connection.", Toast.LENGTH_SHORT).show();
    }
}