package com.application.manager.domain.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class CvContactData 
{
	private String cvContactName;
	private String cvContactBirthday;
	private String cvContactStreet;
	private String cvContactCity;
	private String cvContactPhone;
	private String cvContactMail;
	
	public CvContactData() {}
	
	public String getCvContactName() 
	{
		return cvContactName;
	}
	
	public void setContactCvName(String name) 
	{
		this.cvContactName = name;
	}
	
	public String getCvContactStreet() 
	{
		return cvContactStreet;
	}
	
	public void setCvContactStreet(String street) 
	{
		this.cvContactStreet = street;
	}
	
	public String getCvContactCity() 
	{
		return cvContactCity;
	}
	
	public void setContactCvCity(String city) 
	{
		this.cvContactCity = city;
	}
	
	public String getCvContactPhone() 
	{
		return cvContactPhone;
	}
	
	public void setCvContactPhone(String phone) 
	{
		this.cvContactPhone = phone;
	}
	
	public String getCvContactMail() 
	{
		return cvContactMail;
	}
	
	public void setCvContactMail(String mail) 
	{
		this.cvContactMail = mail;
	}

	public String getCvContactBirthday() {
		return cvContactBirthday;
	}

	public void setCvContactBirthday(String cvContactBirthday) {
		this.cvContactBirthday = cvContactBirthday;
	}
}
