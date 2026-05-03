package com.application.manager.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.application.manager.domain.model.Application;
import com.application.manager.domain.model.ApplicationNotFoundException;
import com.application.manager.domain.model.CoverLetter;
import com.application.manager.domain.model.Deletable;
import com.application.manager.domain.model.PdfGeneratable;
import com.application.manager.domain.repository.ApplicationRepository;

import jakarta.annotation.Resource;

@Service
public class ApplicationService implements PdfGeneratable, Deletable
{
	
	private final ApplicationRepository applicationRepository;
	
	public ApplicationService(ApplicationRepository applicationRepository)
	{
		this.applicationRepository = applicationRepository;
	}
	
	public Application getById(Long id)
	{
		return applicationRepository.findById(id).orElseThrow(() -> new ApplicationNotFoundException(id));
	}
	
	public Application save(Application application)
	{
		return applicationRepository.save(application);
	}
	
	@Override
	public void delete(Long id) 
	{
		applicationRepository.deleteById(id);		
	}

	@Override
	public byte[] generatePdf(Long id) 
	{
		Application application = getById(id);
		return ("PDF für " + application.getCompany()).getBytes();
	}

	public void setCoverLetter(CoverLetter coverLetter) 
	{
		// TODO Auto-generated method stub
		
	}

	public List<Application> findAll() 
	{
		// TODO Auto-generated method stub
		return null;
	}

	public Resource loadPdf(Long id) 
	{
		// TODO Auto-generated method stub
		return null;
	}
}
