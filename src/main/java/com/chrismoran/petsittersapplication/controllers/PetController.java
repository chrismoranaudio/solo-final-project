package com.chrismoran.petsittersapplication.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.chrismoran.petsittersapplication.models.Client;
import com.chrismoran.petsittersapplication.models.Pet;
import com.chrismoran.petsittersapplication.models.PetDetailsForm;
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
	
	// Show number of pets selection form
	@GetMapping("/pets/numberSelection")
	public String showPetNumberForm(@RequestParam("clientId") Long clientId, Model model) {
	    
	    List<Integer> dogOptions = new ArrayList<>();
	    List<Integer> catOptions = new ArrayList<>();

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
	public String processPetDetails(
			@RequestParam("clientId") Long clientId,
			@Valid @ModelAttribute("petDetailsForm") PetDetailsForm form,
			BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			model.addAttribute("numberOfDogs", form.getDogNames().size());
		    model.addAttribute("numberOfCats", form.getCatNames().size());
		    model.addAttribute("clientId", clientId);
		}
		
		Client client = clientService.getOneClient(clientId);
		if (client == null) {
			return "redirect:/clients/all";
		}
		
		petService.updateClientPets(clientId, form, true);
		
		return "redirect:/home";
	
	}
	
	// Show pet edit options form
	@GetMapping("/clients/{clientId}/pets/edit")
	public String showPetEditOptions(
			@PathVariable Long clientId, Model model) {
		
		model.addAttribute("clientId", clientId);
		
		return "petEditOptions.jsp";
	}
	
	// Show edit existing pet(s) form
	@GetMapping("/clients/{clientId}/pets/edit-existing")
	public String showEditExistingPetsForm(
			@PathVariable Long clientId, Model model) {
		
		Client client = clientService.getOneClient(clientId);
		if(client == null) {
			return "redirect:/clients/all";
		}
		
		PetDetailsForm form = new PetDetailsForm();
		for(Pet pet : client.getPets()) {
			form.getPetIds().add(pet.getId());
			form.getPetNames().add(pet.getName());
			form.getPetNotes().add(pet.getNotes());
			form.getPetTypes().add(pet.getPetType());
		}
		
		model.addAttribute("client", client);
		model.addAttribute("petDetailsForm", form);
		return "editExistingPets.jsp";
	}
	
	@Transactional
	@PutMapping("/clients/{clientId}/pets/update")
	public String updateExistingPets(
			@PathVariable Long clientId, 
			@Valid @ModelAttribute("petDetailsForm") PetDetailsForm form,
			BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			Client client = clientService.getOneClient(clientId);
			model.addAttribute("client", client);
			return "editExistingPets.jsp";
		}
		
		petService.updateExistingPets(clientId, form);
		
		return "redirect:/clients/all";
			
	}
	
	@GetMapping("/clients/{clientId}/pets/add")
	public String showAddPetForm(@PathVariable Long clientId, Model model) {
	    model.addAttribute("clientId", clientId);
	    List<Integer> petOptions = new ArrayList<>();

	    for (int i = 0; i <= 5; i++) {
	        petOptions.add(i);
	    }
	    model.addAttribute("petOptions", petOptions);
	    return "addPetForm.jsp";
	}

	@PostMapping("/clients/{clientId}/pets/add")
	public String processAddPetForm(@PathVariable Long clientId, 
			@RequestParam int numberOfDogs, 
			@RequestParam int numberOfCats, 
			Model model) {
	    model.addAttribute("clientId", clientId);
	    model.addAttribute("numberOfDogs", numberOfDogs);
	    model.addAttribute("numberOfCats", numberOfCats);
	    model.addAttribute("petDetailsForm", new PetDetailsForm());
	    return "newPetDetails.jsp";
	}
	
	@PostMapping("/clients/{clientId}/pets/add-details")
	public String addNewPets(
			@PathVariable Long clientId, 
			@Valid @ModelAttribute PetDetailsForm form,
			BindingResult result) {
		if(result.hasErrors()) {
			return "redirect:/clients/{clientId}/pets/add-details";
		}
		
	    petService.addNewPets(clientId, form);
	    return "redirect:/clients/all";
	}

}












