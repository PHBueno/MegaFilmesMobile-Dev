package com.example.megafilmes_teste.Interfaces;

import com.example.megafilmes_teste.Models.Serie;

public interface OnGetEpCallback {
    void onSuccess(Serie serie);

    void onError();
}
