package com.example.megafilmes_teste.RecyclerViewAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.megafilmes_teste.R;
import com.example.megafilmes_teste.model.Serie;

import java.util.List;

public class SeriesAdapter extends RecyclerView.Adapter<SeriesAdapter.SeriesViewHolder> {
    private List<Serie> series;
    private String GET_POSTERS = "http://image.tmdb.org/t/p/w500";
    
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
        holder.bind(series.get(position));
    }

    @Override
    public int getItemCount() {
        return series.size();
    }

    public class SeriesViewHolder extends RecyclerView.ViewHolder{
        ImageView viewSerie;

        public SeriesViewHolder(View itemView) {
            super(itemView);
            viewSerie = itemView.findViewById(R.id.image_serie);
        }


        public void bind(Serie serie) {
            Glide.with(itemView)
                    .load(GET_POSTERS + serie.getPosterPath())
                    .apply(RequestOptions.placeholderOf(R.color.colorPrimary))
                    .into(viewSerie);
        }
    }
}
