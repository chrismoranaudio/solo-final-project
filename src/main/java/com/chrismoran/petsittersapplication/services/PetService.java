package com.chrismoran.petsittersapplication.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chrismoran.petsittersapplication.models.Client;
import com.chrismoran.petsittersapplication.models.Pet;
import com.chrismoran.petsittersapplication.models.PetDetailsForm;
import com.chrismoran.petsittersapplication.repositories.ClientRepository;
import com.chrismoran.petsittersapplication.repositories.PetRepository;

import jakarta.transaction.Transactional;

@Service
public class PetService {

	@Autowired
	private PetRepository petRepo;
	
	@Autowired
	private ClientRepository clientRepo;
	
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
	
	// Create a new pet
	public Pet createPet(Pet newPet) {
		return petRepo.save(newPet);
	}
	
	// Add pet to client
	@Transactional
	public void addPetToClient(Pet pet, Client client) {
	    pet.setClient(client);
	    petRepo.save(pet);
	    if (pet.getPetType().equals("dog")) {
	        client.setNumberOfDogs(client.getNumberOfDogs() + 1);
	    } else if (pet.getPetType().equals("cat")) {
	        client.setNumberOfCats(client.getNumberOfCats() + 1);
	    }
	    clientRepo.save(client);
	}

	// Remove a pet from a client
	@Transactional
	public void removePetFromClient(Pet pet, Client client) {
	    client.getPets().remove(pet);
	    if (pet.getPetType().equals("dog")) {
	        client.setNumberOfDogs(client.getNumberOfDogs() - 1);
	    } else if (pet.getPetType().equals("cat")) {
	        client.setNumberOfCats(client.getNumberOfCats() - 1);
	    }
	    petRepo.delete(pet);
	    clientRepo.save(client);
	}
	
	// Update a client's pets from a form
	@Transactional
	public void updateClientPets(Client client, PetDetailsForm form) {
	    List<Pet> updatedPets = new ArrayList<>();
	    
	    // Process dogs
	    for (int i = 0; i < form.getDogNames().size(); i++) {
	        String name = form.getDogNames().get(i);
	        String notes = form.getDogNotes().get(i);
	        if (name != null && !name.trim().isEmpty()) {
	            Pet dog = new Pet();
	            dog.setName(name);
	            dog.setNotes(notes);
	            dog.setPetType("dog");
	            dog.setClient(client);
	            updatedPets.add(dog);
	        }
	    }
	    
	    // Process cats
	    for (int i = 0; i < form.getCatNames().size(); i++) {
	        String name = form.getCatNames().get(i);
	        String notes = form.getCatNotes().get(i);
	        if (name != null && !name.trim().isEmpty()) {
	            Pet cat = new Pet();
	            cat.setName(name);
	            cat.setNotes(notes);
	            cat.setPetType("cat");
	            cat.setClient(client);
	            updatedPets.add(cat);
	        }
	    }
	    
	    // Remove all existing pets
	    petRepo.deleteAll(client.getPets());
	    
	    // Save all new and updated pets
	    petRepo.saveAll(updatedPets);
	    
	    // Update client's pet counts
	    client.setNumberOfDogs((int) updatedPets.stream().filter(p -> p.getPetType().equals("dog")).count());
	    client.setNumberOfCats((int) updatedPets.stream().filter(p -> p.getPetType().equals("cat")).count());
	    clientRepo.save(client);
	}
	
	// Update client with list of new pets
	public void updateClientPets(Client client, List<Pet> newPets) {
	    // Remove all existing pets
	    List<Pet> existingPets = petRepo.findByClient(client);
	    for (Pet pet : existingPets) {
	        removePetFromClient(pet, client);
	    }

	    // Add all new pets
	    for (Pet pet : newPets) {
	        addPetToClient(pet, client);
	    }
	}


	
	// Save all pets
	public List<Pet> saveAllPets(List<Pet> pets) {
		return (List<Pet>) petRepo.saveAll(pets);
	}
	
	// Update pet
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
