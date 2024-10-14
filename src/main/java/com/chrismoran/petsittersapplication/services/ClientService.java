package com.chrismoran.petsittersapplication.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chrismoran.petsittersapplication.dto.ClientUpdateDTO;
import com.chrismoran.petsittersapplication.dto.PetUpdateDTO;
import com.chrismoran.petsittersapplication.models.Client;
import com.chrismoran.petsittersapplication.models.Pet;
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















