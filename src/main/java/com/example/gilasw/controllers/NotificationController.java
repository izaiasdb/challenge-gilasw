package com.example.gilasw.controllers;

import com.example.gilasw.services.NotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/{category}")
    public ResponseEntity<String> sendNotification(@PathVariable String category, @RequestBody String message) {
        if (notificationService.isValidCategory(category) && notificationService.isValidMessage(message)) {
            notificationService.sendNotifications(category, message);
            return new ResponseEntity<>("Notification sent successfully.", HttpStatus.OK);
        }
        return new ResponseEntity<>("Invalid category or message.", HttpStatus.BAD_REQUEST);
    }
}