package com.hobbyland.version1.FindPartner;

public class ResultItem {
    String username;
    String age;
    String experience;
    String knowledge;
    String skill;
    String UID;
    String hobbyName;

    public ResultItem(String username, String age, String experience, String knowledge, String skill, String UID, String hobbyName) {
        this.username = username;
        this.age = age;
        this.experience = experience;
        this.knowledge = knowledge;
        this.skill = skill;
        this.UID = UID;
        this.hobbyName = hobbyName;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getKnowledge() {
        return knowledge;
    }

    public void setKnowledge(String knowledge) {
        this.knowledge = knowledge;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getHobbyName() {
        return hobbyName;
    }

    public void setHobbyName(String hobbyName) {
        this.hobbyName = hobbyName;
    }
}
