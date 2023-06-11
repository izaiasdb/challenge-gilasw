package com.example.gilasw.services;

import com.example.gilasw.builders.NotificationBuilder;
import com.example.gilasw.model.NotificationType;
import com.example.gilasw.model.User;
import com.example.gilasw.notifications.NotificationSender;
import com.example.gilasw.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class NotificationService {

    private final List<User> users;
    private final NotificationRepository notificationRepository;
    private final NotificationSender notificationSender;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository, NotificationSender notificationSender) {
        this.notificationRepository = notificationRepository;
        this.notificationSender = notificationSender;

        users = new ArrayList<>();
        users.add(new User(1, "John Doe", "john@example.com", "1234567890", Arrays.asList("Sports", "Films"), Arrays.asList(NotificationType.SMS, NotificationType.Email)));
        users.add(new User(2, "Jane Smith", "jane@example.com", "jane@example.com", Arrays.asList("Finance", "Films"), Arrays.asList(NotificationType.Email, NotificationType.PushNotification)));
        users.add(new User(3, "Bob Johnson", "bob@example.com", "5555555555", Arrays.asList("Sports"), Arrays.asList(NotificationType.SMS)));
    }

    public boolean isValidCategory(final String category) {
        List<String> validCategories = Arrays.asList("Sports", "Finance", "Films");
        return validCategories.contains(category);
    }

    public boolean isValidMessage(final String message) {
        return message != null && !message.isEmpty();
    }

    public void sendNotifications(final String category, final String message) {
        List<User> subscribedUsers = notificationRepository.findBySubscribed(category);

        for (User user : subscribedUsers) {
            sendNotificationToUser(category, user, message);
        }
    }

    private void sendNotificationToUser(final String category, final User user, final String message) {
        switch (category) {
            case "SMS":
                notificationSender.sendSMS(user.getPhone(), message);
                break;
            case "Email":
                notificationSender.sendEmail(user.getEmail(), message);
                break;
            case "PushNotification":
                notificationSender.sendPushNotification(user.getId(), message);
                break;
            default:
                break;
        }

        notificationRepository.save(new NotificationBuilder()
                .category(category)
                .message(message)
                .user(user.getName())
                .build());
    }
}