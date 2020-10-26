package com.example.megafilmes_teste.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.megafilmes_teste.R;
import com.example.megafilmes_teste.Services.SerieServices;
import com.example.megafilmes_teste.Adapters.EPSerieAdapter;
import com.example.megafilmes_teste.Models.Serie;

import java.util.ArrayList;
import java.util.List;

public class OverviewSeries extends AppCompatActivity {
    public static String SERIE_ID = "serie_id";

    private EPSerieAdapter adapterSerie;
    private SerieServices serieRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview__series);

        int serieId = 0;
        serieId = getIntent().getIntExtra(SERIE_ID, serieId);

        System.out.println("Serie ID: " + serieId);

        serieRepository = new SerieServices();
        List<Serie> serie = new ArrayList<>();

        RecyclerView rv = findViewById(R.id.recyclerview_ep_series);
        RecyclerView.Adapter adapter = new EPSerieAdapter(serie);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        rv.setAdapter(adapter);
        rv.setLayoutManager(lm);
    }
}