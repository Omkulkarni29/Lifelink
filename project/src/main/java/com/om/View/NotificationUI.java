package com.om.View;

import com.om.Controller.NotificationController;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.List;
import java.util.Map;

public class NotificationUI {

    private final VBox notificationList = new VBox(15);

    public VBox NotificationsScreen(com.om.Controller.ViewController controller) {
        VBox root = new VBox(20);
        root.setPadding(new Insets(20));
        root.setStyle(
            "-fx-background-color: linear-gradient(to right, #fbc2eb, #a6c1ee);" +
            "-fx-border-color: green; -fx-border-width: 3px;"
        );

        Button backArrow = new Button("←");
        backArrow.setPrefWidth(40);
        backArrow.setPrefHeight(30);
        backArrow.setOnMouseClicked(e -> {
            controller.showAdminDashboardScreen();
        });

        Label title = new Label("🔔 Admin Notifications");
        title.setFont(Font.font("Arial", 28));
        title.setTextFill(Color.DARKSLATEBLUE);

        HBox header = new HBox(10, backArrow, title);
        header.setAlignment(Pos.CENTER_LEFT);

        notificationList.setPadding(new Insets(10));
        notificationList.setStyle(
            "-fx-background-color: #222222; " +
            "-fx-border-color: #00FF00; -fx-border-width: 2px;"
        );
        notificationList.setMinHeight(600);
        notificationList.setMinWidth(560);

        ScrollPane scrollPane = new ScrollPane(notificationList);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: transparent; -fx-border-color: transparent;");
        scrollPane.setPrefViewportHeight(600);
        scrollPane.setPrefViewportWidth(580);

        root.getChildren().addAll(header, scrollPane);

        // Add a hardcoded notification card with bright colors
        addNotificationCard("Test User Hardcoded", "testuser@example.com");

        // Load real notifications from Firestore
        loadNotifications();

        return root;
    }

    private void loadNotifications() {
        new Thread(() -> {
            List<Map<String, Object>> notifications = NotificationController.getNotifications();
            Platform.runLater(() -> {
                notificationList.getChildren().clear();

                if (notifications.isEmpty()) {
                    Label noUsers = new Label("No registered users.");
                    noUsers.setFont(Font.font(18));
                    noUsers.setTextFill(Color.WHITE);
                    notificationList.getChildren().add(noUsers);
                } else {
                    for (Map<String, Object> user : notifications) {
                        String name = (String) user.getOrDefault("firstName", "No Name");
                        String email = (String) user.getOrDefault("email", "No Email");
                        addNotificationCard(name, email);
                    }
                }
            });
        }).start();
    }

    public void addNotificationCard(String name, String email) {
        VBox card = new VBox(5);
        card.setPadding(new Insets(10));
        card.setStyle(
            "-fx-background-color: #444444; -fx-background-radius: 8; " +
            "-fx-border-color: #00FFFF; -fx-border-radius: 8; -fx-border-width: 2px;"
        );

        Label nameLabel = new Label("👤 " + name);
        nameLabel.setFont(Font.font("Verdana", 24));
        nameLabel.setTextFill(Color.YELLOW);

        Label emailLabel = new Label("📧 " + email);
        emailLabel.setFont(Font.font("Verdana", 20));
        emailLabel.setTextFill(Color.CYAN);

        card.getChildren().addAll(nameLabel, emailLabel);
        notificationList.getChildren().add(card);

        System.out.println("Added notification card: " + name + ", " + email);
    }
}
