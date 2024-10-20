package com.chrismoran.petsittersapplication.controllers;

import java.time.format.DateTimeFormatter;
import java.util.List;

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

import com.chrismoran.petsittersapplication.models.Client;
import com.chrismoran.petsittersapplication.models.Sit;
import com.chrismoran.petsittersapplication.services.ClientService;
import com.chrismoran.petsittersapplication.services.SitService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class ClientController {

	@Autowired
	private ClientService clientService;
	
	@Autowired
	private SitService sitService;
	
	@Autowired
	private HttpSession session;
	
	// Display the client list
	@GetMapping("/clients/all")
	public String showAllClients(Model model) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			return "redirect:/";
		}
		model.addAttribute("clients", clientService.getAllClients());
		return "allClients.jsp";
	}
	
	// Display one client
	@GetMapping("/clients/{clientId}/view")
	public String viewClient(@PathVariable Long clientId, Model model) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			return "redirect:/";
		}
		
		Client client = clientService.getOneClient(clientId);
		if(client == null) {
			return "redirect:/clients/all";
		}
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd");
		model.addAttribute("dateFormatter", formatter);
		
		String formattedPhoneNumber = formatPhoneNumber(client.getPhoneNumber());
		model.addAttribute("formattedPhoneNumber", formattedPhoneNumber);
		
		List<Sit> currentSits = sitService.findSitsByClient(client);
		model.addAttribute("currentSits", currentSits);
		model.addAttribute("client", client);
		return "viewClient.jsp";
	}
	
	private String formatPhoneNumber(String phoneNumber) {
		String cleaned = phoneNumber.replaceAll("[^\\d]", "");
		return cleaned.replaceFirst("(\\d{3})(\\d{3})(\\d{4})", "($1) $2-$3");
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
	        BindingResult result) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			return "redirect:/";
		}
	    if (result.hasErrors()) {
	        return "newClient.jsp";
	    }

	    Client savedClient = clientService.createClient(newClient);

	    // Redirect to the pet form
	    return "redirect:/pets/numberSelection?clientId="+savedClient.getId();
	}

	
	// Show the edit client form
	@GetMapping("/clients/{id}/edit")
	public String showEditClientForm(@PathVariable("id") Long id, Model model) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			return "redirect:/";
		}
		
		Client client = clientService.getOneClient(id);
	    if (client == null) {
	        return "redirect:/clients/all";
	    }
	    model.addAttribute("client", client);
	    return "editClient.jsp";
	}

	@PutMapping("/clients/{id}/edit")
	public String processEditClientForm(
			@PathVariable("id") Long id, 
			@Valid @ModelAttribute("client") Client updatedClient, 
			BindingResult result) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			return "redirect:/";
		}
		
	    if (result.hasErrors()) {
	        return "editClient.jsp";
	    }
	    Client existingClient = clientService.getOneClient(id);
	    if(existingClient == null) {
	    	return "redirect:/clients/all";
	    }
	    // Update only client info (nothing pet related)
	    clientService.updateClientDetails(existingClient, updatedClient);
	    
	    return "redirect:/clients/all";
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
		return "redirect:/clients/all";
	}
}
