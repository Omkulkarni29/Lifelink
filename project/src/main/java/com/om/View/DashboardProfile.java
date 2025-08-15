package com.om.View;

import com.om.Controller.ProfileController;
import com.om.Controller.ViewController;
import com.om.model.UserProfileModel;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.concurrent.CompletableFuture;

public class DashboardProfile {

    public BorderPane DashboardProfileScreen(ViewController controller, Runnable showSignupScreen, Runnable showdashboardScreen) {

        // --- Header Section (No changes) ---
        // ... your header code remains the same ...
        Label bell = new Label("🔔");
        bell.setFont(Font.font("Arial", 36));
        Label user = new Label("👤");
        user.setFont(Font.font("Arial", 36));
        Hyperlink backArrow = new Hyperlink("<-");
        backArrow.setFont(Font.font(20));
        backArrow.setOnAction(e -> showdashboardScreen.run());
        Text heading = new Text("Dashboard");
        heading.setFont(Font.font(28));
        HBox headerLeft = new HBox(10, backArrow, heading);
        headerLeft.setAlignment(Pos.CENTER_LEFT);
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        HBox header = new HBox(10, headerLeft, spacer, bell, user);
        header.setPadding(new Insets(15));
        header.setStyle("-fx-background-color: white;");

        // --- Redesigned Center Profile Form ---
        Text title = new Text("User Profile");
        title.setFont(Font.font(30));

        // Create TextFields for all user data
        TextField fullNameField = createStyledTextField("Full Name");
        TextField usernameField = createStyledTextField("Username");
        TextField mobileField = createStyledTextField("Mobile Number");
       // TextField emailField = createStyledTextField("Email");
        TextField dobField = createStyledTextField("Date of Birth");
        TextField genderField = createStyledTextField("Gender");
        TextField bloodGroupField = createStyledTextField("Blood Group");

        // Make non-editable fields look different
       // makeReadOnly(emailField);
        makeReadOnly(dobField);
        makeReadOnly(genderField);
        makeReadOnly(bloodGroupField);
        
        // Arrange fields in a VBox for clean alignment
        VBox form = new VBox(15,
            createFormRow("Full Name:", fullNameField),
            createFormRow("Username:", usernameField),
            createFormRow("Mobile Number:", mobileField),
           // createFormRow("Email:", emailField),
            createFormRow("Date of Birth:", dobField),
            createFormRow("Gender:", genderField),
            createFormRow("Blood Group:", bloodGroupField)
        );
        form.setAlignment(Pos.CENTER);
        form.setPadding(new Insets(20));
        form.setMaxWidth(600); // Control form width

        // Buttons
        Button saveButton = new Button("Save Changes");
        saveButton.setFont(Font.font(16));
        saveButton.setStyle("-fx-background-color: #ff1e00ff; -fx-text-fill: white;");
        saveButton.setPrefWidth(250);

        VBox centerContent = new VBox(30, title, form, saveButton);
        centerContent.setAlignment(Pos.TOP_CENTER);
        centerContent.setPadding(new Insets(40, 20, 20, 20));

        // --- Main Layout ---
        BorderPane root = new BorderPane();
        root.setTop(header);
        root.setCenter(centerContent);
        root.setStyle("-fx-background-color: #F4C7CC;");

        // --- Logic to Fetch and Save Data ---
        String currentUserId = ViewController.getCurrentUserId();
        if (currentUserId != null) {
            CompletableFuture.supplyAsync(() -> ProfileController.getUserProfile(currentUserId))
                .thenAccept(userProfile -> {
                    if (userProfile != null) {
                        Platform.runLater(() -> {
                            fullNameField.setText(userProfile.getName());
                            usernameField.setText(userProfile.getUsername());
                            mobileField.setText(userProfile.getMobileNumber());
                           // emailField.setText(userProfile.getEmail());
                            dobField.setText(userProfile.getDob());
                            genderField.setText(userProfile.getGender());
                            bloodGroupField.setText(userProfile.getBloodGroup());
                        });
                    }
                });
        }

        saveButton.setOnAction(e -> {
            String updatedName = fullNameField.getText().trim();
            String updatedUsername = usernameField.getText().trim();
            String updatedMobile = mobileField.getText().trim();

            if (currentUserId != null && !updatedName.isEmpty()) {
                saveButton.setText("Saving...");
                saveButton.setDisable(true);
                ProfileController.updateUserProfile(currentUserId, updatedName, updatedUsername, updatedMobile)
                    .addListener(() -> Platform.runLater(() -> {
                        showAlert("Success", "Profile updated successfully!");
                        saveButton.setText("Save Changes");
                        saveButton.setDisable(false);
                    }), Platform::runLater);
            } else {
                showAlert("Error", "Full Name cannot be empty.");
            }
        });

        return root;
    }

    // Helper method to create a styled form row
    private HBox createFormRow(String labelText, TextField textField) {
        Text text = new Text(labelText);
        text.setFont(Font.font(18));
        HBox row = new HBox(20, text, textField);
        row.setAlignment(Pos.CENTER_RIGHT);
        // Ensure text and field are aligned nicely
        text.setWrappingWidth(150);
        HBox.setHgrow(textField, Priority.ALWAYS);
        return row;
    }
    
    // Helper method to create a consistent text field
    private TextField createStyledTextField(String prompt) {
        TextField textField = new TextField();
        textField.setPromptText(prompt);
        textField.setMaxWidth(350);
        return textField;
    }

    // Helper method to style read-only fields
    private void makeReadOnly(TextField field) {
        field.setEditable(false);
        field.setStyle("-fx-background-color: #e9ecef; -fx-text-fill: #495057;"); // Grayed out style
    }
    
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
