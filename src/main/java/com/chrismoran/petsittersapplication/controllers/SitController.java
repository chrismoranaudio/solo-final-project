package com.chrismoran.petsittersapplication.controllers;

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
	
	// Show new sit form
	@GetMapping("/sits/new")
	public String showSitForm(Model model) {
		model.addAttribute("allClients", clientService.getAllClients());
		return "newSit.jsp";
	}
	
	// Process the sit form
	@PostMapping("/sits/new")
	public String createSit(
			@Valid @ModelAttribute("newSit") Sit newSit,
			BindingResult result) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			return "redirect:/";
		}
		if(result.hasErrors()) {
			return "newSit.jsp";
		}
		sitService.createSit(newSit);
		return "redirect:/home";
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
			BindingResult result) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			return "redirect:/";
		}
		if(result.hasErrors()) {
			return "editSit.jsp";
		}
		Sit thisSit = sitService.getOneSit(id);
		if(thisSit == null) {
			return "redirect:/home";
		}
		sitService.updateSit(editSit);
		return "redirect:/home";
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
		return "redirect:/home";
	}
}










