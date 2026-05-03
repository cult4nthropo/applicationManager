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

@RestController
@RequestMapping("/api/applications")
public class ApplicationController 
{

		private final ApplicationService applicationService;
		
		public ApplicationController(ApplicationService applicationService)
		{
			this.applicationService = applicationService;
		}
		
		@PostMapping
		public Application create(@RequestBody Application application)
		{
			return applicationService.save(application);
		}
		
		@GetMapping
		public List<Application>getAllApplications()
		{
			return applicationService.findAll();
		}
		
		@GetMapping("/{id}")
		public Application getById(@PathVariable Long id)
		{
			return applicationService.getById(id);
		}
		
		
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
		
		@DeleteMapping("/{id}")
		@ResponseStatus(HttpStatus.NO_CONTENT)
		public void delete(@PathVariable Long id)
		{
			applicationService.delete(id);
		}
		
}
