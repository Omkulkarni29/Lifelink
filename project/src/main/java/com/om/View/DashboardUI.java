package com.om.View;

import java.io.InputStream;

import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class DashboardUI {

    public BorderPane DashboardScreen(com.om.Controller.ViewController controller, Runnable showSignupScreen,
            Runnable DonorUiScreen) {
        BorderPane root = new BorderPane();

        // Top bar
        HBox topBar = new HBox();
        topBar.setStyle("-fx-background-color: white;");
        topBar.setPadding(new Insets(10, 20, 10, 20));
        topBar.setAlignment(Pos.CENTER_LEFT);
        topBar.setSpacing(10);

        Text title = new Text("➤ Dashboard");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        topBar.getChildren().add(title);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Label bell = new Label("🔔");
        bell.setFont(Font.font("Arial", 26));
        bell.setOnMouseClicked(e->{
           // controller.showNotificationScreen();
        });
        Label user = new Label("👤");
        user.setFont(Font.font("Arial", 26));
        user.setOnMouseClicked(e -> {
            controller.showDashboardProfileScreen();
        });

        topBar.getChildren().addAll(spacer,  user);
        root.setTop(topBar);

        // Center content
        VBox centerBox = new VBox(30);
        centerBox.setAlignment(Pos.TOP_CENTER);
        centerBox.setPadding(new Insets(30));
        centerBox.setStyle("-fx-background-color: lightpink;");

        // Donor and Receiver Icons
        HBox iconBox = new HBox(80);
        iconBox.setAlignment(Pos.CENTER);

        VBox donorBox = createIconCard("asset/d.png", "");
        VBox receiverBox = createIconCard("asset/r.png", "");
        donorBox.setOnMouseClicked(e -> {
            DonorUiScreen.run();
        });
        receiverBox.setOnMouseClicked(e -> {
            controller.showReceiverRegistrationScreen();
        });

        iconBox.getChildren().addAll(donorBox, receiverBox);

        // Stats Cards
        HBox statsBox = new HBox(30);
        statsBox.setAlignment(Pos.CENTER);

        statsBox.getChildren().addAll(
                createStatCard("Total Donor Count", "125", Color.RED, "asset/donor.png"),
                createStatCard("Total Organ", "98", Color.ORANGE, "asset/donor2.png"),
                createStatCard("Receiver Count", "87", Color.RED, "asset/receiver.png"),
                createStatCard("Matching Count", "21", Color.ORANGE, "asset/automatch.png"));

        centerBox.getChildren().addAll(iconBox, statsBox);
        root.setCenter(centerBox);

        Text profileText = new Text("👤" + " Profile");
        profileText.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        profileText.setFill(Color.web("#0c0603ff"));
        profileText.setOnMouseClicked(e -> {
            controller.showDashboardProfileScreen();
        });

        Text about = new Text("ℹ️" + " About");
        about.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        about.setFill(Color.web("#050301ff"));
        about.setOnMouseClicked(e -> {
            controller.showAboutpageScreen();
        });

        Text help = new Text("❓" + " Help");
        help.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        help.setFill(Color.web("#090401ff"));
        help.setOnMouseClicked(e -> {
            controller.showHelppageScreen();
        });

        Text Logout = new Text("📴" + " Logout ");
        Logout.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        Logout.setFill(Color.web("#070402ff"));
        Logout.setOnMouseClicked(e -> {
            showSignupScreen.run();
        });

        // Sidebar
        VBox sidebar = new VBox(25, profileText, about, help, Logout);
        sidebar.setPadding(new Insets(50, 30, 50, 20));
        sidebar.setAlignment(Pos.TOP_LEFT);
        sidebar.setStyle("-fx-background-color: #fcd9e1;");
        sidebar.setPrefWidth(200);

        // sidebar.getChildren().addAll(
        // createSidebarOption("👤", "Profile"),

        // createSidebarOption("ℹ️", "About"),
        // createSidebarOption("❓", "Help"),
        // createSidebarOption("📴", "Logout")
        // );

        root.setRight(sidebar);

        return root;
    }

    private VBox createIconCard(String imagePath, String labelText) {
        VBox box = new VBox(10);
        box.setAlignment(Pos.CENTER);

        ImageView icon = new ImageView(new Image(imagePath));
        icon.setFitHeight(180);
        icon.setFitWidth(180);

        Text label = new Text(labelText);
        label.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        box.getChildren().addAll(icon, label);
        return box;
    }

    private VBox createStatCard(String labelText, String valueText, Color color, String imagePath) {
        VBox card = new VBox(8);
        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(15));
        card.setPrefSize(160, 160);
        card.setStyle(
                "-fx-border-color: " + toHex(color) + ";" +
                        "-fx-border-radius: 12;" +
                        "-fx-background-color: #fff3e0;" +
                        "-fx-background-radius: 12;");

        // Icon
        InputStream is = getClass().getClassLoader().getResourceAsStream(imagePath);
        if (is == null) {
            System.err.println("Image not found at path: " + imagePath);
        }
        ImageView icon = new ImageView(new Image(is));

        icon.setFitHeight(40);
        icon.setFitWidth(40);

        // Label
        Label label = new Label(labelText);
        label.setTextFill(color);
        label.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        // Value
        Text value = new Text(valueText);
        value.setFill(color);
        value.setFont(Font.font("Arial", FontWeight.BOLD, 26));

        card.getChildren().addAll(icon, label, value);
        return card;
    }

    private HBox createSidebarOption(String icon, String text) {
        HBox row = new HBox(10);
        row.setAlignment(Pos.CENTER_LEFT);

        Label iconLabel = new Label(icon);
        iconLabel.setFont(Font.font(20));

        Label textLabel = new Label(text);
        textLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        row.getChildren().addAll(iconLabel, textLabel);
        return row;
    }

    private String toHex(Color color) {
        return String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }

}
