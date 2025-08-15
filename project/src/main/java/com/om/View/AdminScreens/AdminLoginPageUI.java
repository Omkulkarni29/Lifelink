package com.om.View.AdminScreens;

import com.om.Controller.AdminController;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class AdminLoginPageUI {

    public HBox AdminLoginScreen(com.om.Controller.ViewController controller, Runnable showSignupScreen) {
        // Left Side (Logo & Text)
        Label title = new Label("LifeLink");
        title.setFont(Font.font("Arial", 80));
        title.setStyle("-fx-text-fill: linear-gradient(to right, #ff0000, #ff7f00); -fx-font-weight: bold;");

        ImageView heartImage = new ImageView(new Image("asset/lifelinkui.png"));
        heartImage.setFitWidth(180);
        heartImage.setStyle("-fx-background-color: lightpink;");
        heartImage.setPreserveRatio(true);

        Label slogan = new Label("Every Link Saves a Life");
        slogan.setFont(Font.font("Arial", 20));
        slogan.setStyle("-fx-text-fill: linear-gradient(to right, #ff0000, #ff7f00); -fx-font-weight: bold;");

        VBox leftPane = new VBox(20, title, heartImage, slogan);
        leftPane.setStyle("-fx-background-color: lightpink;");
        leftPane.setAlignment(Pos.CENTER);
        leftPane.setPadding(new Insets(20));

        // Right Side (Login Form inside rectangle)
        Label createLabel = new Label("Admin Login");
        createLabel.setFont(Font.font("Arial", 45));
        createLabel.setStyle("-fx-text-fill: linear-gradient(to right, #ff0000, #ff7f00); -fx-font-weight: bold;");

        // Name field and label
        Text nameText = new Text("Name");
        nameText.setStyle("-fx-font-size: 18px; -fx-fill: linear-gradient(to right, #ff0000, #ff7f00);");

        TextField username = new TextField();
        username.setPromptText("UserName");
        username.setMaxWidth(Double.MAX_VALUE);
        username.setFocusTraversable(false);

        Hyperlink user = new Hyperlink("Register user");
        user.setStyle("-fx-text-fill: black; -fx-font-size: 16px");
        user.setOnAction(evt -> {
            showSignupScreen.run();
        });

        VBox nameBox = new VBox(10, nameText, username);
        nameBox.setMaxWidth(300);

        // Password field and label
        Text passText = new Text("Password");
        passText.setStyle("-fx-font-size: 18px; -fx-fill: linear-gradient(to right, #ff0000, #ff7f00);");

        PasswordField password = new PasswordField();
        password.setPromptText("Password");
        password.setMaxWidth(Double.MAX_VALUE);
        password.setFocusTraversable(false);

        VBox passBox = new VBox(10, passText, password);
        passBox.setMaxWidth(300);

        // Login Button
        Button loginButton = new Button("Login");
        loginButton.setStyle(
                "-fx-background-color: #ff1e00ff; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-size: 14px; " +
                        "-fx-pref-width: 200px;");
        Label resultLabel=new Label();
        loginButton.setOnAction(evt -> {
            String emailInput = username.getText().trim();
            String passwordInput = password.getText().trim();

            // [Authenticate user as before, get successful login response]

            // After successful Firebase login:
            String adminEmail = AdminController.getAdminEmail(); // Firestore call

            if (adminEmail != null && emailInput.equalsIgnoreCase(adminEmail)) {
                // Proceed to admin dashboard
                controller.showAdminDashboardScreen();
            } else {
                // Show error or restrict access
              showUnauthorizedAlert();


            }
        });

        // VBox to hold all login form content
        VBox contentBox = new VBox(20, createLabel, nameBox, passBox, loginButton, user);
        contentBox.setAlignment(Pos.CENTER);

        // Card-style background box
        VBox loginCard = new VBox(contentBox);
        loginCard.setAlignment(Pos.CENTER);
        loginCard.setPrefHeight(500);
        loginCard.setPrefWidth(200);
        loginCard.setPadding(new Insets(30));
        loginCard.setStyle(
                "-fx-background-color: #f8f8f8; " +
                        "-fx-background-radius: 20; " +
                        "-fx-border-radius: 20; " +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0.5, 0, 0);");

        // Right pane with background
        VBox formBox = new VBox(loginCard);
        formBox.setAlignment(Pos.CENTER);
        formBox.setPadding(new Insets(40, 100, 40, 40));
        formBox.setStyle("-fx-background-color: lightpink;");

        // Layout
        HBox hb = new HBox(leftPane, formBox);
        HBox.setHgrow(leftPane, Priority.ALWAYS);
        HBox.setHgrow(formBox, Priority.ALWAYS);
        leftPane.setMaxWidth(Double.MAX_VALUE);
        formBox.setMaxWidth(Double.MAX_VALUE);

        return hb;
    }
    private void showUnauthorizedAlert() {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Unauthorized Access");
    alert.setHeaderText(null);
    alert.setContentText("You are not authorized as admin.");
    alert.showAndWait();
}

}
