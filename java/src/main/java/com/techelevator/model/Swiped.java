package com.techelevator.model;

public class Swiped {
    private int userId;
    private int breedId;
    private String img;
    private boolean swipeYes;

    public Swiped(int userId, int breedId, String img, boolean swipeYes) {
        this.userId = userId;
        this.breedId = breedId;
        this.img = img;
        this.swipeYes = swipeYes;
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

//    public boolean isYes() {
//        return isYes;
//    }
//
//    public void setYes(boolean yes) {
//        isYes = yes;
//    }

    public boolean getSwipeYes(){
        return swipeYes;
    }
    public void setSwipeYes(boolean val){
        swipeYes =val;
    }
}
