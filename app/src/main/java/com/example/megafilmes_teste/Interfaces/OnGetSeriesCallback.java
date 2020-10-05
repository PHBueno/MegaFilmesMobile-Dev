package com.example.megafilmes_teste.Interfaces;

import com.example.megafilmes_teste.model.Serie;

import java.util.List;

public interface OnGetSeriesCallback {
    void onSuccess(List<Serie> series);

    void onError();
}
