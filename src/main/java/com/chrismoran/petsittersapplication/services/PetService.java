package com.chrismoran.petsittersapplication.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chrismoran.petsittersapplication.models.Client;
import com.chrismoran.petsittersapplication.models.Pet;
import com.chrismoran.petsittersapplication.repositories.PetRepository;

@Service
public class PetService {

	@Autowired
	private PetRepository petRepo;
	
	// Get all pets
	public List<Pet> getAllPets() {
		return petRepo.findAll();
	}
	
	// Find pets by client
	public List<Pet> findPetsByClient(Client client) {
		return petRepo.findByClient(client);
	}
	
	// Create a new pet
	public Pet createPet(Pet newPet) {
		return petRepo.save(newPet);
	}
	
	// Save all pets
	public List<Pet> saveAllPets(List<Pet> pets) {
		return (List<Pet>) petRepo.saveAll(pets);
	}
	
	// Edit pet
	public Pet updatePet(Pet petToEdit) {
		return petRepo.save(petToEdit);
	}
	
	// Delete by id
	public void deletePet(Long id) {
		petRepo.deleteById(id);
	}
	
	// Delete pet object
	public void deletePet(Pet petToDelete) {
		petRepo.delete(petToDelete);
	}
}
