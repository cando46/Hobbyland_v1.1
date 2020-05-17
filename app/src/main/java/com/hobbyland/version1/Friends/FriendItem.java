package com.hobbyland.version1.Friends;

public class FriendItem {
    private int profileImgResource;
    private  String name;

    public FriendItem(int profileImgResource, String name) {
        this.profileImgResource = profileImgResource;
        this.name = name;
    }

    public int getProfileImgResource() {
        return profileImgResource;
    }

    public void setProfileImgResource(int profileImgResource) {
        this.profileImgResource = profileImgResource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
