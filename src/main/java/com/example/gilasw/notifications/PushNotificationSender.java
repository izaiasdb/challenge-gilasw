package com.example.gilasw.notifications;

public class PushNotificationSender implements NotificationSender {
    @Override
    public void send(String identification, String message) {
        System.out.println("Sending push notification to user " + identification + ": " + message);
    }
}
