package com.application.manager.application.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.application.manager.domain.model.Application;
import com.application.manager.domain.model.Cv;
import com.application.manager.domain.model.Editable;
import com.application.manager.domain.model.Job;
import com.application.manager.domain.model.Previewable;

@Service
public class CvService implements Editable<Cv>, Previewable<Cv>
{
	
	private final ApplicationService applicationService;
	
	public CvService(ApplicationService applicationService)
	{
		this.applicationService = applicationService;
	}
	
	public Cv getById(Long id)
	{
		return applicationService.getById(id).getCv();
	}
	
	public void updateColor(Long id, String color)
	{
		this.applicationService.getById(id).getCv().setPrimaryColor(color);
	}
	
	@Override
	public String preview(Long id, Cv cv) 
	{
		String template = loadTemplate();
		String css = loadCss();

		template = template.replace("</head>", "<style>" + css + "</style></head>");

		template = template
		        .replace("{{cvContactName}}", safe(cv.getContactData().getCvName()))
		        .replace("{{cvContactBirthday}}", safe(cv.getContactData().getCvContactBirthday()))
		        .replace("{{cvContactStreet}}", safe(cv.getContactData().getCvStreet()))
		        .replace("{{cvContactCity}}", safe(cv.getContactData().getCvCity()))
		        .replace("{{cvContactPhone}}", safe(cv.getContactData().getCvPhone()))
		        .replace("{{cvContactMail}}", safe(cv.getContactData().getCvMail()));
		template = template.replace(
			    "{{work_experience}}",
			    renderJobs(cv.getJobs())
			);

		String techStackHtml =
		        cv.getTechStack() == null ? "" :
		        cv.getTechStack().stream()
		                .map(t -> "<li>" + safe(t) + "</li>")
		                .reduce("", String::concat);

		template = template.replace("{{tech_stack}}", techStackHtml);

		return template;
	}

	private String renderJobs(List<Job> jobs) {
	    if (jobs == null || jobs.isEmpty()) {
	        return "";
	    }

	    return jobs.stream()
	            .map(job -> {
	                String tasksHtml = job.getTasks() == null ? "" :
	                        job.getTasks().stream()
	                                .map(t -> "<li>" + safe(t) + "</li>")
	                                .reduce("", String::concat);

	                return """
	                    <article class="job">
	                        <h3>%s (%s – %s)</h3>
	                        <div class="company">%s</div>
	                        <div class="tech">%s</div>
	                        <ul>
	                            %s
	                        </ul>
	                    </article>
	                """.formatted(
	                        safe(job.getTitle()),
	                        safe(job.getStartDate()),
	                        safe(job.getEndDate()),
	                        safe(job.getCompany()),
	                        safe(job.getTech()),
	                        tasksHtml
	                );
	            })
	            .reduce("", String::concat);
	}

	@Override
	public Cv update(Long id, Cv cv) 
	{
		Application application = applicationService.getById(id);
		application.setCv(cv);
		applicationService.save(application);
		return cv;
	}
	
	private String loadTemplate()
	{
		try (InputStream inputStream = getClass().getResourceAsStream("/templates/cv-template.html"))
		{
			return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		} 
	}
	
	private String loadCss()
	{
		try (InputStream inputStream = getClass().getResourceAsStream("/static/css/cv.css"))
		{
			return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}
	
	private String safe(String value) {
	    return value == null ? "" : value;
	}
}
