package com.chrismoran.petsittersapplication.models;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="sits")
public class Sit {

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
    
    @NotNull(message="Start date is required")
    private LocalDate startDate;
    
    @NotNull(message="End date is required")
    private LocalDate endDate;
    
    @NotNull(message="Daily visits is required")
    @Min(value=1, message="At least 1 daily visit is required")
    @Max(value=4, message="Maximum of 4 daily visits are allowed")
    private Integer dailyVisits;
    
    private String notes;
    
    @Enumerated(EnumType.STRING)
    @NotNull(message="Final visit is required")
    private FinalVisit finalVisit;
    
    @ManyToOne
    @JoinColumn(name="client_id", nullable=false)
    private Client client;
    
    // Constructors
    public Sit() {}

	public Sit(@NotNull(message = "Start date is required") LocalDate startDate,
			@NotNull(message = "End date is required") LocalDate endDate,
			@NotNull(message = "Daily visits is required") @Min(value = 1, message = "At least 1 daily visit is required") @Max(value = 4, message = "Maximum of 4 daily visits are allowed") Integer dailyVisits,
			String notes, @NotNull(message = "Final visit is required") FinalVisit finalVisit, Client client) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.dailyVisits = dailyVisits;
		this.notes = notes;
		this.finalVisit = finalVisit;
		this.client = client;
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

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public Integer getDailyVisits() {
		return dailyVisits;
	}

	public void setDailyVisits(Integer dailyVisits) {
		this.dailyVisits = dailyVisits;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public FinalVisit getFinalVisit() {
		return finalVisit;
	}

	public void setFinalVisit(FinalVisit finalVisit) {
		this.finalVisit = finalVisit;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
     
}

enum FinalVisit {
	MORNING, MID_DAY, DINNER, BEDTIME
}
