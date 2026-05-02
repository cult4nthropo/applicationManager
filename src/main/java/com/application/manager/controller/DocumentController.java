package com.application.manager.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.manager.application.service.DocumentService;
import com.application.manager.domain.model.Document;

@RestController
@RequestMapping("api/bewerbungen/{id}/documents")
public class DocumentController 
{
	private final DocumentService documentService;
	
	public DocumentController(DocumentService documentService)
	{
		this.documentService = documentService;
	}
	
	@GetMapping
	public List<Document> getAllDocuments(@PathVariable Long id)
	{
		return documentService.getAllDocumentsByApplicationId(id);
	}
	
	@PostMapping
	public void addDocumentToApplication(@PathVariable Long id, @RequestBody Document document)
	{
		documentService.addDocument(id, document);
	}
	
	@DeleteMapping("/{documentId}")
	public void deleteDocument(@PathVariable Long applicationId, @PathVariable Long documentId)
	{
		documentService.removeDocument(applicationId, documentId);
	}
}
