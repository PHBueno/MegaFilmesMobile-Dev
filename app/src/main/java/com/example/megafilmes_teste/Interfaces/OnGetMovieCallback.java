package com.example.megafilmes_teste.Interfaces;

import com.example.megafilmes_teste.model.Filme;

public interface OnGetMovieCallback {

    void onSuccess(Filme filme);

    void onError();
}