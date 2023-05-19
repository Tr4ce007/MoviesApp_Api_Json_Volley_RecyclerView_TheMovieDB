package com.example.moviesapp.Models;

public class NowPlayingModel {

    private String id, title, release_date, poster_path, overview;

    public NowPlayingModel(String id, String title, String release_date, String poster_path, String overview) {
        this.id = id;
        this.title = title;
        this.release_date = release_date;
        this.poster_path = poster_path;
        this.overview = overview;
    }


    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }
}
