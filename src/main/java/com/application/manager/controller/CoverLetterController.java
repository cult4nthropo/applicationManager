package com.application.manager.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.manager.application.service.CoverLetterService;
import com.application.manager.domain.model.CoverLetter;

@RestController
@RequestMapping("/api/applications/{id}/coverletter")
public class CoverLetterController {
	
	private final CoverLetterService coverLetterService;
	
	public CoverLetterController(CoverLetterService coverLetterService)
	{
		this.coverLetterService = coverLetterService;
	}
	
	@GetMapping
	public CoverLetter getById(@PathVariable Long id, @RequestBody CoverLetter coverLetter)
	{
		return coverLetterService.getById(id);
	}
	
	@PutMapping
	public CoverLetter updateCoverLetter(@PathVariable Long id, @RequestBody CoverLetter coverLetter)
	{
		return coverLetterService.update(id, coverLetter);
	}
	
	@PutMapping("/text")
	public void updateText(@PathVariable Long id, @RequestBody String text)
	{
		coverLetterService.updateText(id, text);
	}
	
	@PostMapping("/preview")
	public String previewCoverLetter(@PathVariable Long id, @RequestBody CoverLetter coverLetter)
	{
		return coverLetterService.preview(id, coverLetter);
	}
	
	
}
