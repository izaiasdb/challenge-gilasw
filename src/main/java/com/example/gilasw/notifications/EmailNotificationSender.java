package com.example.gilasw.notifications;

public class EmailNotificationSender implements NotificationSender {
    @Override
    public void send(String identification, String message) {
        System.out.println("Sending email to " + identification + ": " + message);
    }
}
