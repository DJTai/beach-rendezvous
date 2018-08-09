package com.example.beachrendezvous.database;

public class FoodEntity {

    // Variables shown
    /** Date of the Food event */
    private String date;

    /** The location where the restaurant is at */
    private String location;

    /** The time when the event happens */
    private String time;

    /** Max number of people who can attend the event */
    private int num_max;

    /** Comments regarding the event, submitted by the creator */
    private String comments;

    // Variables stored, but not shown
    /** Who created the event */
    private String created_by;

    /** Restaurant venue */
    private String restaurant;

    public FoodEntity() {
        // required empty constructor
    }

    public FoodEntity(String date, String location, String time, int num_max, String comments,
                      String created_by, String restaurant) {
        this.date = date;
        this.location = location;
        this.time = time;
        this.num_max = num_max;
        this.comments = comments;
        this.created_by = created_by;
        this.restaurant = restaurant;
    }

    public String getDate() { return date; }

    public void setDate(String date) { this.date = date; }

    public String getLocation() { return location; }

    public void setLocation(String location) { this.location = location; }

    public String getTime() { return time; }

    public void setTime(String time) { this.time = time; }

    public int getNum_max() { return num_max; }

    public void setNum_max(int num_max) { this.num_max = num_max; }

    public String getComments() { return comments; }

    public void setComments(String comments) { this.comments = comments; }

    public String getCreated_by() { return created_by; }

    public void setCreated_by(String created_by) { this.created_by = created_by; }

    public String getRestaurant() { return restaurant; }

    public void setRestaurant(String restaurant) { this.restaurant = restaurant; }

    @Override
    public String toString() {
        return "FoodEntity{" +
                "date='" + date + '\'' +
                ", location='" + location + '\'' +
                ", time='" + time + '\'' +
                ", num_max=" + num_max +
                ", comments='" + comments + '\'' +
                ", created_by='" + created_by + '\'' +
                ", restaurant='" + restaurant + '\'' +
                '}';
    }
}
