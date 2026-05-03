package com.application.manager.domain.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

@Embeddable
public class Cv 
{
	
	@Embedded
	private CvContactData contactData;
	private String fotoPath;
	private String primaryColor;
	
	public CvContactData getContactData() 
	{
		return contactData;
	}
	
	public void setApplicantAddress(CvContactData contactData) 
	{
		this.contactData = contactData;
	}

	public String getFotoPath() 
	{
		return fotoPath;
	}

	public void setFotoPath(String fotoPath) 
	{
		this.fotoPath = fotoPath;
	}

	public String getPrimaryColor() 
	{
		return primaryColor;
	}

	public void setPrimaryColor(String primaryColor) 
	{
		this.primaryColor = primaryColor;
	}
}
