package com.example.gilasw.notifications;

public class SmsNotificationSender implements NotificationSender {
    @Override
    public void send(String identification, String message) {
        System.out.println("Sending SMS to " + identification + ": " + message);
    }
}
