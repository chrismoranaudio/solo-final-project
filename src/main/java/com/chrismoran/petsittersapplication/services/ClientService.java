package com.chrismoran.petsittersapplication.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chrismoran.petsittersapplication.models.Client;
import com.chrismoran.petsittersapplication.models.Pet;
import com.chrismoran.petsittersapplication.models.Sit;
import com.chrismoran.petsittersapplication.models.User;
import com.chrismoran.petsittersapplication.repositories.ClientRepository;
import com.chrismoran.petsittersapplication.repositories.PetRepository;
import com.chrismoran.petsittersapplication.repositories.SitRepository;


@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepo;
	
	@Autowired
	private PetRepository petRepo;
	
	@Autowired
	private SitRepository sitRepo;
	
	@Autowired
	private UserService userService;
	
	// Get all clients
	public List<Client> getAllClientsForUser(Long userId) {
		List<Client> clients = clientRepo.findAllByUserId(userId);
		for(Client client : clients) {
			// Checking how many dogs and cats the client has
			int dogsCount = (int) client.getPets().stream().filter(p -> "dog".equals(p.getPetType())).count();
	        int catsCount = (int) client.getPets().stream().filter(p -> "cat".equals(p.getPetType())).count();
			// Making sure numberOfDogs and numberOfCats is always up-to-date
			client.setNumberOfDogs(dogsCount);
			client.setNumberOfCats(catsCount);
		}
		return clients;

	}
	
	// Create a new client
	public Client createClient(Client newClient, Long userId) {
		User user = userService.getOneUser(userId);
		newClient.setUser(user);
		return clientRepo.save(newClient);
	}
	
	// Find one client by id
	public Client getOneClient(Long clientId, Long userId) {
		Optional<Client> possibleClient = clientRepo.findByIdAndUserId(clientId, userId);
		return possibleClient.orElse(null);
	}
	
	// Update client details only (nothing pet related)
	public void updateClientDetails(Client existingClient, Client updatedClient) {
	    existingClient.setFirstName(updatedClient.getFirstName());
	    existingClient.setLastName(updatedClient.getLastName());
	    existingClient.setAddress(updatedClient.getAddress());
	    existingClient.setPhoneNumber(updatedClient.getPhoneNumber());
	    existingClient.setPriceQuoted(updatedClient.getPriceQuoted());
	    existingClient.setDailyVisits(updatedClient.getDailyVisits());
	    clientRepo.save(existingClient);
	}
	
	
	// Delete by id
	public void deleteClient(Long id, Long userId) {
		Client clientToDelete = this.getOneClient(id, userId);
		if(clientToDelete != null) {
			for(Pet petToDelete : clientToDelete.getPets()) {
				petRepo.delete(petToDelete);
			}
			for(Sit sitToDelete : clientToDelete.getSits()) {
				sitRepo.delete(sitToDelete);
			}
			clientRepo.deleteById(id);
		}
	}
	
	// Delete client object
	public void deleteClient(Client clientToDelete, Long userId) {
		if(clientToDelete.getUser().getId().equals(userId)) {
			for(Pet petToDelete : clientToDelete.getPets()) {
				petRepo.delete(petToDelete);
			}
			for(Sit sitToDelete : clientToDelete.getSits()) {
				sitRepo.delete(sitToDelete);
			}
			clientRepo.delete(clientToDelete);
		}
	}
		
}
