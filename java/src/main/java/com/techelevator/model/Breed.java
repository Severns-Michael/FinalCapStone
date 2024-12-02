package com.techelevator.model;

public class Breed {
    //class variables

    private int breedId;
    private String breedName;
    private String subBreed;
//empty constructor

    public Breed() {
    }
// constructor

    public Breed(int breedId, String breedName) {
        this.breedId = breedId;
        this.breedName = breedName;
        this.subBreed = null;
    }

// getter and setters

    public int getBreedId() {
        return breedId;
    }

    public void setBreedId(int breedId) {
        this.breedId = breedId;
    }

    public String getBreedName() {
        return breedName;
    }

    public void setBreedName(String breedName) {
        this.breedName = breedName;
    }

    public String getSubBreed() {
        return subBreed;
    }

    public void setSubBreed(String subBreed) {
        this.subBreed = subBreed;
    }
}
