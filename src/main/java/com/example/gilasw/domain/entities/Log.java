package com.example.gilasw.domain.entities;

import com.example.gilasw.domain.enums.CategoryType;
import com.example.gilasw.domain.enums.NotificationType;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "logs")
public class Log {
    @Id
    private ObjectId id;
    private String user;
    private CategoryType category;
    private String message;
    private NotificationType notificationType;
    private LocalDateTime timestamp;

    public Log() { }

    public Log(final ObjectId id,
               final CategoryType category,
               final String message,
               final String user,
               final NotificationType notificationType,
               final LocalDateTime timestamp) {
        this.id = id;
        this.category = category;
        this.message = message;
        this.user = user;
        this.notificationType = notificationType;
        this.timestamp = timestamp;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public CategoryType getCategory() {
        return category;
    }

    public void setCategory(CategoryType category) {
        this.category = category;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}