package com.example.gilasw.controllers;

import com.example.gilasw.domain.enums.CategoryType;
import com.example.gilasw.domain.entities.Log;
import com.example.gilasw.repositories.LogRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class NotificationControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private LogRepository logRepository;

    @AfterEach
    public void tearDown() {
        logRepository.deleteAll();
    }

    @Test
    public void testSendNotification_ValidCategoryAndMessage_ReturnsOk() throws Exception {
        String category = "Sports";
        String message = "New match tomorrow!";

        ResultActions resultActions = mockMvc.perform(post("/api/notifications/{category}", category)
                .contentType(MediaType.APPLICATION_JSON)
                .content(message));

        resultActions.andExpect(status().isOk());

        List<Log> notifications = logRepository.findAll();

        assertEquals(3, notifications.size());
        Log notification = notifications.get(0);
        assertEquals(CategoryType.valueOf(category), notification.getCategory());
        assertEquals(message, notification.getMessage());
    }

    @Test
    public void testSendNotification_InvalidCategory_ReturnsBadRequest() throws Exception {
        String invalidCategory = "Invalid";
        String message = "New match tomorrow!";

        ResultActions resultActions = mockMvc.perform(post("/api/notifications/{category}", invalidCategory)
                .contentType(MediaType.APPLICATION_JSON)
                .content(message));

        resultActions.andExpect(status().isBadRequest());

        List<Log> notifications = logRepository.findAll();
        assertEquals(0, notifications.size());
    }

    @Test
    public void testSendNotification_EmptyMessage_ReturnsBadRequest() throws Exception {
        String category = "Sports";
        String emptyMessage = "";

        ResultActions resultActions = mockMvc.perform(post("/api/notifications/{category}", category)
                .contentType(MediaType.APPLICATION_JSON)
                .content(emptyMessage));

        resultActions.andExpect(status().isBadRequest());

        List<Log> notifications = logRepository.findAll();

        assertEquals(0, notifications.size());
    }
}
