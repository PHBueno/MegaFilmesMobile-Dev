package com.example.megafilmes_teste.Presenters;

import android.widget.Toast;


import com.example.megafilmes_teste.Adapters.SeriesAdapter;
import com.example.megafilmes_teste.Interfaces.OnGetSeriesCallback;
import com.example.megafilmes_teste.Interfaces.OnGetSeriesPresenterCallback;
import com.example.megafilmes_teste.Interfaces.OnSeriesClickCallback;
import com.example.megafilmes_teste.Models.Serie;
import com.example.megafilmes_teste.Services.SerieServices;

import java.util.List;

public class SeriesListPresenter {

    private static SerieServices seriesAPI;

    private SeriesAdapter seriesAdapter;

    public SeriesListPresenter(
            final OnGetSeriesPresenterCallback onGetSeries,
            final OnSeriesClickCallback onClickSerie
    ) {
        this.seriesAPI = new SerieServices().repository;

        this.seriesAPI.getSeries(new OnGetSeriesCallback() {
            @Override
            public void onSuccess(List<Serie> series) {
                seriesAdapter = new SeriesAdapter(series, onClickSerie);
                onGetSeries.onSuccess(seriesAdapter);
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


    }
}
