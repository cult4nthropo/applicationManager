package com.application.manager.controller;

import com.application.manager.application.service.ApplicationService;
import com.application.manager.domain.model.Application;

import tools.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.hamcrest.Matchers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ApplicationController.class)
class ApplicationControllerTest 
{

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ApplicationService applicationService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void create_shouldReturnSavedApplication() throws Exception {
        Application application = new Application();

        when(applicationService.save(Mockito.any(Application.class)))
                .thenReturn(application);

        mockMvc.perform(post("/api/applications")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(application)))
                .andExpect(status().isOk());
        }

    @Test
    void getAll_shouldReturnList() throws Exception {
        Application application = new Application();

        when(applicationService.findAll())
                .thenReturn(List.of(application));

        mockMvc.perform(get("/api/applications"))
                .andExpect(status().isOk());
    }

    @Test
    void getById_shouldReturnApplication() throws Exception {
        Application app = new Application();

        when(applicationService.getById(42L)).thenReturn(app);

        mockMvc.perform(get("/api/applications/42"))
                .andExpect(status().isOk());
    }

    @Test
    void generatePdf_shouldReturnPdfWithHeaders() throws Exception {
        Long id = 5L;
        byte[] pdf = "pdf-content".getBytes();

        when(applicationService.generatePdf(id)).thenReturn(pdf);

        mockMvc.perform(post("/api/applications/" + id + "/pdf"))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE))
                .andExpect(header().string(HttpHeaders.CONTENT_DISPOSITION,
                        Matchers.containsString("prasatko_kathleen_bewerbung_" + id + ".pdf")))
                .andExpect(content().bytes(pdf));
    }

    @Test
    void delete_shouldReturn204() throws Exception {
        mockMvc.perform(delete("/api/applications/10"))
                .andExpect(status().isNoContent());
    }
}