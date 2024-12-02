package com.techelevator.model;

public class Breed {
    //class variables

    private int breedId;
    private String breedName;
    private String subBreed;
    private String OfficialName;

//empty constructor

    public Breed() {
    }
// constructor

    public Breed(int breedId, String breedName, String subBreed, String officialName) {
        this.breedId = breedId;
        this.breedName = breedName;
        this.subBreed = subBreed;
        this.OfficialName = officialName;


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

    public String getOfficialName() {
        return OfficialName;
    }

    public void setOfficialName(String officialName) {
        OfficialName = officialName;
    }
}
