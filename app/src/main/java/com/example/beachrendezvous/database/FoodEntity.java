package com.example.beachrendezvous.database;

import java.sql.Time;
import java.util.Date;

public class FoodEntity {

    private Time time;
    private Date date;
    private String location;
    private int attending;
    private String comments;

    public FoodEntity(){

    }

    public FoodEntity(Time time, Date date, String location, int attending, String comments) {
        this.time = time;
        this.date = date;
        this.location = location;
        this.attending = attending;
        this.comments = comments;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getAttending() {
        return attending;
    }

    public void setAttending(int attending) {
        this.attending = attending;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "FoodEntity{" +
                "time=" + time +
                ", date=" + date +
                ", location='" + location + '\'' +
                ", attending=" + attending +
                ", comments='" + comments + '\'' +
                '}';
    }
}
