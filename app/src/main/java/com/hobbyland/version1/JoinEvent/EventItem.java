package com.hobbyland.version1.JoinEvent;

public class EventItem {

    private int eventID;
    private int imageResourceId;
    private String name;
    private String date;
    private String quota;
    private String location;
    private boolean expanded;

    public EventItem(int eventID, int imageResourceId, String name, String date, String quota, String location) {
        this.eventID = eventID;
        this.imageResourceId = imageResourceId;
        this.name = name;
        this.date = date;
        this.quota = quota;
        this.location = location;
        this.expanded=false;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
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
}
