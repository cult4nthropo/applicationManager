package com.application.manager.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
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
		public void generatePdf(@PathVariable Long id)
		{
			applicationService.generatePdf(id);
		}
		
		@DeleteMapping("/{id}")
		@ResponseStatus(HttpStatus.NO_CONTENT)
		public void delete(@PathVariable Long id)
		{
			applicationService.delete(id);
		}
		
}
