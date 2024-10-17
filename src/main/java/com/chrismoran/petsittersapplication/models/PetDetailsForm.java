package com.chrismoran.petsittersapplication.models;

import java.util.ArrayList;
import java.util.List;

public class PetDetailsForm {

	// For adding new pets
	private List<String> dogNames = new ArrayList<>();
    private List<String> dogNotes = new ArrayList<>();
    private List<String> catNames = new ArrayList<>();
    private List<String> catNotes = new ArrayList<>();
    
    // For editing existing pets
    private List<Long> petIds = new ArrayList<>();
    private List<String> petNames = new ArrayList<>();
    private List<String> petNotes = new ArrayList<>();
    private List<String> petTypes = new ArrayList<>();
    private List<Long> petsToDelete = new ArrayList<>();

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

	public List<Long> getPetIds() {
		return petIds;
	}

	public void setPetIds(List<Long> petIds) {
		this.petIds = petIds;
	}

	public List<String> getPetNames() {
		return petNames;
	}

	public void setPetNames(List<String> petNames) {
		this.petNames = petNames;
	}

	public List<String> getPetNotes() {
		return petNotes;
	}

	public void setPetNotes(List<String> petNotes) {
		this.petNotes = petNotes;
	}

	public List<String> getPetTypes() {
		return petTypes;
	}

	public void setPetTypes(List<String> petTypes) {
		this.petTypes = petTypes;
	}

	public List<Long> getPetsToDelete() {
		return petsToDelete;
	}

	public void setPetsToDelete(List<Long> petsToDelete) {
		this.petsToDelete = petsToDelete;
	}

    

    
}
