package com.example.beachrendezvous.database;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

public class SportsEntity implements Serializable {

    private String time;
    private String date;
    private String location;
    private String num_max;
    private String comments;
    private  String created_by;
    private  String type;
    private  String duration;


    public SportsEntity(){

    }

    public SportsEntity(String created_by,String time,String duration, String date, String location, String num_max, String comments, String type) {
        this.time = time;
        this.duration=duration;
        this.date = date;
        this.location = location;
        this.num_max = num_max;
        this.comments = comments;
        this.created_by=created_by;
        this.type=type;
    }


    public String getTime() {
        return time;
    }


    public String getType() {
        return type;
    }
    public  void setType(String type){
        this.type=type;
    };

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by)
    {
        this.created_by=created_by;
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

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String  getNum_max() {
        return num_max;
    }

    public void setNum_max(String num_max) {
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
        return "SportsEntity{" +"created_by=" + created_by +"type=" + type +
                "time=" + time + ", duration=" + duration +
                ", date=" + date +
                ", location='" + location + '\'' +
                ", num_max=" + num_max +
                ", comments='" + comments + '\'' +
                '}';
    }
}




