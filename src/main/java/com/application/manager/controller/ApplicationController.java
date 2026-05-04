package com.application.manager.controller;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.application.manager.application.service.ApplicationService;
import com.application.manager.domain.model.Application;

/**
 * REST controller for managing {@link com.application.manager.domain.model.Application}.
 *
 * <p>Provides CRUD operations for applications as well as PDF generation.
 * Each application is identified by its unique ID.</p>
 *
 * <p>Base URL: {@code /api/applications}</p>
 */
@RestController
@RequestMapping("/api/applications")
public class ApplicationController 
{

		private final ApplicationService applicationService;
		
		public ApplicationController(ApplicationService applicationService)
		{
			this.applicationService = applicationService;
		}
		
		/**
		 * @param application the application to create
		 * @return the persisted application including generated ID
		 */
		@PostMapping
		public Application create(@RequestBody Application application)
		{
			return applicationService.save(application);
		}
		
		/**
		 * Retrieves all stored applications.
		 *
		 * @return list of all applications
		 */
		@GetMapping
		public List<Application>getAllApplications()
		{
			return applicationService.findAll();
		}
		
		/**
		 * @param id the application ID
		 * @return the application if found
		 * @throws com.application.manager.exception.ResourceNotFoundException if not found
		 */
		@GetMapping("/{id}")
		public Application getById(@PathVariable Long id)
		{
			return applicationService.getById(id);
		}
		
		/**
		 * @param id the application ID
		 * @return PDF file as byte array
		 */
		@PostMapping("/{id}/pdf")
		public ResponseEntity<byte[]> generatePdf(@PathVariable Long id)
		{
			byte[] pdf = applicationService.generatePdf(id);
			String filename = "prasatko_kathleen_bewerbung_" + id + ".pdf";
			return  ResponseEntity
					.ok()
					.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE)
					.header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + filename + "\"")
					.body(pdf);
					
		}
		
		/**
		 * Deletes an application by its ID.
		 *
		 * @param id the application ID
		 */
		@DeleteMapping("/{id}")
		@ResponseStatus(HttpStatus.NO_CONTENT)
		public void delete(@PathVariable Long id)
		{
			applicationService.delete(id);
		}
		
}
