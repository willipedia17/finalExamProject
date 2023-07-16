package com.example.finalexam_project;

public class NotificationModel {
    String title;
    String body;

    public NotificationModel() {
        // Default constructor required for Firebase
    }

    public NotificationModel(String title, String body) {
        this.title = title;
        this.body = body;
    }

}