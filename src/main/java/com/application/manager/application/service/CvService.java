package com.application.manager.application.service;

import org.springframework.stereotype.Service;

import com.application.manager.domain.model.Application;
import com.application.manager.domain.model.Cv;
import com.application.manager.domain.model.Editable;
import com.application.manager.domain.model.Previewable;

@Service
public class CvService implements Editable<Cv>, Previewable<Cv>
{
	
	private final ApplicationService applicationService;
	
	public CvService(ApplicationService applicationService)
	{
		this.applicationService = applicationService;
	}
	
	public Cv getById(Long id)
	{
		return applicationService.getById(id).getCv();
	}
	
	public void updateColor(Long id, String color)
	{
		this.applicationService.getById(id).getCv().setPrimaryColor(color);
	}
	
	@Override
	public String preview(Long id, Cv cv) 
	{
		return "<html>" + cv.getContactData().getCvName() + "</html>";
	}

	@Override
	public Cv update(Long id, Cv cv) 
	{
		Application application = applicationService.getById(id);
		application.setCv(cv);
		applicationService.save(application);
		return cv;
	}

}
