package com.chrismoran.petsittersapplication.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chrismoran.petsittersapplication.models.Client;
import com.chrismoran.petsittersapplication.models.Sit;
import com.chrismoran.petsittersapplication.repositories.SitRepository;

@Service
public class SitService {

	@Autowired
	private SitRepository sitRepo;
	
	// Get all sits (sorted by Start Date)
	public List<Sit> getAllSits() {
		return sitRepo.findAllByOrderByStartDateAsc();
	}
	
	// Create a new sit
	public Sit createSit(Sit newSit) {
		return sitRepo.save(newSit);
	}
	
	// Find sits by client
	public List<Sit> findSitsByClient(Client client) {
		return sitRepo.findByClient(client);
	}
	
	// Find one sit by id
	public Sit getOneSit(Long id) {
		Optional<Sit> possibleSit = sitRepo.findById(id);
		return possibleSit.orElse(null);
	}
	
	// Edit sit
	public Sit updateSit(Sit sitToEdit) {
		return sitRepo.save(sitToEdit);
	}
	
	// Delete by id
	public void deleteSit(Long id) {
		sitRepo.deleteById(id);
	}
	
	// Delete sit object
	public void deleteSit(Sit sitToDelete) {
		sitRepo.delete(sitToDelete);
	}
	
}
