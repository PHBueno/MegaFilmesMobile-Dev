package com.example.megafilmes_teste.Presenters;

import com.example.megafilmes_teste.Interfaces.SeriesContract;
import com.example.megafilmes_teste.Interfaces.OnGetSerieCallback;
import com.example.megafilmes_teste.Models.Serie;
import com.example.megafilmes_teste.Services.SerieServices;

public class SerieOverviewPresenter implements SeriesContract.SerieOverviewPresenter {
    private static SerieServices serieAPI;

    private Serie serie;

    public SerieOverviewPresenter() {
        serieAPI = new SerieServices().repository;
    }

    public void getSeriesInfos(int serieId, final OnGetSerieCallback callback) {

        this.serieAPI.getSerie(serieId, new OnGetSerieCallback() {

            public void onSuccess(Serie serieResponse) {
                setSerie(serieResponse);
                callback.onSuccess(serieResponse);
            }
            public void onError() {
                callback.onError();
            }
        });
    }

    private void setSerie (Serie serie) {
        this.serie = serie;
    }

    public Serie getSerie() {
        System.out.println("Get Serie: ");

        return this.serie;
    }

}
