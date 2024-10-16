package com.chrismoran.petsittersapplication.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chrismoran.petsittersapplication.models.Client;
import com.chrismoran.petsittersapplication.models.Pet;
import com.chrismoran.petsittersapplication.models.Sit;
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
	private PetService petService;
	
	@Autowired
	private SitRepository sitRepo;
	
	// Get all clients
	public List<Client> getAllClients() {
		List<Client> clients = clientRepo.findAll();
		for(Client client : clients) {
			if(client.getNumberOfCats() == null) {
				client.setNumberOfCats(0);
			}
			if(client.getNumberOfDogs() == null) {
				client.setNumberOfDogs(0);
			}
		}
		return clients;
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
	
	// Method to update client pet count
	@Transactional
	public void updateClientPetCounts(Client client) {
		int dogCount = (int) client.getPets().stream().filter(p -> p.getPetType().equals("dog")).count();
		int catCount = (int) client.getPets().stream().filter(p -> p.getPetType().equals("cat")).count();
		
		client.setNumberOfDogs(dogCount);
		client.setNumberOfCats(catCount);
		clientRepo.save(client);
		
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
	
	
}















