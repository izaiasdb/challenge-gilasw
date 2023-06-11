package com.example.gilasw.notifications;

import org.springframework.stereotype.Component;

@Component
public class NotificationSender {

    public void sendSMS(String phoneNumber, String message) {
        System.out.println("Sending SMS to " + phoneNumber + ": " + message);
    }

    public void sendEmail(String emailAddress, String message) {
        System.out.println("Sending email to " + emailAddress + ": " + message);
    }

    public void sendPushNotification(Integer userId, String message) {
        System.out.println("Sending push notification to user " + userId + ": " + message);
    }
}