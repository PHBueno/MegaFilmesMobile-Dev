package com.example.megafilmes_teste.Interfaces;

import com.example.megafilmes_teste.model.Filme;
import com.example.megafilmes_teste.model.Serie;

public interface OnGetEpCallback {
    void onSuccess(Serie serie);

    void onError();
}
