package com.example.gilasw.domain;

import com.example.gilasw.domain.enums.CategoryType;
import com.example.gilasw.domain.enums.NotificationType;
import com.example.gilasw.domain.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class UserMock {
    public static List<User> getUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User(UUID.randomUUID(), "John Doe", "john@example.com", "1234567890",
                Arrays.asList(CategoryType.Sports, CategoryType.Films),
                Arrays.asList(NotificationType.SMS, NotificationType.Email)));
        users.add(new User(UUID.randomUUID(), "Jane Smith", "jane@example.com", "jane@example.com",
                Arrays.asList(CategoryType.Finance, CategoryType.Films),
                Arrays.asList(NotificationType.Email, NotificationType.PushNotification)));
        users.add(new User(UUID.randomUUID(), "Bob Johnson", "bob@example.com", "5555555555",
                Arrays.asList(CategoryType.Sports), Arrays.asList(NotificationType.SMS)));
        return users;
    }
}
