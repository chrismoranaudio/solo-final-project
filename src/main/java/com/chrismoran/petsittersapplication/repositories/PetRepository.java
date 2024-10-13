package com.chrismoran.petsittersapplication.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.chrismoran.petsittersapplication.models.Client;
import com.chrismoran.petsittersapplication.models.Pet;

@Repository
public interface PetRepository extends CrudRepository<Pet, Long> {

	List<Pet> findAll();
	
	List<Pet> findByClient(Client client);

}
