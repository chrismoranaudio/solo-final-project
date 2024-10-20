package com.chrismoran.petsittersapplication.models;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

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
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name="clients")
public class Client {

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
    
    @NotBlank(message="First name is required")
    private String firstName;
    
    @NotBlank(message="Last name is required")
    private String lastName;
    
    @NotBlank(message="Address is required")
    private String address;
    
    @NotBlank(message="Phone number is required")
    @Pattern(regexp = "^(\\+0?1\\s)?\\(?\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{4}$", message="Please enter a valid phone number")
    private String phoneNumber;
    
    @NotNull(message = "Price quoted is required")
    @Min(value = 0, message = "Price quoted must be a positive number")
    @Digits(integer = 10, fraction = 0, message = "Price quoted must be a valid whole number")
    private Integer priceQuoted;
    
//    @NotNull(message="Price quoted is required")
//    @Min(value=0, message="Price quoted must be a positive number")
//    private Double priceQuoted;
    
    @NotNull(message="Please enter number of daily visits required")
    @Min(value=1, message="At least 1 daily visit is required")
    @Max(value=4, message="Maximum 4 dailys visits are allowed")
    private Integer dailyVisits;
    
    private Integer numberOfDogs = 0;
    
    private Integer numberOfCats = 0;
    
    @OneToMany(mappedBy="client", fetch = FetchType.LAZY)
    private List<Pet> pets;
    
    @OneToMany(mappedBy="client", fetch = FetchType.LAZY)
    private List<Sit> sits;
    
    // Constructors
    public Client() {}

	public Client(@NotBlank(message = "First name is required") String firstName,
			@NotBlank(message = "Last name is required") String lastName,
			@NotBlank(message = "Address is required") String address,
			@NotBlank(message = "Phone number is required") @Pattern(regexp = "^(\\+0?1\\s)?\\(?\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{4}$", message = "Please enter a valid phone number") String phoneNumber,
			@NotNull(message = "Price quoted is required") @Min(value = 0, message = "Price quoted must be a positive number") @Digits(integer = 10, fraction = 0, message = "Price quoted must be a valid whole number") Integer priceQuoted,
			@NotNull(message = "Please enter number of daily visits required") @Min(value = 1, message = "At least 1 daily visit is required") @Max(value = 4, message = "Maximum 4 dailys visits are allowed") Integer dailyVisits,
			Integer numberOfDogs, Integer numberOfCats, List<Pet> pets, List<Sit> sits) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.priceQuoted = priceQuoted;
		this.dailyVisits = dailyVisits;
		this.numberOfDogs = numberOfDogs;
		this.numberOfCats = numberOfCats;
		this.pets = pets;
		this.sits = sits;
	}

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Integer getPriceQuoted() {
		return priceQuoted;
	}

	public void setPriceQuoted(Integer priceQuoted) {
		this.priceQuoted = priceQuoted;
	}

	public Integer getDailyVisits() {
		return dailyVisits;
	}

	public void setDailyVisits(Integer dailyVisits) {
		this.dailyVisits = dailyVisits;
	}

	public Integer getNumberOfDogs() {
		return numberOfDogs;
	}

	public void setNumberOfDogs(Integer numberOfDogs) {
		this.numberOfDogs = numberOfDogs;
	}

	public Integer getNumberOfCats() {
		return numberOfCats;
	}

	public void setNumberOfCats(Integer numberOfCats) {
		this.numberOfCats = numberOfCats;
	}

	public List<Pet> getPets() {
		return pets;
	}

	public void setPets(List<Pet> pets) {
		this.pets = pets;
	}

	public List<Sit> getSits() {
		return sits;
	}

	public void setSits(List<Sit> sits) {
		this.sits = sits;
	}

	
}
