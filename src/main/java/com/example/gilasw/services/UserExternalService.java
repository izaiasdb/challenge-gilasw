package com.example.gilasw.services;

import com.example.gilasw.domain.User;
import com.example.gilasw.domain.enums.CategoryType;
import com.example.gilasw.domain.UserMock;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserExternalService {
    public List<User> getByCategory(CategoryType category) {
        return UserMock.getUsers().stream()
                .filter(user -> user.getSubscribed().contains(category))
                .collect(Collectors.toList());
    }

}
