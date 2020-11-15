package com.example.megafilmes_teste.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
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
    public static String SERIE_ID = "serie_id";
    private static String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w780";

    private SerieOverviewPresenter serieOverviewPresenter;

    private EPSerieAdapter adapterSerie;
    private SerieServices serieRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview__series);

        int serieId = 0;
        serieId = getIntent().getIntExtra(SERIE_ID, serieId);

        serieOverviewPresenter = new SerieOverviewPresenter();

        System.out.println("Serie ID: " + serieId);

        serieRepository = new SerieServices();
        List<Serie> serie = new ArrayList<>();


        serieOverviewPresenter.getSeriesInfos(serieId, new OnGetSerieCallback(){
            public void onSuccess(Serie serie){
                TextView serieTitle = findViewById(R.id.tvTitleSerie);
                ImageView serieBackdrop = findViewById(R.id.poster_serie);

                serieTitle.setText(serie.getTitle());

                if (!isFinishing()) {
                    Glide.with(OverviewSeries.this)
                            .load(IMAGE_BASE_URL + serie.getBackdrop())
                            .apply(RequestOptions.placeholderOf(R.color.colorPrimary))
                            .into(serieBackdrop);
                }
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