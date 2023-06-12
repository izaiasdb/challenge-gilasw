package com.example.gilasw.controllers;

import com.example.gilasw.helpers.NotificationValidation;
import com.example.gilasw.domain.enums.CategoryType;
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
        if (NotificationValidation.isValidCategory(category) && NotificationValidation.isValidMessage(message)) {
            notificationService.sendNotifications(CategoryType.valueOf(category), message);
            return new ResponseEntity<>("Notification sent successfully.", HttpStatus.OK);
        }
        return new ResponseEntity<>("Invalid category or message.", HttpStatus.BAD_REQUEST);
    }
}