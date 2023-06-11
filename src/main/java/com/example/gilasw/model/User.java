package com.example.gilasw.model;

import java.util.List;

public class User {

    private Integer id;
    private String name;
    private String email;
    private String phone;
    private List<String> subscribed;
    private List<NotificationType> channels;

    public User(Integer id, String name, String email, String phone, List<String> subscribed, List<NotificationType> channels) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.subscribed = subscribed;
        this.channels = channels;
    }

    public Integer getId() {
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

    public List<String> getSubscribed() {
        return subscribed;
    }

    public List<NotificationType> getChannels() {
        return channels;
    }

    public boolean isSubscribedTo(String category) {
        return subscribed.contains(category);
    }
}

