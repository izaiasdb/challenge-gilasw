package com.example.gilasw.services;

import com.example.gilasw.exceptions.InvalidCategoryException;
import com.example.gilasw.model.NotificationType;
import com.example.gilasw.model.User;
import com.example.gilasw.notifications.NotificationSender;
import com.example.gilasw.repositories.NotificationRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
@DataJpaTest
public class NotificationServiceTest {

    @Autowired
    private NotificationRepository notificationRepository;

    @MockBean
    private NotificationSender notificationSender;

    private NotificationService notificationService;

    @AfterEach
    public void tearDown() {
        notificationRepository.deleteAll();
    }

    @Test
    public void testSendNotification_ValidCategoryAndMessage_NotifiesSubscribedUsers() {
        String category = "Sports";
        String message = "New match tomorrow!";

        User user1 = new User(1, "John", "john@example.com", "1234567890", Arrays.asList("Sports"), Arrays.asList(NotificationType.SMS));
        User user2 = new User(2, "Jane", "jane@example.com", "0987654321", Arrays.asList("Sports", "Finance"), Arrays.asList(NotificationType.Email));

        notificationRepository.saveAll(Arrays.asList(user1, user2));

        notificationService.sendNotifications(category, message);

        verify(notificationSender, times(1)).sendSMS(user1.getPhone(), message);

        verify(notificationSender, never()).sendEmail(user1.getEmail(), message);
        verify(notificationSender, never()).sendPushNotification(user1.getId(), message);
        verify(notificationSender, never()).sendEmail(user2.getEmail(), message);
        verify(notificationSender, never()).sendPushNotification(user2.getId(), message);
    }

    @Test
    public void testSendNotification_InvalidCategory_ThrowsException() {
        String invalidCategory = "Invalid";
        String message = "New match tomorrow!";

        assertThrows(InvalidCategoryException.class, () -> notificationService.sendNotifications(invalidCategory, message));

        verify(notificationSender, never()).sendSMS(anyString(), anyString());
        verify(notificationSender, never()).sendEmail(anyString(), anyString());
        verify(notificationSender, never()).sendPushNotification(anyInt(), anyString());
    }

    @Test
    public void testSendNotification_NoSubscribedUsers_ThrowsException() {
        String category = "Sports";
        String message = "New match tomorrow!";

        notificationService.sendNotifications(category, message);

        verify(notificationSender, never()).sendSMS(anyString(), anyString());
        verify(notificationSender, never()).sendEmail(anyString(), anyString());
        verify(notificationSender, never()).sendPushNotification(anyInt(), anyString());
    }
}