package com.application.manager.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.application.manager.domain.model.Application;
import com.application.manager.domain.model.Document;

@Service
public class DocumentService 
{

	private final ApplicationService applicationService;
	
	public DocumentService(ApplicationService applicationService)
	{
		this.applicationService = applicationService;
	}
	
	public List<Document> getAllDocumentsByApplicationId(Long id) 
	{
		return applicationService.getById(id).getDocuments();
	}

	public void addDocument(Long id, Document document) 
	{
		Application application = applicationService.getById(id);
		application.getDocuments().add(document);
		applicationService.save(application);
	}

	public void removeDocument(Long applicationId, Long documentId) 
	{
		Application application = applicationService.getById(applicationId);
		application.getDocuments().removeIf(d -> d.getId().equals(documentId));
		applicationService.save(application);
	}

}
