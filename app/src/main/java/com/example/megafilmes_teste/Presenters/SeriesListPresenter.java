package com.example.megafilmes_teste.Presenters;

import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.megafilmes_teste.Adapters.SeriesAdapter;
import com.example.megafilmes_teste.Interfaces.OnGetSeriesCallback;
import com.example.megafilmes_teste.Interfaces.OnSeriesClickCallback;
import com.example.megafilmes_teste.Interfaces.SeriesContract;
import com.example.megafilmes_teste.Models.Serie;
import com.example.megafilmes_teste.Services.SerieServices;

import java.util.List;

public class SeriesListPresenter implements SeriesContract.SeriesListPresenter {

    private static SerieServices seriesAPI;

    private RecyclerView recyclerViewSeries;
    private SeriesAdapter seriesAdapter;

    public SeriesListPresenter() {
        this.seriesAPI = new SerieServices().repository;
    }

    public RecyclerView useRecyclerView (
            RecyclerView recyclerView,
            RecyclerView.LayoutManager layout,
            final OnSeriesClickCallback onClickSerie
    ) {

        this.recyclerViewSeries = recyclerView;
        this.recyclerViewSeries.setLayoutManager(layout);

        this.seriesAPI.getSeries(new OnGetSeriesCallback() {
            @Override
            public void onSuccess(List<Serie> series) {
                seriesAdapter = new SeriesAdapter(series, onClickSerie);
                recyclerViewSeries.setAdapter(seriesAdapter);
            }

            @Override
            public void onError() {
                Toast.makeText(
                        null,
                        "Please check your internet connection.",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });

        return recyclerViewSeries;
    }

    public RecyclerView getRecyclerViewSeries() {
        return this.recyclerViewSeries;
    }

}
