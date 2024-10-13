package com.chrismoran.petsittersapplication.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.chrismoran.petsittersapplication.models.Client;
import com.chrismoran.petsittersapplication.models.Sit;

@Repository
public interface SitRepository extends CrudRepository<Sit, Long> {

	List<Sit> findAll();
	
	List<Sit> findByClient(Client client);
}
