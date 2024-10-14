package com.chrismoran.petsittersapplication.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.chrismoran.petsittersapplication.models.Client;
import com.chrismoran.petsittersapplication.models.Pet;
import com.chrismoran.petsittersapplication.services.ClientService;
import com.chrismoran.petsittersapplication.services.PetService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class PetController {

	@Autowired
	private PetService petService;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private HttpSession session;
	
	// Show new pet form
	@GetMapping("/clients/{clientId}/pets/new")
	public String showNewPetForm(
	        @PathVariable("clientId") Long clientId, Model model) {

	    Client client = clientService.getOneClient(clientId);
	    if (client == null) {
	        return "redirect:/clients";  // Redirect if the client doesn't exist
	    }

	    // Retrieve the number of dogs and cats from the session
	    Integer numberOfDogs = (Integer) session.getAttribute("numberOfDogs");
	    Integer numberOfCats = (Integer) session.getAttribute("numberOfCats");

	    // Handle null values by defaulting to 0
	    if (numberOfDogs == null) {
	        numberOfDogs = 0;
	    }
	    if (numberOfCats == null) {
	        numberOfCats = 0;
	    }

	    // Ensure pets list is initialized
	    if (client.getPets() == null) {
	        client.setPets(new ArrayList<>());  // Initialize the pets list if null
	    }

	    // Prepare the Client's Pet list with empty Pet objects
	    for (int i = 0; i < numberOfDogs; i++) {
	        Pet dog = new Pet();
	        dog.setPetType(Pet.PetType.DOG);
	        client.getPets().add(dog);
	    }
	    for (int i = 0; i < numberOfCats; i++) {
	        Pet cat = new Pet();
	        cat.setPetType(Pet.PetType.CAT);
	        client.getPets().add(cat);
	    }

	    // Add client with pets to the model
	    model.addAttribute("client", client);

	    return "newPets.jsp";
	}
	
	// Process the new pet form
	@PostMapping("/clients/{clientId}/pets/new")
	public String createPets(
	        @PathVariable("clientId") Long clientId,
	        @ModelAttribute("client") Client clientForm,
	        BindingResult result, Model model) {

	    Long userId = (Long) session.getAttribute("userId");
	    if (userId == null) {
	        return "redirect:/";
	    }

	    Client existingClient = clientService.getOneClient(clientId);
	    if (existingClient == null) {
	        return "redirect:/home";
	    }

	    if (result.hasErrors()) {
	        model.addAttribute("client", existingClient);
	        return "newPets.jsp";
	    }

	    System.out.println("Number of pets in form: " + clientForm.getPets().size());
	    
	    List<Pet> savedPets = new ArrayList<>();
	    for (Pet pet : clientForm.getPets()) {
	        if (pet.getName() != null && !pet.getName().isEmpty()) {
	        	System.out.println("Saving pet: " + pet.getName() + ", Type: " + pet.getPetType());
	            pet.setClient(existingClient);
	            Pet savedPet = petService.savePet(pet);
	            savedPets.add(savedPet);
	            System.out.println("Saved pet with ID: " + savedPet.getId());
	        }
	    }

	    existingClient.setPets(savedPets);
	    Client updatedClient = clientService.updateClient(existingClient);
	    
	    System.out.println("Updated client. Number of pets: " + updatedClient.getPets().size());

	    return "redirect:/home";
	}

	
}












