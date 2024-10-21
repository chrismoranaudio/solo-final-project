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


@Service
public class PetService {

	@Autowired
	private PetRepository petRepo;
	
	@Autowired
	private ClientRepository clientRepo;
	
	@Autowired
	private ClientService clientService;

	
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
	
	// Update a client's existing, or add new pets
	public void updateClientPets(Long clientId, PetDetailsForm form, boolean includeNewPets) {
	    Client client = clientService.getOneClient(clientId);
	    if (client == null) {
	        throw new RuntimeException("Client not found");
	    }

	    // Retrieve current pets of the client
	    List<Pet> currentPets = client.getPets();
	    // Create a new list, starting with the client's current pets
	    List<Pet> updatedPets = new ArrayList<>(currentPets);

	    // Process existing pets (those submitted in the form)
	    for (int i = 0; i < form.getPetIds().size(); i++) {
	        Long petId = form.getPetIds().get(i);
	        String name = form.getPetNames().get(i);
	        String notes = form.getPetNotes().get(i);
	        String type = form.getPetTypes().get(i);
	        boolean toDelete = form.getPetsToDelete().contains(petId);

	        Pet pet = petRepo.findById(petId).orElseThrow(() -> new RuntimeException("Pet not found"));

	        if (!toDelete && name != null && !name.trim().isEmpty()) {
	            // Update existing pet
	            pet.setName(name);
	            pet.setNotes(notes);
	            pet.setPetType(type);
	            pet.setClient(client); 
	        } else if (toDelete) {
	            // Remove the pet if marked for deletion
	            petRepo.delete(pet);
	            updatedPets.remove(pet);
	        }
	    }

	    // Process new pets, if necessary (boolean "true" is passed)
	    if (includeNewPets) {
	        // Process new dogs
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

	        // Process new cats
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
	    }

	    client.setPets(updatedPets);
	    petRepo.saveAll(updatedPets);  

	    updateClientPetCounts(client); 
	    clientRepo.save(client); 
	}

	// Update client pet count
	public void updateClientPetCounts(Client client) {
	    int dogCount = (int) client.getPets().stream().filter(p -> "dog".equals(p.getPetType())).count();
	    int catCount = (int) client.getPets().stream().filter(p -> "cat".equals(p.getPetType())).count();
	    
	    client.setNumberOfDogs(dogCount);
	    client.setNumberOfCats(catCount);
	    clientRepo.save(client);
	}
	
	// Sending to master updater (no new pets)
	public void updateExistingPets(Long clientId, PetDetailsForm form) {
	    updateClientPets(clientId, form, false);
	}

	// Sending to master updater (include new pets)
	public void updateClientPets(Client client, PetDetailsForm form) {
	    updateClientPets(client.getId(), form, true);
	}

	// Add new pets
	public void addNewPets(Long clientId, PetDetailsForm form) {
		updateClientPets(clientId, form, true);
	}
}
