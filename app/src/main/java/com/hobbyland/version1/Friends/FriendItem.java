package com.hobbyland.version1.Friends;

public class FriendItem {
   String username;
   String status;
   String UID;

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public FriendItem(String username, String status, String UID) {
        this.username = username;
        this.status = status;
        this.UID=UID;
    }

    public FriendItem() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
