package com.chrismoran.petsittersapplication.dto;

public class PetUpdateDTO {

	private Long id;
	private String name;
	private String notes;
	private Boolean toBeRemoved;
	private PetType petType;
	
	public PetUpdateDTO() {}
	
	public enum PetType {
		DOG, CAT
	}

	// Getters & Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Boolean getToBeRemoved() {
		return toBeRemoved;
	}

	public void setToBeRemoved(Boolean toBeRemoved) {
		this.toBeRemoved = toBeRemoved;
	}

	public PetType getPetType() {
		return petType;
	}

	public void setPetType(PetType petType) {
		this.petType = petType;
	}
	
	
}
