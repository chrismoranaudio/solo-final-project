package com.chrismoran.petsittersapplication.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.chrismoran.petsittersapplication.models.Sit;

@Repository
public interface SitRepository extends CrudRepository<Sit, Long> {

	List<Sit> findAll();
		
	List<Sit> findByClientIdAndClient_UserIdOrderByStartDateAsc(Long clientId, Long userId);
	
	List<Sit> findAllByClient_UserIdOrderByStartDateAsc(Long userId);
	
	Optional<Sit> findByIdAndClient_UserId(Long id, Long userId);
}
