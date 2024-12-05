package com.techelevator.model;

public class Swiped {
    private int userId;
    private int breedId;
    private String img;
    private boolean isYes;

    public Swiped(int userId, int breedId, String img, boolean isYes) {
        this.userId = userId;
        this.breedId = breedId;
        this.img = img;
        this.isYes = isYes;
    }

    public Swiped() {

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBreedId() {
        return breedId;
    }

    public void setBreedId(int breedId) {
        this.breedId = breedId;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public boolean isYes() {
        return isYes;
    }

    public void setYes(boolean yes) {
        isYes = yes;
    }
}
