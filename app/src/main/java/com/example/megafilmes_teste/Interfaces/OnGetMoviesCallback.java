package com.example.megafilmes_teste.Interfaces;

import com.example.megafilmes_teste.Models.Filme;

import java.util.List;

public interface OnGetMoviesCallback {

    void onSuccess(List<Filme> movies);

    void onError();
}
