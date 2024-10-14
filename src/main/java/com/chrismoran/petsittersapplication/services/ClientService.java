package com.chrismoran.petsittersapplication.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chrismoran.petsittersapplication.models.Client;
import com.chrismoran.petsittersapplication.models.Pet;
import com.chrismoran.petsittersapplication.models.Sit;
import com.chrismoran.petsittersapplication.repositories.ClientRepository;
import com.chrismoran.petsittersapplication.repositories.PetRepository;
import com.chrismoran.petsittersapplication.repositories.SitRepository;

import jakarta.transaction.Transactional;

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
}
