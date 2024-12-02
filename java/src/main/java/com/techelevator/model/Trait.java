package com.techelevator.model;

public class Trait {
    // class variables
    private int traitId;
    private String traitName;

    // empty constructor
    public Trait() {
    }

    // constructor
    public Trait(int traitId, String traitName) {
        this.traitId = traitId;
        this.traitName = traitName;
    }
    //getter and setters
    public int getTraitId() {
        return traitId;
    }

    public void setTraitId(int traitId) {
        this.traitId = traitId;
    }

    public String getTraitName() {
        return traitName;
    }

    public void setTraitName(String traitName) {
        this.traitName = traitName;
    }
}
