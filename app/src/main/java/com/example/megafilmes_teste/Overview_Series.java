package com.example.megafilmes_teste;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.megafilmes_teste.Interfaces.OnGetSeriesCallback;
import com.example.megafilmes_teste.MovieService.ServiceSerie;
import com.example.megafilmes_teste.RecyclerViewAdapter.EPSerieAdapter;
import com.example.megafilmes_teste.RecyclerViewAdapter.SeriesAdapter;
import com.example.megafilmes_teste.model.Serie;

import java.util.ArrayList;
import java.util.List;

public class Overview_Series extends AppCompatActivity {
    private EPSerieAdapter adapterSerie;
    private ServiceSerie serieRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview__series);
        serieRepository = ServiceSerie.getInstance();
        List<Serie> serie = new ArrayList<>();
        RecyclerView rv = findViewById(R.id.recyclerview_ep_series);
        RecyclerView.Adapter adapter = new EPSerieAdapter(serie);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        rv.setAdapter(adapter);
        rv.setLayoutManager(lm);
    }
}