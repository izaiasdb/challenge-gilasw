package com.example.gilasw.services;

import com.example.gilasw.domain.User;
import com.example.gilasw.domain.entities.Log;
import com.example.gilasw.domain.enums.CategoryType;
import com.example.gilasw.domain.UserMock;
import com.example.gilasw.repositories.LogRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class NotificationServiceTest {

    @InjectMocks
    private NotificationService notificationService;

    @Mock
    private LogRepository logRepository;

    @Mock
    private UserExternalService userExternalService;

    @Test
    public void sendNotification_ValidCategoryAndMessage_Success() {
        // Arrange
        List<User> subscribedUsers = UserMock.getUsers();

        Mockito.when(userExternalService.getByCategory(CategoryType.Sports)).thenReturn(subscribedUsers);

        // Act
        notificationService.sendNotifications(CategoryType.Sports, "New sports update");

        // Assert
        Mockito.verify(logRepository, Mockito.times(5)).save(Mockito.any(Log.class));
    }

}