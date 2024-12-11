package com.techelevator.model;

public class PreviewBreed {

    private int previewBreedId;
    private int breedId;

    public PreviewBreed() {

    }
    public PreviewBreed(int previewBreedId, int breedId) {
        this.previewBreedId = previewBreedId;
        this.breedId = breedId;
    }

    public int getPreviewBreedId() {
        return previewBreedId;
    }

    public void setPreviewBreedId(int previewBreedId) {
        this.previewBreedId = previewBreedId;
    }

    public int getBreedId() {
        return breedId;
    }

    public void setBreedId(int breedId) {
        this.breedId = breedId;
    }
}
