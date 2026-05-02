package com.application.manager.domain.model;

public interface Previewable<T> {
	String preview(Long id, T data);
}
