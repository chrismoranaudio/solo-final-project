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
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			return "redirect:/";
		}
	    
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
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			return "redirect:/";
		}
		
	    // Store the number of dogs and cats, plus client id in session
	    session.setAttribute("numberOfDogs", numberOfDogs);
	    session.setAttribute("numberOfCats", numberOfCats);
	    session.setAttribute("clientId", clientId);

	    return "redirect:/pets/detailsForm";
	}
	
	// Show pet details form
	@GetMapping("/pets/detailsForm")
	public String showPetDetailsForm(Model model) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			return "redirect:/";
		}
		
		// Retrieve number of dogs and cats to populate form
	    Integer numberOfDogs = (Integer) session.getAttribute("numberOfDogs");
	    Integer numberOfCats = (Integer) session.getAttribute("numberOfCats");
	    Long clientId = (Long) session.getAttribute("clientId");

	    model.addAttribute("numberOfDogs", numberOfDogs);
	    model.addAttribute("numberOfCats", numberOfCats);
	    model.addAttribute("clientId", clientId);

	    // Send the Pet Details Form to the view
	    model.addAttribute("petDetailsForm", new PetDetailsForm());

	    return "petDetailsForm.jsp"; 
	}
	
	@PostMapping("/pets/detailsSubmission")
	public String processPetDetails(
			@RequestParam("clientId") Long clientId,
			@Valid @ModelAttribute("petDetailsForm") PetDetailsForm form,
			BindingResult result, Model model) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			return "redirect:/";
		}
		
		if(result.hasErrors()) {
			model.addAttribute("numberOfDogs", form.getDogNames().size());
		    model.addAttribute("numberOfCats", form.getCatNames().size());
		    model.addAttribute("clientId", clientId);
		    return "petDetailsForm.jsp";
		}
		
		Client client = clientService.getOneClient(clientId);
		if (client == null) {
			return "redirect:/clients/all";
		}
		
		// Sending form information to update (add) the client's pets
		petService.updateClientPets(clientId, form, true);
		
		return "redirect:/clients/all";
	
	}
	
	// Show pet edit options form
	@GetMapping("/clients/{clientId}/pets/edit")
	public String showPetEditOptions(
			@PathVariable Long clientId, Model model) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			return "redirect:/";
		}
		
		model.addAttribute("clientId", clientId);
		
		return "petEditOptions.jsp";
	}
	
	// Show edit existing pet(s) form
	@GetMapping("/clients/{clientId}/pets/edit-existing")
	public String showEditExistingPetsForm(
			@PathVariable Long clientId, Model model) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			return "redirect:/";
		}
		
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
	
	// Update existing pets
	@PutMapping("/clients/{clientId}/pets/update")
	public String updateExistingPets(
			@PathVariable Long clientId, 
			@Valid @ModelAttribute("petDetailsForm") PetDetailsForm form,
			BindingResult result, Model model) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			return "redirect:/";
		}
		
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
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			return "redirect:/";
		}
		
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
			@RequestParam Integer numberOfDogs, 
			@RequestParam Integer numberOfCats, 
			Model model) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			return "redirect:/";
		}
	    model.addAttribute("clientId", clientId);
	    model.addAttribute("numberOfDogs", numberOfDogs);
	    model.addAttribute("numberOfCats", numberOfCats);
	    model.addAttribute("petDetailsForm", new PetDetailsForm());
	    
	    // Sends the new numberOfDogs and numberOfCats to the "Add Details" form
	    return "redirect:/clients/"+clientId+"/pets/add-details?numberOfDogs="+numberOfDogs+"&numberOfCats="+numberOfCats;
	}
	
	@GetMapping("/clients/{clientId}/pets/add-details")
	public String addNewPetDetailsForm(@PathVariable Long clientId,
			@RequestParam("numberOfDogs") Integer numberOfDogs,
			@RequestParam("numberOfCats") Integer numberOfCats,
			Model model) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			return "redirect:/";
		}
		Client client = clientService.getOneClient(clientId);
		if(client == null) {
			return "redirect:/clients/all";
		}
		
		model.addAttribute("clientId", clientId);
		model.addAttribute("numberOfDogs", numberOfDogs);
		model.addAttribute("numberOfCats", numberOfCats);
		model.addAttribute("petDetailsForm", new PetDetailsForm());
		
		return "newPetDetails.jsp";
	}
	
	@PostMapping("/clients/{clientId}/pets/add-details")
	public String addNewPets(
			@PathVariable Long clientId, 
			@Valid @ModelAttribute("petDetailsForm") PetDetailsForm form,
			BindingResult result,
			@RequestParam("numberOfDogs") Integer numberOfDogs,
			@RequestParam("numberOfCats") Integer numberOfCats,
			Model model) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			return "redirect:/";
		}
		if(result.hasErrors()) {
			model.addAttribute("numberOfDogs", numberOfDogs);
			model.addAttribute("numberOfCats", numberOfCats);
			model.addAttribute("clientId", clientId);
			return "newPetDetails.jsp";
		}
		
	    petService.addNewPets(clientId, form);
	    return "redirect:/clients/all";
	}

}