package com.example.gilasw.services;

import com.example.gilasw.builders.LogBuilder;
import com.example.gilasw.factories.NotificationFactory;
import com.example.gilasw.domain.enums.CategoryType;
import com.example.gilasw.domain.enums.NotificationType;
import com.example.gilasw.domain.User;
import com.example.gilasw.notifications.NotificationSender;
import com.example.gilasw.repositories.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService {

    private final LogRepository logRepository;

    private final UserExternalService userExternalService;

    @Autowired
    public NotificationService(LogRepository logRepository,
                               UserExternalService userExternalService) {
        this.logRepository = logRepository;
        this.userExternalService = userExternalService;
    }

    public void sendNotifications(final CategoryType category, final String message) {
        List<User> subscribedUsers = userExternalService.getByCategory(category);

        for (User user : subscribedUsers) {
            sendNotificationToUser(category, user, message);
        }
    }

    private void sendNotificationToUser(final CategoryType category, final User user, final String message) {
        for (NotificationType notificationType : user.getChannels()) {
            NotificationSender notificationSender = NotificationFactory.createNotification(notificationType);

            switch (notificationType) {
                case SMS -> notificationSender.send(user.getPhone(), message);
                case Email -> notificationSender.send(user.getEmail(), message);
                case PushNotification -> notificationSender.send(user.getId().toString(), message);
                default -> { }
            }

            saveOnDataBase(category, user, message, notificationType);
        }
    }

    private void saveOnDataBase(final CategoryType category,
                                final User user,
                                final String message,
                                final NotificationType notificationType) {
        logRepository.save(new LogBuilder()
                .category(category)
                .message(message)
                .user(user.getName())
                .notificationType(notificationType)
                .timestamp(LocalDateTime.now())
                .build());
    }
}