package com.application.manager.domain.model;

public interface Editable<T> 
{
	
	T update(Long id, T data);
}
