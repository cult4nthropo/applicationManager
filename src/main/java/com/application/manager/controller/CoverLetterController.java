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

/**
 * REST controller for managing cover letters of applications.
 *
 * <p>Each cover letter is associated with an application via its ID.</p>
 *
 * <p>Base URL: {@code /api/applications/{id}/coverletter}</p>
 */
@RestController
@RequestMapping("/api/applications/{id}/coverletter")
public class CoverLetterController 
{
	
	private final CoverLetterService coverLetterService;
	
	public CoverLetterController(CoverLetterService coverLetterService)
	{
		this.coverLetterService = coverLetterService;
	}
	
	/**
	 * @param id application ID
	 * @return the associated cover letter
	 */
	@GetMapping
	public CoverLetter getById(@PathVariable Long id)
	{
		return coverLetterService.getById(id);
	}
	
	/**
	 * @param id application ID
	 * @param coverLetter updated cover letter data
	 * @return updated cover letter
	 */
	@PutMapping
	public CoverLetter updateCoverLetter(@PathVariable Long id, @RequestBody CoverLetter coverLetter)
	{
		return coverLetterService.update(id, coverLetter);
	}
	
	/**
	 * @param id application ID
	 * @param text new cover letter text
	 */
	@PutMapping("/text")
	public void updateText(@PathVariable Long id, @RequestBody String text)
	{
		coverLetterService.updateText(id, text);
	}
	
	/**
	 * @param id application ID
	 * @param coverLetter cover letter input data
	 * @return rendered preview as string (e.g. HTML or formatted text)
	 */
	@PostMapping("/preview")
	public String previewCoverLetter(@PathVariable Long id, @RequestBody CoverLetter coverLetter)
	{
		return coverLetterService.preview(id, coverLetter);
	}
}
