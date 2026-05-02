package com.application.manager.domain.model;

public interface PdfGeneratable {
	byte[] generatePdf(Long id);
}
