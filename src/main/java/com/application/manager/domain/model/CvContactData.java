package com.application.manager.domain.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class CvContactData {
	private String cvContactName;
	private String cvContactStreet;
	private String cvContactCity;
	private String cvContactPhone;
	private String cvContactMail;
	
	public CvContactData() {}
	
	public String getCvName() {
		return cvContactName;
	}
	
	public void setCvName(String name) {
		this.cvContactName = name;
	}
	
	public String getCvStreet() {
		return cvContactStreet;
	}
	
	public void setCvStreet(String street) {
		this.cvContactStreet = street;
	}
	
	public String getCvCity() {
		return cvContactCity;
	}
	
	public void setCvCity(String city) {
		this.cvContactCity = city;
	}
	
	public String getCvPhone() {
		return cvContactPhone;
	}
	
	public void setCvPhone(String phone) {
		this.cvContactPhone = phone;
	}
	
	public String getCvMail() {
		return cvContactMail;
	}
	
	public void setCvMail(String mail) {
		this.cvContactMail = mail;
	}
}
