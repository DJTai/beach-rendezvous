package com.example.beachrendezvous.database;

import java.sql.Time;
import java.util.Date;

public class SportsEntity {

    private String time;
    private String date;
    private String location;
    private int num_max;
    private String comments;
    private  String Created_by;
    private  String type;

    public SportsEntity(){

    }

    public SportsEntity(String Created_by,String time, String date, String location, int num_max, String comments, String type) {
        this.time = time;
        this.date = date;
        this.location = location;
        this.num_max = num_max;
        this.comments = comments;
        this.Created_by=Created_by;
        this.type=type;
    }


    public String getTime() {
        return time;
    }
    public String getType() {
        return type;
    }
    public String getCreated_by() {
        return Created_by;
    }

    public void setTime(String time) {
        this.time = time;
    }

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

    @Override
    public String toString() {
        return "SportsEntity{" +"Created_by=" + Created_by +"type=" + type +
                "time=" + time +
                ", date=" + date +
                ", location='" + location + '\'' +
                ", num_max=" + num_max +
                ", comments='" + comments + '\'' +
                '}';
    }
}




