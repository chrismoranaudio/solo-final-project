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
	        @PathVariable("clientId") Long clientId, 
	        HttpSession session, 
	        Model model) {

	    Client client = clientService.getOneClient(clientId);
	    if (client == null) {
	        return "redirect:/clients";  // Redirect if the client doesn't exist
	    }

	    // Retrieve the number of dogs and cats from the session
	    Integer numberOfDogs = (Integer) session.getAttribute("numberOfDogs");
	    Integer numberOfCats = (Integer) session.getAttribute("numberOfCats");

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
	        @Valid @ModelAttribute("client") Client client,
	        BindingResult result, Model model, HttpSession session) {

	    Long userId = (Long) session.getAttribute("userId");
	    if (userId == null) {
	        return "redirect:/";
	    }

	    // Retrieve the existing client from the database
	    Client existingClient = clientService.getOneClient(clientId);
	    if (existingClient == null) {
	        return "redirect:/home";
	    }

	    if (result.hasErrors()) {
	        model.addAttribute("client", existingClient); // Ensure client info is still passed back
	        return "newPets.jsp";
	    }

	    // Save each pet explicitly and associate it with the client
	    List<Pet> petsToSave = new ArrayList<>();
	    for (Pet pet : client.getPets()) {
	        if (pet.getName() != null && !pet.getName().isEmpty()) {
	            pet.setClient(existingClient);  // Link pet to the existing client
	            petsToSave.add(pet);
	        }
	    }

	    // Save the pets explicitly using PetService
	    petService.saveAllPets(petsToSave);

	    // Add all new pets to the existing client
	    existingClient.getPets().addAll(petsToSave);

	    // Save the client with the updated pets list
	    clientService.updateClient(existingClient);

	    return "redirect:/home";
	}
	
}












