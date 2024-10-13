package com.chrismoran.petsittersapplication.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.chrismoran.petsittersapplication.models.LoginUser;
import com.chrismoran.petsittersapplication.models.User;
import com.chrismoran.petsittersapplication.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	
	// Register method
	public User register(User newUser, BindingResult result) {
		// Check if the email is already taken
		Optional<User> potentialUser = userRepo.findByEmail(newUser.getEmail());
		if(potentialUser.isPresent()) {
			result.rejectValue("email", "Matches", "An account with this email already exists.");
		}
		
		// Check if password matches confirm password
		if(!newUser.getPassword().equals(newUser.getConfirm())) {
			result.rejectValue("confirm", "Matches", "The Confirm Password must match Password.");
		}
		
		// If there are any errors, return null
		if(result.hasErrors()) {
			return null;
		}
		
		// Hash the password and set it for the new user
		String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
		newUser.setPassword(hashed);
		
		// Save and return the new user
		return userRepo.save(newUser);
	}
		
	// Login method
	public User login(LoginUser newLoginObject, BindingResult result) {
		// Find user by email
		Optional<User> potentialUser = userRepo.findByEmail(newLoginObject.getEmail());
		
		// If the email does not exist in the database, return an error
		if(!potentialUser.isPresent()) {
			result.rejectValue("email", "Matches", "Invalid login credentials!");
			return null;
		}
		
		User user = potentialUser.get();
		
		// If the password does not match, return an error
		if(!BCrypt.checkpw(newLoginObject.getPassword(), user.getPassword())) {
			result.rejectValue("password", "Matches", "Invalid login credentials!");
		}
		
		// Return null if there are any errors
		if(result.hasErrors()) {
			return null;
		}
		
		// Otherwise, return the user
		return user;
	}
		
	// Get user by id
	public User getOneUser(Long id) {
		Optional<User> potentialUser = userRepo.findById(id);
		if(potentialUser.isPresent()) {
			return potentialUser.get();
		} else {
			return null;
		}
	}
}
