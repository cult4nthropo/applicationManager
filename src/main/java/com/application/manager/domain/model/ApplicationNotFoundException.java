package com.application.manager.domain.model;

public class ApplicationNotFoundException extends RuntimeException{

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
		super("Bewerbung mit der id " + id + " existiert nicht");
	}
}
