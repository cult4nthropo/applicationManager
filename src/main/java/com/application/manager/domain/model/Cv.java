package com.application.manager.domain.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

@Embeddable
public class Cv 
{
	@Embedded
    private CvContactData contactData;

    private String fotoFilePath;
    private String primaryColor;

    @ElementCollection
    @CollectionTable(name = "cv_jobs", joinColumns = {})
    private List<Job> jobs = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "cv_techstack")
    @Column(name = "tech")
    private List<String> techStack = new ArrayList<>();
	
	
	public CvContactData getContactData() 
	{
		return contactData;
	}
	
	public void setApplicantAddress(CvContactData contactData) 
	{
		this.contactData = contactData;
	}

	public String getFotoFilePath() 
	{
		return fotoFilePath;
	}

	public void setFotoFilePath(String fotoPath) 
	{
		this.fotoFilePath = fotoPath;
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
