package com.example.megafilmes_teste.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
    private Button btnShare;
    public static String MOVIE_ID = "movie_id";
    private static String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w780";

    private FilmeOverviewPresenter filmeOverviewPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview_movie);

        btnShare = (Button) findViewById(R.id.btnShare);

        int movieId = 0;
        movieId = getIntent().getIntExtra(MOVIE_ID, movieId);
        filmeOverviewPresenter = new FilmeOverviewPresenter();

        filmeOverviewPresenter.getFilmeInfos(movieId, new OnGetMovieCallback() {
            @Override
            public void onSuccess(Filme filme) {
                ImageView movieBackdrop = findViewById(R.id.overview_poster);
                TextView movieOverview = findViewById(R.id.overview_text);

                movieOverview.setText(filme.getOverview());

                final String ID_FILME = String.valueOf(filme.getId());


                if (!isFinishing()) {
                    Glide.with(OverviewMovie.this)
                        .load(IMAGE_BASE_URL + filme.getBackdrop())
                        .apply(RequestOptions.placeholderOf(R.color.colorPrimary))
                        .into(movieBackdrop);
                }

                btnShare.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent sendIntent = new Intent();
                        sendIntent.setAction(Intent.ACTION_SEND);
                        sendIntent.putExtra(Intent.EXTRA_TEXT, ID_FILME);
                        sendIntent.setType("text/plain");

                        if (sendIntent.resolveActivity(getPackageManager()) != null){
                            startActivity(sendIntent);
                        }
                    }
                });
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
