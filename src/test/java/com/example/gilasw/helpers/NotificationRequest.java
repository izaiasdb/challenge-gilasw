package com.example.gilasw.helpers;

public class NotificationRequest {
    private String category;
    private String message;

    public NotificationRequest(final String category, final String message) {
        this.category = category;
        this.message = message;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
