package com.example.gilasw.repositories;

import com.example.gilasw.model.Notification;
import com.example.gilasw.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<User> findBySubscribed(String category);
    List<User> saveAll(List<User> users);
}