package com.example.megafilmes_teste.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.megafilmes_teste.Interfaces.OnSeriesClickCallback;
import com.example.megafilmes_teste.R;
import com.example.megafilmes_teste.Models.Serie;

import java.util.List;

public class SeriesAdapter extends RecyclerView.Adapter<SeriesAdapter.SeriesViewHolder> {
    private List<Serie> series;
    private String GET_POSTERS = "http://image.tmdb.org/t/p/w500";
    private OnSeriesClickCallback callback;
    
    public SeriesAdapter(List<Serie> series, OnSeriesClickCallback callback) {
        this.series = series;
        this.callback = callback;
    }

    @NonNull
    @Override
    public SeriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_serie, parent, false);
        SeriesViewHolder svh = new SeriesViewHolder(view);
        return svh;
    }

    @Override
    public void onBindViewHolder(@NonNull SeriesViewHolder holder, int position) {
        holder.bind(series.get(position));
    }

    @Override
    public int getItemCount() {
        return series.size();
    }

    public class SeriesViewHolder extends RecyclerView.ViewHolder{
        ImageView viewSerie;
        Serie serie;

        public SeriesViewHolder(View itemView) {
            super(itemView);
            viewSerie = itemView.findViewById(R.id.image_serie);
            viewSerie.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callback.onClick(serie);
                }
            });
        }


        public void bind(Serie serie) {
            this.serie = serie;
            Glide.with(itemView)
                    .load(GET_POSTERS + serie.getPosterPath())
                    .apply(RequestOptions.placeholderOf(R.color.colorPrimary))
                    .into(viewSerie);
        }
    }
}
