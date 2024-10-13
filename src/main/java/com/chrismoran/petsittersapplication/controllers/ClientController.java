package com.chrismoran.petsittersapplication.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.chrismoran.petsittersapplication.models.Client;
import com.chrismoran.petsittersapplication.models.Pet;
import com.chrismoran.petsittersapplication.services.ClientService;
import com.chrismoran.petsittersapplication.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class ClientController {

	@Autowired
	private ClientService clientService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private HttpSession session;
	
	// Display the client list
	@GetMapping("/clients/all")
	public String clientList(Model model) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			return "redirect:/";
		}
		model.addAttribute("allClients", clientService.getAllClients());
		return "clientList.jsp";
	}
	
	// Show the new client form
	@GetMapping("/clients/new")
	public String newClientForm(@ModelAttribute("newClient") Client newClient) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			return "redirect:/";
		}
		return "newClient.jsp";
	}
	
	// Process the new client form
	@PostMapping("/clients/new")
	public String createClient(
			@Valid @ModelAttribute("newClient") Client newClient,
			BindingResult result, 
			@RequestParam Map<String, String> requestParams) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			return "redirect:/";
		}
		if(result.hasErrors()) {
			return "newClient.jsp";
		}
		// We need to get the pet information from the request params
		List<Pet> pets = new ArrayList<>();
		
		// Handling dogs
		int numberOfDogs = Integer.parseInt(requestParams.get("numberOfDogs"));
		for(int i = 1; i <= numberOfDogs; i++) {
			Pet dog = new Pet();
			dog.setName(requestParams.get("dog" + i + "Name"));
			dog.setNotes(requestParams.get("dog" + i + "Notes"));
			dog.setPetType(Pet.PetType.DOG);
			dog.setClient(newClient);
			pets.add(dog);
		}
		
		// Handling cats
		int numberOfCats = Integer.parseInt(requestParams.get("numberOfCats"));
		for(int i = 1; i <= numberOfCats; i++) {
			Pet cat = new Pet();
			cat.setName(requestParams.get("cat" + i + "Name"));
			cat.setNotes(requestParams.get("cat" + i + "Notes"));
			cat.setPetType(Pet.PetType.CAT);
			cat.setClient(newClient);
			pets.add(cat);
		}
		
		// Set the pets in the client object
		newClient.setPets(pets);
		
		// Save the new client
		clientService.createClient(newClient);
		return "redirect:/home";
	}
	
	// Show the edit client form
	@GetMapping("/clients/{id}/edit")
	public String editClientForm(
			@ModelAttribute("editClient")
			@PathVariable Long id, Model model) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			return "redirect:/";
		}
		Client thisClient = clientService.getOneClient(id);
		if(thisClient == null) {
			return "redirect:/home";
		}
		model.addAttribute("editClient", thisClient);
		return "editClient.jsp";
	}
	
	// Process the edit client form
	@PutMapping("/clients/{id}/update")
	public String updateClient(
			@PathVariable Long id,
			@Valid @ModelAttribute("editClient") Client editClient,
			BindingResult result) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			return "redirect:/";
		}
		if(result.hasErrors()) {
			return "editClient.jsp";
		}
		Client thisClient = clientService.getOneClient(id);
		if(thisClient == null) {
			return "redirect:/home";
		}
		clientService.updateClient(editClient);
		return "redirect:/home";
	}
	
	// Delete client
	@DeleteMapping("/clients/{id}/delete")
	public String deleteClient(@PathVariable Long id) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			return "redirect:/";
		}
		Client clientToDelete = clientService.getOneClient(id);
		if(clientToDelete == null) {
			return "redirect:/home";
		}
		clientService.deleteClient(id);
		return "redirect:/home";
	}
}
















