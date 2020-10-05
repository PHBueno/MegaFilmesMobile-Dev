package com.example.megafilmes_teste.RecyclerViewAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.megafilmes_teste.R;
import com.example.megafilmes_teste.model.Filme;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.FilmesViewHolder>{
    private List<Filme> movies;

    public MoviesAdapter(List<Filme> movies) {
        this.movies = movies;
    }

    @NonNull
    @Override
    public FilmesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_filme, parent, false);
        return new FilmesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmesViewHolder holder, int position) {
        holder.bind(movies.get(position));

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class FilmesViewHolder extends RecyclerView.ViewHolder{
        TextView txtTitle;
       public FilmesViewHolder(View itemView) {
           super(itemView);
           txtTitle = itemView.findViewById(R.id.filme_name);
       }

        public void bind(Filme filme) {
           txtTitle.setText(filme.getTitle());
        }
    }
}