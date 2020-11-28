package com.example.megafilmes_teste.Interfaces;

import androidx.recyclerview.widget.RecyclerView;

public interface FilmeContract {

    interface FilmeOverviewPresenter {
        void getFilmeInfos(int filmeId, OnGetMovieCallback callback);
    }
}
