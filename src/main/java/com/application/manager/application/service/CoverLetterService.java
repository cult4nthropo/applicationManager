package com.application.manager.application.service;


import org.springframework.stereotype.Service;

import com.application.manager.domain.model.Application;
import com.application.manager.domain.model.CoverLetter;
import com.application.manager.domain.model.Editable;
import com.application.manager.domain.model.Previewable;

@Service
public class CoverLetterService implements Editable<CoverLetter>, Previewable<CoverLetter>{

	private final ApplicationService applicationService;
	
	public CoverLetterService(ApplicationService applicationService)
	{
		this.applicationService = applicationService;
	}
	
	public CoverLetter getById(Long id)
	{
		return applicationService.getById(id).getCoverLetter();
	}
	
	@Override
	public String preview(Long id, CoverLetter coverLetter) {
		return """
		        <html>
		        <body>

		            <div>%s</div>
		            <div>%s</div>
		            <div>%s</div>
		            
		            <div>%s</div>
		            <div>%s</div>
		            <div>%s</div>

		            <h3>%s</h3>

		            <p>%s</p>

		        </body>
		        </html>
		        """.formatted(
		            coverLetter.getApplicant().getSenderName(),
		            coverLetter.getApplicant().getSenderStreet(),
		            coverLetter.getApplicant().getSenderCity(),
		            coverLetter.getRecipient().getReceiverName(),
		            coverLetter.getRecipient().getReceiverStreet(),
		            coverLetter.getRecipient().getReceiverCity(),
		            coverLetter.getSubject(),
		            coverLetter.getText()
		        );
	}

	@Override
	public CoverLetter update(Long id, CoverLetter coverLetter) {
		Application application = applicationService.getById(id);
		application.setCoverLetter(coverLetter);
		applicationService.save(application);
		return coverLetter;
	}

	public void updateText(Long id, String text) {
		Application application = applicationService.getById(id);
		CoverLetter coverLetter = application.getCoverLetter();
		
		if (coverLetter == null)
			application.setCoverLetter(new CoverLetter());
		
		coverLetter.setText(text);
		applicationService.save(application);
	}

}
