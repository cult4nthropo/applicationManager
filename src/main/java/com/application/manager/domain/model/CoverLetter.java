package com.application.manager.domain.model;

import java.time.LocalDate;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Lob;

@Embeddable
public class CoverLetter {
	@Embedded
	private SenderAdress applicant;
	@Embedded
	private ReceiverAdress recipient;
	private String city;
	private LocalDate date;
	private String subject;
	
	@Lob
	private String text;
	
	public SenderAdress getApplicant() {
		return applicant;
	}
	
	public void setApplicant(SenderAdress applicant) {
		this.applicant = applicant;
	}
	
	public ReceiverAdress getRecipient() {
		return recipient;
	}
	
	public void setRecipient(ReceiverAdress recipient) {
		this.recipient = recipient;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public LocalDate getDate() {
		return date;
	}
	
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}

}
