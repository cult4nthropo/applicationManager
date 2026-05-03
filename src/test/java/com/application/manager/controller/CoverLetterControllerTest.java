package com.application.manager.controller;

import com.application.manager.application.service.CoverLetterService;
import com.application.manager.domain.model.CoverLetter;

import tools.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CoverLetterController.class)
class CoverLetterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CoverLetterService coverLetterService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getById_shouldReturnCoverLetter() throws Exception {
        CoverLetter cl = new CoverLetter();

        when(coverLetterService.getById(1L)).thenReturn(cl);

        mockMvc.perform(get("/api/applications/{id}/coverletter", 1L))
                .andExpect(status().isOk());
    }

    @Test
    void updateCoverLetter_shouldReturnUpdated() throws Exception {
        CoverLetter input = new CoverLetter();
        CoverLetter updated = new CoverLetter();

        when(coverLetterService.update(Mockito.eq(1L), Mockito.any()))
                .thenReturn(updated);

        mockMvc.perform(put("/api/applications/{id}/coverletter", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk());
    }

    @Test
    void updateText_shouldCallService() throws Exception {
        String text = "new text";

        mockMvc.perform(put("/api/applications/{id}/coverletter/text", 1L)
                        .contentType(MediaType.TEXT_PLAIN)
                        .content(text))
                .andExpect(status().isOk());

        verify(coverLetterService).updateText(1L, text);
    }

    @Test
    void preview_shouldReturnString() throws Exception {
        CoverLetter input = new CoverLetter();

        when(coverLetterService.preview(Mockito.eq(1L), Mockito.any()))
                .thenReturn("preview-result");

        mockMvc.perform(post("/api/applications/{id}/coverletter/preview", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(content().string("preview-result"));
    }
}