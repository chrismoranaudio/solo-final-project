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
	
	// Master Updater
	@Transactional
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
	    client.setNumberOfDogs((int) updatedPets.stream().filter(p -> p.getPetType().equals("dog")).count());
	    client.setNumberOfCats((int) updatedPets.stream().filter(p -> p.getPetType().equals("cat")).count());
	    clientRepo.save(client);  // Ensure client’s pet counts are updated properly
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

	
	
	// Update existing pets
//	@Transactional
//	public void updateExistingPets(Long clientId, PetDetailsForm form) {
//	    Client client = clientService.getOneClient(clientId);
//	    List<Pet> updatedPets = new ArrayList<>();
//	    
//	    for (int i = 0; i < form.getPetIds().size(); i++) {
//	        Long petId = form.getPetIds().get(i);
//	        String name = form.getPetNames().get(i);
//	        String notes = form.getPetNotes().get(i);
//	        String type = form.getPetTypes().get(i);
//	        boolean toDelete = form.getPetsToDelete().contains(petId);
//	        
//	        if (!toDelete && name != null && !name.trim().isEmpty()) {
//	            Pet pet = petRepo.findById(petId).orElse(new Pet());
//                pet.setName(name);
//                pet.setNotes(notes);
//                pet.setPetType(type);
//                pet.setClient(client);
//                updatedPets.add(pet);
//	            
//	        }
//	    }
//	    
//	    client.setPets(updatedPets);
//	    client.setNumberOfDogs((int) updatedPets.stream().filter(p -> p.getPetType().equals("dog")).count());
//	    client.setNumberOfCats((int) updatedPets.stream().filter(p -> p.getPetType().equals("cat")).count());
//	    clientService.updateClient(client);
//	}
//
//	@Transactional
//	public void addNewPets(Long clientId, PetDetailsForm form) {
//	    Client client = clientService.getOneClient(clientId);
//	    List<Pet> newPets = new ArrayList<>();
//	    
//	    // Process dogs
//	    for (int i = 0; i < form.getDogNames().size(); i++) {
//	        String name = form.getDogNames().get(i);
//	        String notes = form.getDogNotes().get(i);
//	        if (name != null && !name.trim().isEmpty()) {
//	            Pet dog = new Pet();
//	            dog.setName(name);
//	            dog.setNotes(notes);
//	            dog.setPetType("dog");
//	            dog.setClient(client);
//	            newPets.add(dog);
//	        }
//	    }
//	    
//	    // Process cats
//	    for (int i = 0; i < form.getCatNames().size(); i++) {
//	        String name = form.getCatNames().get(i);
//	        String notes = form.getCatNotes().get(i);
//	        if (name != null && !name.trim().isEmpty()) {
//	            Pet cat = new Pet();
//	            cat.setName(name);
//	            cat.setNotes(notes);
//	            cat.setPetType("cat");
//	            cat.setClient(client);
//	            newPets.add(cat);
//	        }
//	    }
//	    
//	    client.getPets().addAll(newPets);
//	    client.setNumberOfDogs(client.getNumberOfDogs() + (int) newPets.stream().filter(p -> p.getPetType().equals("dog")).count());
//	    client.setNumberOfCats(client.getNumberOfCats() + (int) newPets.stream().filter(p -> p.getPetType().equals("cat")).count());
//	    clientService.updateClient(client);
//	}
//	
//	// Update client with list of new pets
//	@Transactional
//	public void updateClientPets(Client client, PetDetailsForm form) {
//	    
//		List<Pet> updatedPets = new ArrayList<>();
//		
//		// Process existing pets
//		for(int i = 0; i < form.getPetIds().size(); i++) {
//			Long petId = form.getPetIds().get(i);
//			String name = form.getPetNames().get(i);
//			String notes = form.getPetNotes().get(i);
//			String type = form.getPetTypes().get(i);
//			boolean toDelete = form.getPetsToDelete().contains(petId);
//			
//			if(!toDelete && name != null & !name.trim().isEmpty()) {
//				
//				Pet pet = petRepo.findById(petId).orElse(new Pet());
//				pet.setName(name);
//				pet.setNotes(notes);
//				pet.setPetType(type);
//				pet.setClient(client);
//				updatedPets.add(pet);
//			}
//		}
//		
//		// Process new dogs
//		for(int i = 0; i < form.getDogNames().size(); i++) {
//			String name = form.getDogNames().get(i);
//			String notes = form.getDogNotes().get(i);
//			if(name != null && !name.trim().isEmpty()) {
//				Pet dog = new Pet();
//				dog.setName(name);
//				dog.setNotes(notes);
//				dog.setPetType("dog");
//				dog.setClient(client);
//				updatedPets.add(dog);
//			}
//		}
//		
//		// Process new cats
//		for(int i = 0; i < form.getCatNames().size(); i++) {
//			String name = form.getCatNames().get(i);
//			String notes = form.getCatNotes().get(i);
//			if(name != null && !name.trim().isEmpty()) {
//				Pet cat = new Pet();
//				cat.setName(name);
//				cat.setNotes(notes);
//				cat.setPetType("cat");
//				cat.setClient(client);
//				updatedPets.add(cat);
//			}
//		}
//		
//		// Remove all existing pets and add updated pets
//        petRepo.deleteAll(client.getPets());
//        client.setPets(updatedPets);
//        petRepo.saveAll(updatedPets);
//        
//        // Update client's pet counts
//        client.setNumberOfDogs((int) updatedPets.stream().filter(p -> p.getPetType().equals("dog")).count());
//        client.setNumberOfCats((int) updatedPets.stream().filter(p -> p.getPetType().equals("cat")).count());
//        clientRepo.save(client);
//    }
	
	// Save all pets
//	public List<Pet> saveAllPets(List<Pet> pets) {
//		return (List<Pet>) petRepo.saveAll(pets);
//	}
//	
//	// Update pet
//	public Pet updatePet(Pet petToEdit) {
//		return petRepo.save(petToEdit);
//	}
//	
//	// Delete by id
//	public void deletePet(Long id) {
//		petRepo.deleteById(id);
//	}
//	
//	// Delete pet object
//	public void deletePet(Pet petToDelete) {
//		petRepo.delete(petToDelete);
//	}
}
