package com.chrismoran.petsittersapplication.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.chrismoran.petsittersapplication.models.Client;
import com.chrismoran.petsittersapplication.models.Pet;
import com.chrismoran.petsittersapplication.models.PetDetailsForm;
import com.chrismoran.petsittersapplication.services.ClientService;
import com.chrismoran.petsittersapplication.services.PetService;

import jakarta.servlet.http.HttpSession;

@Controller
public class PetController {

	@Autowired
	private PetService petService;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private HttpSession session;
	
	// Show number of pets selection form
	@GetMapping("/pets/numberSelection")
	public String showPetNumberForm(@RequestParam("clientId") Long clientId, Model model) {
	    // Define the options for the number of dogs and cats
	    List<Integer> dogOptions = new ArrayList<>();
	    List<Integer> catOptions = new ArrayList<>();

	    // Populate the lists with numbers (1-5)
	    for (int i = 0; i <= 5; i++) {
	        dogOptions.add(i);
	        catOptions.add(i);
	    }

	    model.addAttribute("dogOptions", dogOptions);
	    model.addAttribute("catOptions", catOptions);
	    model.addAttribute("clientId", clientId);

	    return "petNumberForm.jsp"; 
	}
	
	// Process the number of pets selection form
	@PostMapping("/pets/numberSelection")
	public String processPetNumberForm(
	        @RequestParam("numberOfDogs") Integer numberOfDogs,
	        @RequestParam("numberOfCats") Integer numberOfCats,
	        @RequestParam("clientId") Long clientId) {

	    // Store the number of dogs and cats in the session
	    session.setAttribute("numberOfDogs", numberOfDogs);
	    session.setAttribute("numberOfCats", numberOfCats);
	    session.setAttribute("clientId", clientId);

	    return "redirect:/pets/detailsForm";
	}
	
	// Show pet details form
	@GetMapping("/pets/detailsForm")
	public String showPetDetailsForm(Model model) {
	    // Retrieve the number of dogs, cats, and client ID from the session
	    Integer numberOfDogs = (Integer) session.getAttribute("numberOfDogs");
	    Integer numberOfCats = (Integer) session.getAttribute("numberOfCats");
	    Long clientId = (Long) session.getAttribute("clientId");

	    // Add these values to the model
	    model.addAttribute("numberOfDogs", numberOfDogs);
	    model.addAttribute("numberOfCats", numberOfCats);
	    model.addAttribute("clientId", clientId);

	    // Add an empty PetDetailsForm to bind the form data
	    model.addAttribute("petDetailsForm", new PetDetailsForm());

	    return "petDetailsForm.jsp"; 
	}
	
	@Transactional
	@PostMapping("/pets/detailsSubmission")
	public String processPetDetails(@ModelAttribute("petDetailsForm") PetDetailsForm form,
            @RequestParam("clientId") Long clientId) {
		Client client = clientService.getOneClient(clientId);
		if (client == null) {
		return "redirect:/clients";
		}
		
		List<Pet> newPets = new ArrayList<>();
		
		// Process dogs
		for (int i = 0; i < form.getDogNames().size(); i++) {
		Pet dog = new Pet();
		dog.setName(form.getDogNames().get(i));
		dog.setNotes(form.getDogNotes().get(i));
		dog.setPetType("dog");
		newPets.add(dog);
		}
		
		// Process cats
		for (int i = 0; i < form.getCatNames().size(); i++) {
		Pet cat = new Pet();
		cat.setName(form.getCatNames().get(i));
		cat.setNotes(form.getCatNotes().get(i));
		cat.setPetType("cat");
		newPets.add(cat);
		}
		
		petService.updateClientPets(client, newPets);
		
		return "redirect:/home";
		}

}












