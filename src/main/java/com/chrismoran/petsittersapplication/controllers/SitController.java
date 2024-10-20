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
import org.springframework.web.bind.annotation.RequestParam;

import com.chrismoran.petsittersapplication.models.Client;
import com.chrismoran.petsittersapplication.models.Sit;
import com.chrismoran.petsittersapplication.services.ClientService;
import com.chrismoran.petsittersapplication.services.SitService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class SitController {

	@Autowired
	private SitService sitService;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private HttpSession session;
	
	// Show all sits
	@GetMapping("/sits/all")
	public String showAllSits(Model model) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			return "redirect:/";
		}
		List <Sit> sits = sitService.getAllSits();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd");
		model.addAttribute("dateFormatter", formatter);
		model.addAttribute("sits", sits);
		return "allSits.jsp";
	}
	
	// Show new sit form
	@GetMapping("/sits/new")
	public String showSitForm(@RequestParam(value="clientId", required=false) Long clientId, Model model) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			return "redirect:/";
		}
		List<Client> clients = clientService.getAllClients();
		model.addAttribute("clients", clients);
		
		if(clientId != null) {
			Client client = clientService.getOneClient(clientId);
			model.addAttribute("selectedClient", client);
		}
		
		model.addAttribute("newSit", new Sit());
		
		return "newSit.jsp";
	}
	
	// Process the sit form
	@PostMapping("/sits/new")
	public String createSit(
			@Valid @ModelAttribute("newSit") Sit newSit,
			BindingResult result, Model model) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			return "redirect:/";
		}
		
		// To make sure End Date is after Start date
		if (!result.hasFieldErrors("startDate") && !result.hasFieldErrors("endDate")) {
			if(newSit.getEndDate().isBefore(newSit.getStartDate())) {
				result.rejectValue("endDate", "error.sit", "End date must be after start date");
			}
		}
		if(result.hasErrors()) {
			List<Client> clients = clientService.getAllClients();
			model.addAttribute("clients", clients);
			model.addAttribute("newSit", newSit);
			
			// To pre-populate the client dropdown if a client exists
			if(newSit.getClient() != null && newSit.getClient().getId() != null) {
				Client selectedClient = clientService.getOneClient(newSit.getClient().getId());
				model.addAttribute("selectedClient", selectedClient);
			}
			return "newSit.jsp";
		}
		sitService.createSit(newSit);
		return "redirect:/sits/all";
	}
	
	// Show the edit sit form
	@GetMapping("/sits/{id}/edit")
	public String editSitForm(
			@ModelAttribute("editSit")
			@PathVariable Long id, Model model) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			return "redirect:/";
		}
		List<Client> clients = clientService.getAllClients();
		model.addAttribute("clients", clients);
		
		Sit thisSit = sitService.getOneSit(id);
		if(thisSit == null) {
			return "redirect:/home";
		}
		model.addAttribute("editSit", thisSit);
		return "editSit.jsp";
	}
	
	// Process the edit sit form
	@PutMapping("/sits/{id}/update")
	public String updateSit(
			@PathVariable Long id,
			@Valid @ModelAttribute("editSit") Sit editSit,
			BindingResult result, Model model) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			return "redirect:/";
		}
		Sit thisSit = sitService.getOneSit(id);
		if(thisSit == null) {
			return "redirect:/home";
		}
		
		if(editSit.getEndDate().isBefore(editSit.getStartDate())) {
			result.rejectValue("endDate", "error.sit", "End date must be after start date");
		}
		
		if(result.hasErrors()) {
			model.addAttribute("clients", clientService.getAllClients());
			return "editSit.jsp";
		}
		
		sitService.updateSit(editSit);
		return "redirect:/sits/all";
	}
	
	// Delete sit
	@DeleteMapping("/sits/{id}/delete")
	public String deleteSit(@PathVariable Long id) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			return "redirect:/";
		}
		Sit sitToDelete = sitService.getOneSit(id);
		if(sitToDelete == null) {
			return "redirect:/home";
		}
		sitService.deleteSit(id);
		return "redirect:/sits/all";
	}
}
