package com.example.beachrendezvous.database;

import java.sql.Time;
import java.util.Date;

public class MoviesEntity {

    private String date;
    private String location;
    private String time;
    private int num_max;
    private String comments;
    private String created_by;
    private String movie;

    //region Constructors

    public MoviesEntity() {
        // required empty constructor
    }

    public MoviesEntity(String date, String location, String time, int num_max,
                        String comments, String created_by, String movie) {
        this.date = date;
        this.location = location;
        this.time = time;
        this.num_max = num_max;
        this.comments = comments;
        this.created_by = created_by;
        this.movie = movie;
    }

    //endregion

    //region Getters and Setters

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getNum_max() {
        return num_max;
    }

    public void setNum_max(int num_max) {
        this.num_max = num_max;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    //endregion

    @Override
    public String toString() {
        return "MoviesEntity{" +
                "date='" + date + '\'' +
                ", location='" + location + '\'' +
                ", time='" + time + '\'' +
                ", num_max=" + num_max +
                ", comments='" + comments + '\'' +
                ", created_by='" + created_by + '\'' +
                ", movie='" + movie + '\'' +
                '}';
    }
}
