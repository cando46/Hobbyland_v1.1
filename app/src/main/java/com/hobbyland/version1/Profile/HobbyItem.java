package com.hobbyland.version1.Profile;

public class HobbyItem {
    public String hobbyName;
    public String experience;
    public String knowledge;
    public String skillLevel;

    public HobbyItem() {
    }

    public HobbyItem(String hobbyName, String experience, String knowledge, String skillLevel) {
        this.hobbyName = hobbyName;
        this.experience = experience;
        this.knowledge = knowledge;
        this.skillLevel = skillLevel;
    }

    public String getHobbyName() {
        return hobbyName;
    }

    public void setHobbyName(String hobbyName) {
        this.hobbyName = hobbyName;
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

    public String getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(String skillLevel) {
        this.skillLevel = skillLevel;
    }
}
