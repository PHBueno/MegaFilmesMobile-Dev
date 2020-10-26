package com.example.megafilmes_teste.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.megafilmes_teste.Interfaces.OnGetMovieCallback;
import com.example.megafilmes_teste.Presenters.FilmeOverviewPresenter;
import com.example.megafilmes_teste.Models.Filme;
import com.example.megafilmes_teste.R;

public class OverviewMovie extends AppCompatActivity {
    public static String MOVIE_ID = "movie_id";
    private static String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w780";

    private FilmeOverviewPresenter filmeOverviewPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview_movie);

        int movieId = 0;
        movieId = getIntent().getIntExtra(MOVIE_ID, movieId);
        filmeOverviewPresenter = new FilmeOverviewPresenter();

        filmeOverviewPresenter.getFilmeInfos(movieId, new OnGetMovieCallback() {
            @Override
            public void onSuccess(Filme filme) {
                ImageView movieBackdrop = findViewById(R.id.overview_poster);
                TextView movieOverview = findViewById(R.id.overview_text);

                movieOverview.setText(filme.getOverview());

                if (!isFinishing()) {
                    Glide.with(OverviewMovie.this)
                        .load(IMAGE_BASE_URL + filme.getBackdrop())
                        .apply(RequestOptions.placeholderOf(R.color.colorPrimary))
                        .into(movieBackdrop);
                }
            }

            @Override
            public void onError() {
                Toast.makeText(null, "Please check your internet connection.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    protected void fillData() {

    }

}
