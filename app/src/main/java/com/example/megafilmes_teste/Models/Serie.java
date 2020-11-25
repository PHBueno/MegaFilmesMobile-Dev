package com.example.megafilmes_teste.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Serie {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("overview")
    @Expose
    private String overview;

    /*@SerializedName("title")
    @Expose
    private String title;*/

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("backdrop_path")
    @Expose
    private String backdrop;

    @SerializedName("poster_path")
    @Expose
    private String posterPath;

    @SerializedName("release_date")
    @Expose
    private String releaseDate;

    @SerializedName("vote_average")
    @Expose
    private float rating;

    @SerializedName("genre_ids")
    @Expose
    private List<Integer> genreIds;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return name;
    }

    /*public void setTitle(String title) {
        this.title = title;
    }*/

    public String getBackdrop() {
        return backdrop;
    }

    public void setBackdrop(String backdrop) {
        this.backdrop = backdrop;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getOverview() { return overview; }
    public void setOverview(String overview) {this.overview = overview;}

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
    }
}
