package com.example.gilasw.helpers;

import java.util.Arrays;
import java.util.List;

public class NotificationValidation {
    public static boolean isValidCategory(final String category) {
        List<String> validCategories = Arrays.asList("Sports", "Finance", "Films");
        return validCategories.contains(category);
    }

    public static boolean isValidMessage(final String message) {
        return message != null && !message.isEmpty();
    }
}
