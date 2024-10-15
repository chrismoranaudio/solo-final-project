package com.chrismoran.petsittersapplication.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chrismoran.petsittersapplication.models.Client;
import com.chrismoran.petsittersapplication.models.Pet;
import com.chrismoran.petsittersapplication.repositories.PetRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PetService {

	@Autowired
	private PetRepository petRepo;
	
	// Get all pets
	public List<Pet> getAllPets() {
		return petRepo.findAll();
	}
	
	// Get one pet by id
	public Pet getOnePet(Long id) {
		Optional<Pet> possiblePet = petRepo.findById(id);
		return possiblePet.orElse(null);
	}
	
	// Find pets by client
	public List<Pet> findPetsByClient(Client client) {
		return petRepo.findByClient(client);
	}
	
	// Save a new pet
	public Pet savePet(Pet newPet) {
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
