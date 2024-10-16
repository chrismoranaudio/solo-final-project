package com.chrismoran.petsittersapplication.models;

import java.util.ArrayList;
import java.util.List;

public class PetDetailsForm {

	private List<String> dogNames = new ArrayList<>();
    private List<String> dogNotes = new ArrayList<>();
    private List<String> catNames = new ArrayList<>();
    private List<String> catNotes = new ArrayList<>();

    // Constructor
    public PetDetailsForm() {}

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
