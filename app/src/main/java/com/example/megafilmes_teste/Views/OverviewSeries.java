package com.example.megafilmes_teste.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.megafilmes_teste.Interfaces.OnGetSerieCallback;
import com.example.megafilmes_teste.R;
import com.example.megafilmes_teste.Interfaces.OnGetSeriesCallback;
import com.example.megafilmes_teste.Services.SerieServices;
import com.example.megafilmes_teste.Adapters.EPSerieAdapter;
import com.example.megafilmes_teste.Models.Serie;
import com.example.megafilmes_teste.Presenters.SerieOverviewPresenter;


import java.util.ArrayList;
import java.util.List;

public class OverviewSeries extends AppCompatActivity {
    private Button btnShareSerie;
    public static String SERIE_ID = "serie_id";
    private static String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w780";

    private SerieOverviewPresenter serieOverviewPresenter;

    private EPSerieAdapter adapterSerie;
    private SerieServices serieRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview__series);

        btnShareSerie = (Button) findViewById(R.id.btnShareSerie);

        int serieId = 0;
        serieId = getIntent().getIntExtra(SERIE_ID, serieId);

        serieOverviewPresenter = new SerieOverviewPresenter();

        serieRepository = new SerieServices();
        List<Serie> serie = new ArrayList<>();


        serieOverviewPresenter.getSeriesInfos(serieId, new OnGetSerieCallback(){
            public void onSuccess(Serie serie){
                TextView serieTitle = findViewById(R.id.tvTitleSerie);
                ImageView serieBackdrop = findViewById(R.id.poster_serie);
                TextView serieOverview = findViewById(R.id.tvOverview);

                serieTitle.setText(serie.getTitle());
                serieOverview.setText(serie.getOverview());

                final String ID_SERIE = String.valueOf(serie.getId());

                if (!isFinishing()) {
                    Glide.with(OverviewSeries.this)
                            .load(IMAGE_BASE_URL + serie.getBackdrop())
                            .apply(RequestOptions.placeholderOf(R.color.colorPrimary))
                            .into(serieBackdrop);
                }
                btnShareSerie.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent sendIntent = new Intent();
                        sendIntent.setAction(Intent.ACTION_SEND);
                        sendIntent.putExtra(Intent.EXTRA_TEXT, "uniritterfilmes.edu.br/serie?id="+ID_SERIE);
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

        /*RecyclerView rv = findViewById(R.id.recyclerview_ep_series);
        RecyclerView.Adapter adapter = new EPSerieAdapter(serie);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        rv.setAdapter(adapter);
        rv.setLayoutManager(lm);*/


    }
}