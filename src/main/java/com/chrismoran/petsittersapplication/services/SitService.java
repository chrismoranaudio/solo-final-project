package com.chrismoran.petsittersapplication.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chrismoran.petsittersapplication.models.Sit;
import com.chrismoran.petsittersapplication.repositories.SitRepository;

@Service
public class SitService {

	@Autowired
	private SitRepository sitRepo;
	
	// Get all sits (sorted by Start Date)
	public List<Sit> getAllSits(Long userId) {
		return sitRepo.findAllByClient_UserIdOrderByStartDateAsc(userId);
	}
	
	// Create a new sit
	public Sit createSit(Sit newSit) {
		return sitRepo.save(newSit);
	}
	
	// Find sits by client
	public List<Sit> findSitsByClientIdAndUserId(Long clientId, Long userId) {
	    return sitRepo.findByClientIdAndClient_UserIdOrderByStartDateAsc(clientId, userId);
	}
	// Find one sit by id
	public Sit getOneSit(Long id, Long userId) {
		Optional<Sit> possibleSit = sitRepo.findByIdAndClient_UserId(id, userId);
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
