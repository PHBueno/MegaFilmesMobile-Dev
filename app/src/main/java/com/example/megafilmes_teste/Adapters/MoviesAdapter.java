package com.example.megafilmes_teste.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
//import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.megafilmes_teste.Interfaces.OnMoviesClickCallback;
import com.example.megafilmes_teste.R;
import com.example.megafilmes_teste.Models.Filme;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.FilmesViewHolder>{
    private String GET_POSTERS = "http://image.tmdb.org/t/p/w500";
    private List<Filme> movies;
    private OnMoviesClickCallback callback;


    public MoviesAdapter(List<Filme> movies, OnMoviesClickCallback callback) {
        this.movies = movies;
        this.callback = callback;
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
        //TextView txtTitle;
        ImageView poster;
        Filme filme;
       public FilmesViewHolder(View itemView) {
           super(itemView);
           //txtTitle = itemView.findViewById(R.id.filme_name);
           poster = itemView.findViewById(R.id.image_filme);
           poster.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   callback.onClick(filme);
               }
           });
       }


       /********************
        * ADICIONA IMAGENS *
        * NA RECYCLERVIEW  *
        * DOS FILMES       *
        ********************/
        public void bind(Filme filme) {
           //txtTitle.setText(filme.getTitle());
            // Insere imagem do poster
            this.filme = filme;
            Glide.with(itemView)
                    .load(GET_POSTERS + filme.getPosterPath())
                    .apply(RequestOptions.placeholderOf(R.color.colorPrimary))
                    .into(poster);
        }
    }
}