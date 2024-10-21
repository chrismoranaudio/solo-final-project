package com.chrismoran.petsittersapplication.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.chrismoran.petsittersapplication.models.Client;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {

	List<Client> findAllByUserId(Long userId);
	
	Optional<Client> findByIdAndUserId(Long id, Long userId);
}
