package com.example.gilasw.controllers;

import com.example.gilasw.helpers.NotificationRequest;
import com.example.gilasw.model.Notification;
import com.example.gilasw.repositories.NotificationRepository;
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
    private NotificationRepository notificationRepository;

    @AfterEach
    public void tearDown() {
        notificationRepository.deleteAll();
    }

    @Test
    public void testSendNotification_ValidCategoryAndMessage_ReturnsOk() throws Exception {
        String category = "Sports";
        String message = "New match tomorrow!";

        NotificationRequest request = new NotificationRequest(category, message);
        String requestBody = objectMapper.writeValueAsString(request);

        ResultActions resultActions = mockMvc.perform(post("/api/notifications/{category}", category)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));

        resultActions.andExpect(status().isOk());

        List<Notification> notifications = notificationRepository.findAll();

        assertEquals(1, notifications.size());
        Notification notification = notifications.get(0);
        assertEquals(category, notification.getCategory());
        assertEquals(message, notification.getMessage());
    }

    @Test
    public void testSendNotification_InvalidCategory_ReturnsBadRequest() throws Exception {
        String invalidCategory = "Invalid";
        String message = "New match tomorrow!";

        NotificationRequest request = new NotificationRequest(invalidCategory, message);
        String requestBody = objectMapper.writeValueAsString(request);

        ResultActions resultActions = mockMvc.perform(post("/api/notifications/{category}", invalidCategory)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));

        resultActions.andExpect(status().isBadRequest());

        List<Notification> notifications = notificationRepository.findAll();
        assertEquals(0, notifications.size());
    }

    @Test
    public void testSendNotification_EmptyMessage_ReturnsBadRequest() throws Exception {
        String category = "Sports";
        String emptyMessage = "";

        NotificationRequest request = new NotificationRequest(category, emptyMessage);
        String requestBody = objectMapper.writeValueAsString(request);

        ResultActions resultActions = mockMvc.perform(post("/api/notifications/{category}", category)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));

        resultActions.andExpect(status().isBadRequest());

        List<Notification> notifications = notificationRepository.findAll();

        assertEquals(0, notifications.size());
    }
}
