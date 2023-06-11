package com.example.gilasw.builders;

import com.example.gilasw.model.Notification;

public class NotificationBuilder {
        private Long id;
        private String category;
        private String message;
        private String user;

        public NotificationBuilder() {}

        public NotificationBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public NotificationBuilder category(String category) {
            this.category = category;
            return this;
        }

        public NotificationBuilder message(String message) {
            this.message = message;
            return this;
        }

        public NotificationBuilder user(String user) {
            this.user = user;
            return this;
        }

        public Notification build() {
            return new Notification(this.id, this.category, this.message, this.user);
        }
    }