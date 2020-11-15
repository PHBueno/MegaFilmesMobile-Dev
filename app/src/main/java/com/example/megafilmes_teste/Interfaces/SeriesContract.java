package com.example.megafilmes_teste.Interfaces;

import androidx.recyclerview.widget.RecyclerView;

public interface SeriesContract {

    interface SeriesListPresenter {
        RecyclerView useRecyclerView(
                RecyclerView recyclerViewSeries,
                RecyclerView.LayoutManager layout,
                final OnSeriesClickCallback onClickSerie
        );
    }
    interface SerieOverviewPresenter {
        void getSeriesInfos(int serieId, OnGetSerieCallback callback);
    }

}
