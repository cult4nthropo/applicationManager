package com.application.manager.domain.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Embeddable
public class Cv 
{
	
	@Embedded
	private CvContactData contactData;
	private String fotoPath;
	private String primaryColor;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "cv_id")
	private List<Job> jobs = new ArrayList<Job>();
	@ElementCollection
	private List<String> techStack = new ArrayList<String>();
	
	
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

	public List<Job> getJobs() {
		return jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}

	public List<String> getTechStack() {
		return techStack;
	}

	public void setTechStack(List<String> techStack) {
		this.techStack = techStack;
	}
}
