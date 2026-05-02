package com.application.manager.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.application.manager.application.service.CvService;
import com.application.manager.domain.model.Cv;

@RestController
@RequestMapping("/api/applications/{id}/cv")
public class CvController {
	
	private final CvService cvService;
	
	public CvController(CvService cvService)
	{
		this.cvService = cvService;
	}
	
	@GetMapping
	public Cv getById(@PathVariable Long id)
	{
		return cvService.getById(id);
	}
	
	@PutMapping
	public Cv update(@PathVariable Long id, @RequestBody Cv cv)
	{
		return cvService.update(id, cv);
	}
	
	@PostMapping("/preview")
	public String preview(@PathVariable Long id, @RequestBody Cv cv)
	{
		return cvService.preview(id, cv);
	}
	
	@PatchMapping("/color")
	public void updateColor(@PathVariable Long id, @RequestParam String color)
	{
		cvService.updateColor(id, color);
	}
}
