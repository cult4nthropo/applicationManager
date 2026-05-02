package com.application.manager.domain.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Application {
	
	@Id
	@GeneratedValue
	private long id;
	
	private String company;
	private String status;
	
	@Embedded
	private CoverLetter coverLetter;
	@Embedded
	private Cv cv;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Document> documents = new ArrayList<Document>();
	
	private LocalDate createdAt;
	private LocalDate interviewDate;
	
	public String getCompany() {
		return company;
	}
	
	public void setCompany(String company) {
		this.company = company;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

	public CoverLetter getCoverLetter() {
		return coverLetter;
	}

	public void setCoverLetter(CoverLetter coverLetter) {
		this.coverLetter = coverLetter;
	}

	public Cv getCv() {
		return cv;
	}

	public void setCv(Cv cv) {
		this.cv = cv;
	}

	public List<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDate getInterviewDate() {
		return interviewDate;
	}

	public void setInterviewDate(LocalDate interviewDate) {
		this.interviewDate = interviewDate;
	}
}
