package com.example.megafilmes_teste.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.megafilmes_teste.R;
import com.example.megafilmes_teste.Models.Serie;

import java.util.List;

public class EPSerieAdapter extends RecyclerView.Adapter<EPSerieAdapter.EPViewHolder> {
    private List<Serie> series;

    @NonNull
    @Override
    public EPViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_serie_overview_recyclerview, parent, false);
        EPViewHolder evh = new EPViewHolder(view);
        return evh;
    }

    public EPSerieAdapter(List<Serie> series) {
        this.series = series;
    }

    @Override
    public void onBindViewHolder(@NonNull EPSerieAdapter.EPViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class EPViewHolder extends RecyclerView.ViewHolder{
        TextView ep_name;
        Serie serie;
        public EPViewHolder(View itemView){
            super(itemView);
        }
    }
}
