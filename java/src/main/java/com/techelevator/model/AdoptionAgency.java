package com.techelevator.model;

public class AdoptionAgency {

    // class vars
    private int agencyId;
    private String agencyName;
    private String agencyCity;
    private String agencyState;
    private String agencyLocation;

    // constructors
    public AdoptionAgency() {

    }
    public AdoptionAgency(int agencyId, String agencyName, String city, String state) {
        this.agencyId = agencyId;
        this.agencyName = agencyName;
        this.agencyCity = city;
        this.agencyState = state;
        this.agencyLocation = this.agencyCity + ", " + this.agencyState;
    }

    // getters and setters
    public int getAgencyId() {
        return agencyId;
    }
    public void setAgencyId(int agencyId) {
        this.agencyId = agencyId;
    }
    public String getAgencyName() {
        return agencyName;
    }
    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }
    public String getAgencyCity() {
        return agencyCity;
    }
    public void setAgencyCity(String agencyCity) {
        this.agencyCity = agencyCity;
    }
    public String getAgencyState() {
        return agencyState;
    }
    public void setAgencyState(String agencyState) {
        this.agencyState = agencyState;
    }
    public String getAgencyLocation() {
        return agencyLocation;
    }
    public void setAgencyLocation() {
        this.agencyLocation = this.agencyCity + ", " + this.agencyState;
    }
}
