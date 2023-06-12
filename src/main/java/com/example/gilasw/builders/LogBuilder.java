package com.example.gilasw.builders;

import com.example.gilasw.domain.enums.CategoryType;
import com.example.gilasw.domain.entities.Log;
import com.example.gilasw.domain.enums.NotificationType;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;

public class LogBuilder {
    private ObjectId id;
    private CategoryType category;
    private String message;
    private String user;
    private NotificationType notificationType;
    private LocalDateTime timestamp;

    public LogBuilder() {}

    public LogBuilder id(ObjectId id) {
        this.id = id;
        return this;
    }

    public LogBuilder category(CategoryType category) {
        this.category = category;
        return this;
    }

    public LogBuilder message(String message) {
        this.message = message;
        return this;
    }

    public LogBuilder user(String user) {
        this.user = user;
        return this;
    }

    public LogBuilder notificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
        return this;
    }

    public LogBuilder timestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public Log build() {
        return new Log(this.id, this.category, this.message, this.user, this.notificationType, this.timestamp);
    }
}