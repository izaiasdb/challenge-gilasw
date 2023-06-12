package com.example.gilasw.factories;

import com.example.gilasw.domain.enums.NotificationType;
import com.example.gilasw.notifications.EmailNotificationSender;
import com.example.gilasw.notifications.NotificationSender;
import com.example.gilasw.notifications.PushNotificationSender;
import com.example.gilasw.notifications.SmsNotificationSender;

public class NotificationFactory {
    public static NotificationSender createNotification(NotificationType notificationType) {
        return switch (notificationType) {
            case SMS -> new SmsNotificationSender();
            case Email -> new EmailNotificationSender();
            case PushNotification -> new PushNotificationSender();
            default -> throw new IllegalArgumentException("Invalid notification channel: " + notificationType);
        };
    }
}