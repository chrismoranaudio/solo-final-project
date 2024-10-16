package com.chrismoran.petsittersapplication.models;

import java.util.List;

public class PetDetailsForm {

	private List<String> dogNames;
    private List<String> dogNotes;
    private List<String> catNames;
    private List<String> catNotes;

    // Constructor
    public PetDetailsForm() {}
    
    // Getters and setters
    public List<String> getDogNames() {
        return dogNames;
    }

    public void setDogNames(List<String> dogNames) {
        this.dogNames = dogNames;
    }

    public List<String> getDogNotes() {
        return dogNotes;
    }

    public void setDogNotes(List<String> dogNotes) {
        this.dogNotes = dogNotes;
    }

    public List<String> getCatNames() {
        return catNames;
    }

    public void setCatNames(List<String> catNames) {
        this.catNames = catNames;
    }

    public List<String> getCatNotes() {
        return catNotes;
    }

    public void setCatNotes(List<String> catNotes) {
        this.catNotes = catNotes;
    }
}
