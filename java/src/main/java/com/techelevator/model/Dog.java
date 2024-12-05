package com.techelevator.model;

public class Dog {
    // class variables
    private int dogId;
    private String dogName;
    private int breedId; //official breed
    private String img;
    private int age;
    private int size;
// empty constructor
    public Dog() {}

    // constructor
    public Dog(int dogId, String dogName, String breed, String img, int age, int size) {
        this.dogId = dogId;
        this.dogName = dogName;
        this.breedId = breedId;
        this.img = img;
        this.age = age;
        this.size = size;
    }
    //getter and setters
    public int getDogId() {
        return dogId;
    }

    public void setDogId(int dogId) {
        this.dogId = dogId;
    }

    public String getDogName() {
        return dogName;
    }

    public void setDogName(String dogName) {
        this.dogName = dogName;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}


