package com.chrismoran.petsittersapplication.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="users")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(updatable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Date();
    }
    
    @NotBlank(message="First Name is required")
	@Size(min=1, max=128, message="First Name must be between 1 and 128 characters")
	private String firstName;
    
    @NotBlank(message="Last Name is required")
	@Size(min=1, max=128, message="Last Name must be between 1 and 128 characters")
	private String lastName;
	
	@NotBlank(message="Email is required")
	@Email(message="Please enter a valid email address")
	private String email;
	
	@NotBlank(message="Password is required")
	@Size(min=8, max=128, message="Password must be between 8 and 128 characters")
	@Pattern(regexp = "(?=.*[A-Z])(?=.*\\d).+", message="Password must contain at least one uppercase letter and one number")
	private String password;
	
	@Transient
	@NotBlank(message="Confirm Password is required")
	@Size(min=8, max=128, message="Confirm Password must be between 8 and 128 characters")
	private String confirm;
	
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Client> clients = new ArrayList<>();
	
	// Constructors
	public User() {}

	public User(
			@NotBlank(message = "First Name is required") @Size(min = 1, max = 128, message = "First Name must be between 1 and 128 characters") String firstName,
			@NotBlank(message = "Last Name is required") @Size(min = 1, max = 128, message = "Last Name must be between 1 and 128 characters") String lastName,
			@NotBlank(message = "Email is required") @Email(message = "Please enter a valid email address") String email,
			@NotBlank(message = "Password is required") @Size(min = 8, max = 128, message = "Password must be between 8 and 128 characters") @Pattern(regexp = "(?=.*[A-Z])(?=.*\\d).+", message = "Password must contain at least one uppercase letter and one number") String password,
			@NotBlank(message = "Confirm Password is required") @Size(min = 8, max = 128, message = "Confirm Password must be between 8 and 128 characters") String confirm,
			List<Client> clients) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.confirm = confirm;
		this.clients = clients;
	}

	// Getters & Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}
}
