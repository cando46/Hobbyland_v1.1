package com.hobbyland.version1.HelperClasses;

public class EventHelperClass {

    private String title;
    private String hobbyName;
    private String date;
    private String quota;
    private String location;
    private String creatorUID;

    public EventHelperClass(String title, String hobbyName, String date, String quota, String location, String creatorUID) {
        this.title = title;
        this.hobbyName = hobbyName;
        this.date = date;
        this.quota = quota;
        this.location = location;
        this.creatorUID = creatorUID;
    }

    public EventHelperClass() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHobbyName() {
        return hobbyName;
    }

    public void setHobbyName(String hobbyName) {
        this.hobbyName = hobbyName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getQuota() {
        return quota;
    }

    public void setQuota(String quota) {
        this.quota = quota;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCreatorUID() {
        return creatorUID;
    }

    public void setCreatorUID(String creatorUID) {
        this.creatorUID = creatorUID;
    }
}
