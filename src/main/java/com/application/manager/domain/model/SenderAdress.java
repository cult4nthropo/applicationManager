package com.application.manager.domain.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class SenderAdress {
	
	private String senderName;
	private String senderStreet;
	private String senderCity;
	private String senderPhone;
	private String senderMail;
	
	public SenderAdress() {}
	
	public String getSenderName() {
		return senderName;
	}
	
	public void setSenderName(String name) {
		this.senderName = name;
	}
	
	public String getSenderStreet() {
		return senderStreet;
	}
	
	public void setSenderStreet(String street) {
		this.senderStreet = street;
	}
	
	public String getSenderCity() {
		return senderCity;
	}
	
	public void setSenderCity(String city) {
		this.senderCity = city;
	}
	
	public String getSenderPhone() {
		return senderPhone;
	}
	
	public void setSenderPhone(String phone) {
		this.senderPhone = phone;
	}
	
	public String getSenderMail() {
		return senderMail;
	}
	
	public void setSenderMail(String mail) {
		this.senderMail = mail;
	}
}
