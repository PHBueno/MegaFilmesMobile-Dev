package com.example.megafilmes_teste.RecyclerViewAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.megafilmes_teste.R;
import com.example.megafilmes_teste.model.Serie;

import java.util.List;

public class SeriesAdapter extends RecyclerView.Adapter<SeriesAdapter.SeriesViewHolder> {
    private List<Serie> series;
    
    public SeriesAdapter(List<Serie> series) {this.series = series;}

    @NonNull
    @Override
    public SeriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_serie, parent, false);
        SeriesViewHolder svh = new SeriesViewHolder(view);
        return svh;
    }

    @Override
    public void onBindViewHolder(@NonNull SeriesViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 40;
    }

    public class SeriesViewHolder extends RecyclerView.ViewHolder{
        public  View viewSerie;

        public SeriesViewHolder(View itemView) {
            super(itemView);
            this.viewSerie = itemView;
        }


    }
}
