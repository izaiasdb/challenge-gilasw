package com.example.gilasw.domain;

import com.example.gilasw.domain.enums.CategoryType;
import com.example.gilasw.domain.enums.NotificationType;

import java.util.List;
import java.util.UUID;

public class User {

    private UUID id;
    private String name;
    private String email;
    private String phone;
    private List<CategoryType> subscribed;
    private List<NotificationType> channels;

    public User(final UUID id,
                final String name,
                final String email,
                final String phone,
                final List<CategoryType> subscribed,
                final List<NotificationType> channels) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.subscribed = subscribed;
        this.channels = channels;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public List<CategoryType> getSubscribed() {
        return subscribed;
    }

    public List<NotificationType> getChannels() {
        return channels;
    }

    public boolean isSubscribedTo(String category) {
        return subscribed.contains(category);
    }
}

