package com.application.manager.domain.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ApplicationNotFoundException extends RuntimeException
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ApplicationNotFoundException(String message)
	{
		super(message);
	}
	
	public ApplicationNotFoundException(Long id)
	{
		super("Application with id " + id + " not found.");
	}
}
