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
	
	// Add pet to client
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
	
	// Update existing or add new pets
	public void updateClientPets(Long clientId, PetDetailsForm form, boolean includeNewPets) {
	    Client client = clientService.getOneClient(clientId);
	    if (client == null) {
	        throw new RuntimeException("Client not found");
	    }

	    List<Pet> updatedPets = new ArrayList<>();

	    // Process existing pets
	    for (int i = 0; i < form.getPetIds().size(); i++) {
	        Long petId = form.getPetIds().get(i);
	        String name = form.getPetNames().get(i);
	        String notes = form.getPetNotes().get(i);
	        String type = form.getPetTypes().get(i);
	        boolean toDelete = form.getPetsToDelete().contains(petId);

	        if (!toDelete && name != null && !name.trim().isEmpty()) {
	            // Update existing pet
	            Pet pet = petRepo.findById(petId).orElseThrow(() -> new RuntimeException("Pet not found"));
	            pet.setName(name);
	            pet.setNotes(notes);
	            pet.setPetType(type);
	            pet.setClient(client);
	            updatedPets.add(pet);  // Add pet to the updated list
	        } else if (toDelete) {
	            // Remove the pet if marked for deletion
	            Pet pet = petRepo.findById(petId).orElse(null);
	            if (pet != null) {
	            	updatedPets.remove(pet); // Remove pet from the updated list
	                petRepo.delete(pet);
	            }
	        }
	    }

	    // Process new pets if necessary
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
	                updatedPets.add(dog);  // Add the new dog to the list
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
	                updatedPets.add(cat);  // Add the new cat to the list
	            }
	        }
	    }

	    // Ensure updated pets are saved and client is updated
	    client.setPets(updatedPets);
	    petRepo.saveAll(updatedPets);  // Save all pets including new and updated pets

	    // Update client’s pet counts
	    updateClientPetCounts(client);
	    
	    // Save the client with all updates
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
	
	// Sending to master updater
	public void updateExistingPets(Long clientId, PetDetailsForm form) {
	    updateClientPets(clientId, form, false);
	}

	// Sending to master updater
	public void updateClientPets(Client client, PetDetailsForm form) {
	    updateClientPets(client.getId(), form, true);
	}

	// Add new pets
	public void addNewPets(Long clientId, PetDetailsForm form) {
	    PetDetailsForm newForm = new PetDetailsForm();
	    newForm.setDogNames(form.getDogNames());
	    newForm.setDogNotes(form.getDogNotes());
	    newForm.setCatNames(form.getCatNames());
	    newForm.setCatNotes(form.getCatNotes());
	    updateClientPets(clientId, newForm, true);
	}
}
