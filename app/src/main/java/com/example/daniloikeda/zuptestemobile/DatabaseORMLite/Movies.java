package com.example.daniloikeda.zuptestemobile.DatabaseORMLite;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by DaniloIkeda on 13/01/2017.
 */
public class Movies {
    @DatabaseField(generatedId = true)
    int id;

    @DatabaseField
    private String title;

    @DatabaseField
    private String year;

    @DatabaseField
    private String rated;

    @DatabaseField
    private String released;

    @DatabaseField
    private String runtime;

    @DatabaseField
    private String genre;

    @DatabaseField
    private String director;

    @DatabaseField
    private String writer;

    @DatabaseField
    private String actors;

    @DatabaseField
    private String plot;

    @DatabaseField
    private String language;

    @DatabaseField
    private String country;

    @DatabaseField
    private String awards;

    @DatabaseField
    private String poster;

    @DatabaseField
    private String metascore;

    @DatabaseField
    private String imdbRating;

    @DatabaseField
    private String imdbVotes;

    @DatabaseField
    private String imdbID;

    @DatabaseField
    private String type;

    public Movies(){

    }

    public Movies(String title, String year, String rated, String released, String runtime, String genre, String director, String writer,
                  String actors, String plot, String language, String country, String awards, String poster, String metascore, String imdbRating,
                  String imdbVotes, String imdbID, String type){
        super();
        this.title = title;
        this.year = year;
        this.rated = rated;
        this.released = released;
        this.runtime = runtime;
        this.genre = genre;
        this.director = director;
        this.writer = writer;
        this.actors = actors;
        this.plot = plot;
        this.language = language;
        this.country = country;
        this.awards = awards;
        this.poster = poster;
        this.metascore = metascore;
        this.imdbRating = imdbRating;
        this.imdbVotes = imdbVotes;
        this.imdbID = imdbID;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Movies [ id =" + id+
                "title =" + title +
                " year =" + year +
                " rated =" + rated +
                " released =" + released +
                " runtime =" + runtime +
                " genre =" + genre +
                " director =" + director +
                " writer =" + writer +
                " actors =" + actors +
                " plot =" + plot +
                " language =" + language +
                " country =" + country +
                " awards =" + awards +
                " poster =" + poster +
                " metascore =" + metascore +
                " imdbRating =" + imdbRating +
                " imdbVotes =" + imdbVotes +
                " imdbID =" + imdbID +
                " type =]" + type;
    }

    public String getTitle(){
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getRated() {
        return rated;
    }

    public String getReleased() {
        return released;
    }

    public String getRuntime() {
        return runtime;
    }

    public String getGenre() {
        return genre;
    }

    public String getDirector() {
        return director;
    }

    public String getWriter() {
        return writer;
    }

    public String getActors() {
        return actors;
    }

    public String getPlot() {
        return plot;
    }

    public String getLanguage() {
        return language;
    }

    public String getCountry() {
        return country;
    }

    public String getAwards() {
        return awards;
    }

    public String getPoster() {
        return poster;
    }

    public String getMetascore() {
        return metascore;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public String getImdbVotes() {
        return imdbVotes;
    }

    public String getImdbID() {
        return imdbID;
    }

    public String getType() {
        return type;
    }
}
