package com.example.beachrendezvous.database;

import java.io.Serializable;

public class FoodEntity implements Serializable {

    // Variables shown
    private String date;
    private String location;
    private String time;
    private String num_max;
    private String duration;
    private String comments;

    // Variables stored, but not shown
    private String created_by;
    private String type;
    private String limit;

    //region Constructors
    public FoodEntity() {
        // required empty constructor
    }

    public FoodEntity(String created_by, String time, String duration, String date,
                      String location, String num_max, String comments, String type, String limit) {
        this.date = date;
        this.location = location;
        this.time = time;
        this.num_max = num_max;
        this.duration = duration;
        this.comments = comments;
        this.created_by = created_by;
        this.type = type;
        this.limit = limit;
    }

    //endregion

    //region Getters & Setters

    public String getDate() { return date; }

    public void setDate(String date) { this.date = date; }

    public String getLocation() { return location; }

    public void setLocation(String location) { this.location = location; }

    public String getTime() { return time; }

    public void setTime(String time) { this.time = time; }

    public String getNum_max() { return num_max; }

    public void setNum_max(String num_max) { this.num_max = num_max; }

    public String getDuration() { return duration; }

    public void setDuration(String duration) { this.duration = duration; }

    public String getComments() { return comments; }

    public void setComments(String comments) { this.comments = comments; }

    public String getCreated_by() { return created_by; }

    public void setCreated_by(String created_by) { this.created_by = created_by; }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public String getLimit() { return limit; }

    public void setLimit(String limit) { this.limit = limit; }

    //endregion


    @Override
    public String toString() {
        return "FoodEntity{" +
                "date='" + date + '\'' +
                ", location='" + location + '\'' +
                ", time='" + time + '\'' +
                ", num_max='" + num_max + '\'' +
                ", duration='" + duration + '\'' +
                ", comments='" + comments + '\'' +
                ", created_by='" + created_by + '\'' +
                ", type='" + type + '\'' +
                ", limit='" + limit + '\'' +
                '}';
    }
}
