package com.chrismoran.petsittersapplication.dto;

import java.util.List;

public class ClientUpdateDTO {
	
	private Long id;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String address;
	private Double priceQuoted;
	private Integer dailyVisits;
	private Integer numberOfDogs;
	private Integer numberOfCats;
	private List<PetUpdateDTO> existingPets;
	private List<PetUpdateDTO> newDogs;
	private List<PetUpdateDTO> newCats;
	
	public ClientUpdateDTO() {}

	public Long getId() {
		return id;
	}

	// Getters & Setters
	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getPriceQuoted() {
		return priceQuoted;
	}

	public void setPriceQuoted(Double priceQuoted) {
		this.priceQuoted = priceQuoted;
	}

	public Integer getDailyVisits() {
		return dailyVisits;
	}

	public void setDailyVisits(Integer dailyVisits) {
		this.dailyVisits = dailyVisits;
	}

	public Integer getNumberOfDogs() {
		return numberOfDogs;
	}

	public void setNumberOfDogs(Integer numberOfDogs) {
		this.numberOfDogs = numberOfDogs;
	}

	public Integer getNumberOfCats() {
		return numberOfCats;
	}

	public void setNumberOfCats(Integer numberOfCats) {
		this.numberOfCats = numberOfCats;
	}

	public List<PetUpdateDTO> getExistingPets() {
		return existingPets;
	}

	public void setExistingPets(List<PetUpdateDTO> existingPets) {
		this.existingPets = existingPets;
	}

	public List<PetUpdateDTO> getNewDogs() {
		return newDogs;
	}

	public void setNewDogs(List<PetUpdateDTO> newDogs) {
		this.newDogs = newDogs;
	}

	public List<PetUpdateDTO> getNewCats() {
		return newCats;
	}

	public void setNewCats(List<PetUpdateDTO> newCats) {
		this.newCats = newCats;
	}

	
	
	
}
