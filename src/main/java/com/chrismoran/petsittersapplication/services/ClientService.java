package com.chrismoran.petsittersapplication.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chrismoran.petsittersapplication.dto.ClientUpdateDTO;
import com.chrismoran.petsittersapplication.dto.PetUpdateDTO;
import com.chrismoran.petsittersapplication.models.Client;
import com.chrismoran.petsittersapplication.models.Pet;
import com.chrismoran.petsittersapplication.models.PetType;
import com.chrismoran.petsittersapplication.models.Sit;
import com.chrismoran.petsittersapplication.repositories.ClientRepository;
import com.chrismoran.petsittersapplication.repositories.PetRepository;
import com.chrismoran.petsittersapplication.repositories.SitRepository;


@Service
@Transactional
public class ClientService {

	@Autowired
	private ClientRepository clientRepo;
	
	@Autowired
	private PetRepository petRepo;
	
	@Autowired
	private PetService petService;
	
	@Autowired
	private SitRepository sitRepo;
	
	// Get all clients
	@Transactional(readOnly = true)
	public List<Client> getAllClients() {
		return clientRepo.findAll();
	}
	
	// Create a new client
	public Client createClient(Client newClient) {
		return clientRepo.save(newClient);
	}
	
	// Find one client by id
	public Client getOneClient(Long id) {
		Optional<Client> possibleClient = clientRepo.findById(id);
		return possibleClient.orElse(null);
	}
	
	// Edit client
	public Client updateClient(Client clientToEdit) {
		return clientRepo.save(clientToEdit);
	}
	
	// Update client from DTO
	@Transactional
	public Client updateClientFromDTO(Long id, ClientUpdateDTO clientUpdateDTO) {
	    Client client = getOneClient(id);
	    if (client == null) {
	        return null;
	    }
	    
	    // Update client fields
	    client.setFirstName(clientUpdateDTO.getFirstName());
	    client.setLastName(clientUpdateDTO.getLastName());
	    client.setAddress(clientUpdateDTO.getAddress());
	    client.setPhoneNumber(clientUpdateDTO.getPhoneNumber());
	    client.setPriceQuoted(clientUpdateDTO.getPriceQuoted());
	    client.setDailyVisits(clientUpdateDTO.getDailyVisits());
	    
	    // Handle existing pets
	    Set<Long> updatedPetIds = new HashSet<>();
	    List<Pet> updatedPets = new ArrayList<>();
	    
	    for (PetUpdateDTO petDTO : clientUpdateDTO.getExistingPets()) {
	        Pet pet;
	        if (petDTO.getId() != null) {
	            // Existing pet - update
	            pet = petService.getOnePet(petDTO.getId());
	            if (pet == null || !pet.getClient().getId().equals(client.getId())) {
	                // Pet doesn't exist or doesn't belong to this client - skip
	                continue;
	            }
	        } else {
	            // New pet - create
	            pet = new Pet();
	            pet.setClient(client);
	        }
	        
	        // Update pet fields
	        pet.setName(petDTO.getName());
	        pet.setNotes(petDTO.getNotes());
	        pet.setPetType(petDTO.getPetType());
	        
	        if (!petDTO.isToBeRemoved()) {
	            updatedPets.add(pet);
	            if (pet.getId() != null) {
	                updatedPetIds.add(pet.getId());
	            }
	        }
	    }
	    
	    // Remove pets that are no longer in the list
	    client.getPets().removeIf(pet -> !updatedPetIds.contains(pet.getId()));
	    
	    // Add all updated and new pets
	    client.getPets().addAll(updatedPets);
	    
	    // Handle new dogs
	    if (clientUpdateDTO.getNewDogs() != null) {
	        for (PetUpdateDTO newDog : clientUpdateDTO.getNewDogs()) {
	            Pet dog = new Pet();
	            dog.setName(newDog.getName());
	            dog.setNotes(newDog.getNotes());
	            dog.setPetType(PetType.DOG);
	            dog.setClient(client);
	            client.getPets().add(dog);
	        }
	    }
	    
	    // Handle new cats
	    if (clientUpdateDTO.getNewCats() != null) {
	        for (PetUpdateDTO newCat : clientUpdateDTO.getNewCats()) {
	            Pet cat = new Pet();
	            cat.setName(newCat.getName());
	            cat.setNotes(newCat.getNotes());
	            cat.setPetType(PetType.CAT);
	            cat.setClient(client);
	            client.getPets().add(cat);
	        }
	    }
	    
	    // Update pet counts
	    client.setNumberOfDogs((int) client.getPets().stream().filter(p -> p.getPetType() == PetType.DOG).count());
	    client.setNumberOfCats((int) client.getPets().stream().filter(p -> p.getPetType() == PetType.CAT).count());
	    
	    return clientRepo.save(client);
	}

	
	// Delete by id
	public void deleteClient(Long id) {
		Client clientToDelete = this.getOneClient(id);
		for(Pet petToDelete : clientToDelete.getPets()) {
			petRepo.delete(petToDelete);
		}
		for(Sit sitToDelete : clientToDelete.getSits()) {
			sitRepo.delete(sitToDelete);
		}
		clientRepo.deleteById(id);
	}
	
	// Delete client object
	public void deleteClient(Client clientToDelete) {
		for(Pet petToDelete : clientToDelete.getPets()) {
			petRepo.delete(petToDelete);
		}
		for(Sit sitToDelete : clientToDelete.getSits()) {
			sitRepo.delete(sitToDelete);
		}
		clientRepo.delete(clientToDelete);
	}
	
	// Client Update Conversion
	public ClientUpdateDTO convertToClientUpdateDTO(Client client) {
		ClientUpdateDTO dto = new ClientUpdateDTO();
		
		// Set the basic client information
		dto.setId(client.getId());
		dto.setFirstName(client.getFirstName());
		dto.setLastName(client.getLastName());
		dto.setAddress(client.getAddress());
		dto.setPhoneNumber(client.getPhoneNumber());
		dto.setPriceQuoted(client.getPriceQuoted());
		dto.setDailyVisits(client.getDailyVisits());
		dto.setNumberOfDogs(client.getNumberOfDogs());
		dto.setNumberOfCats(client.getNumberOfCats());
		
		// Convert and set existing pets
		List<PetUpdateDTO> existingPets = client.getPets().stream()
				.map(this::convertToPetUpdateDTO)
				.collect(Collectors.toList());
		dto.setExistingPets(existingPets);
		
		return dto;
	}
	
	private PetUpdateDTO convertToPetUpdateDTO(Pet pet) {
		PetUpdateDTO dto = new PetUpdateDTO();
		dto.setId(pet.getId());
		dto.setName(pet.getName());
		dto.setNotes(pet.getNotes());
		dto.setPetType(pet.getPetType());
		dto.setToBeRemoved(false); // Initially set to false
		
		return dto;
	}
}















