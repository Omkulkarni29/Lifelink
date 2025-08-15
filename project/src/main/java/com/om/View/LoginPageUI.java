package com.om.View;

import com.om.Controller.AuthController;
import com.om.Controller.ViewController;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.json.JSONObject;

public class LoginPageUI {

    public HBox LoginScreen(ViewController controller, Runnable showSignupScreen) {
        AuthController auth = new AuthController();

        // Left Side (Logo & Text)
        Label title = new Label("LifeLink");
        title.setFont(Font.font("Arial", 80));
        title.setStyle("-fx-text-fill: linear-gradient(to right, #ff0000, #ff7f00); -fx-font-weight: bold;");

        ImageView heartImage = new ImageView(new Image("asset/Life.png"));
        heartImage.setFitWidth(180);
        heartImage.setPreserveRatio(true);

        Label slogan = new Label("Every Link Saves a Life");
        slogan.setFont(Font.font("Arial", 20));
        slogan.setStyle("-fx-text-fill: linear-gradient(to right, #ff0000, #ff7f00); -fx-font-weight: bold;");

        VBox leftPane = new VBox(20, title, heartImage, slogan);
        leftPane.setStyle("-fx-background-color: lightpink;");
        leftPane.setAlignment(Pos.CENTER);
        leftPane.setPadding(new Insets(20));

        // Form Section
        Label loginLabel = new Label("Login to your Account");
        loginLabel.setFont(Font.font("Arial", 45));
        loginLabel.setStyle("-fx-text-fill: linear-gradient(to right, #ff0000, #ff7f00); -fx-font-weight: bold;");

        Text email = new Text("Email");
        email.setStyle("-fx-font-size: 18px;");
        TextField emailField = new TextField();
        emailField.setFocusTraversable(false);
        emailField.setPromptText("Enter your email");

        Text password = new Text("Password");
        password.setStyle("-fx-font-size: 18px;");
        PasswordField passwordField = new PasswordField();
        passwordField.setFocusTraversable(false);
        passwordField.setPromptText("Enter your password");

        Label resultLabel = new Label();
        resultLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: red;");

        Button loginButton = new Button("Login");
        loginButton.setStyle(
            "-fx-background-color: #ff1e00ff; " +
            "-fx-text-fill: white; " +
            "-fx-font-size: 16px; " +
            "-fx-pref-width: 200px;"
        );

        // 🔀 Non-blocking Login on Background Thread
        loginButton.setOnAction(evt -> {
            String emailInput = emailField.getText().trim();
            String passwordInput = passwordField.getText().trim();

            if (emailInput.isEmpty() || passwordInput.isEmpty()) {
                resultLabel.setText("⚠ Please enter both email and password.");
                return;
            }

            loginButton.setDisable(true); // prevent double clicks
            resultLabel.setText("⏳ Logging in...");

            Task<Void> loginTask = new Task<>() {
                @Override
                protected Void call() throws Exception {
                    String response = auth.signIn(emailInput, passwordInput);

                    // Safely update UI
                    Platform.runLater(() -> {
                        loginButton.setDisable(false); // re-enable button

                        if (response != null && !response.contains("error")) {
                            try {
                                JSONObject json = new JSONObject(response);
                                String localId = json.optString("localId");

                                if (localId != null && !localId.isEmpty()) {
                                    ViewController.setCurrentUserId(localId);
                                    System.out.println("🔐 Logged in UID: " + localId);
                                    resultLabel.setStyle("-fx-text-fill: green;");
                                    resultLabel.setText("✅ Login successful.");
                                    controller.showDashboardScreen(); // go to dashboard
                                } else {
                                    resultLabel.setText("❌ Login failed: UID not found.");
                                }
                            } catch (Exception ex) {
                                ex.printStackTrace();
                                resultLabel.setText("❌ Login failed. Try again.");
                            }
                        } else {
                            resultLabel.setText("❌ Invalid email or password.");
                        }
                    });

                    return null;
                }
            };

            new Thread(loginTask).start(); //  run in new background thread
        });

        Hyperlink signupLink = new Hyperlink("Don't have an account? Sign up.");
        signupLink.setStyle("-fx-text-fill: black; -fx-font-size: 16px");
        signupLink.setOnAction(evt -> showSignupScreen.run());

        VBox formFields = new VBox(15, loginLabel, email, emailField, password, passwordField, loginButton, resultLabel, signupLink);
        formFields.setAlignment(Pos.TOP_LEFT);
        formFields.setPadding(new Insets(30));
        formFields.setPrefWidth(350);

        VBox loginCard = new VBox(formFields);
        loginCard.setAlignment(Pos.CENTER);
        loginCard.setPadding(new Insets(30));
        loginCard.setStyle(
            "-fx-background-color: #f8f8f8; " +
            "-fx-background-radius: 20; " +
            "-fx-border-radius: 20; " +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0.5, 0, 0);"
        );

        VBox rightPane = new VBox(loginCard);
        rightPane.setAlignment(Pos.CENTER);
        rightPane.setPadding(new Insets(40, 100, 40, 40));
        rightPane.setStyle("-fx-background-color: lightpink;");

        HBox root = new HBox(leftPane, rightPane);
        HBox.setHgrow(leftPane, Priority.ALWAYS);
        HBox.setHgrow(rightPane, Priority.ALWAYS);
        leftPane.setMaxWidth(Double.MAX_VALUE);
        rightPane.setMaxWidth(Double.MAX_VALUE);

        return root;
    }
}
