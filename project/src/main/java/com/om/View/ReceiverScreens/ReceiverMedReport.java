package com.om.View.ReceiverScreens;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import com.om.Controller.ReceiverReportcontroller;
import com.om.Controller.ViewController;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class ReceiverMedReport {
     private File selectedFile;

    public VBox ReceiverMedReportScreen(ViewController controller, Runnable showSignupScreen, Runnable showReceiverRegistrationScreen, Runnable showDashboardScreen,Runnable showReceiverConsentformScreen) {

         // holder for selected file

        // Top bar setup
        Button backArrow = new Button("🏠");
        backArrow.setFont(Font.font("Georgia", 30));
        backArrow.setStyle("-fx-background-color: transparent; -fx-text-fill: black;");
        backArrow.setOnAction(e -> showDashboardScreen.run());

        Text t1 = new Text("Dashboard");
        t1.setStyle("-fx-font-family: 'Georgia'; -fx-font-size: 25px;");
        HBox titleBox = new HBox(10, backArrow, t1);
        titleBox.setAlignment(Pos.CENTER_LEFT);

        ImageView profile = new ImageView(new Image("asset/profile.jpg"));
        profile.setFitWidth(35);
        profile.setFitHeight(35);

        Text notification = new Text("🔔");
        notification.setStyle("-fx-font-family: 'Georgia'; -fx-font-size: 35px;");

        HBox iconBox = new HBox(20,  profile);
        iconBox.setAlignment(Pos.CENTER_RIGHT);

        HBox topBar = new HBox(1200, titleBox, iconBox);
        //topBar.setPadding(new Insets(, 20, 0, 40));
        topBar.setStyle("-fx-background-color: white;");

        // Header & navigation
        Text title = new Text("Medical Report");
        title.setFont(Font.font("Georgia", 40));

        Hyperlink organs = new Hyperlink("Select Organ");
        organs.setTextFill(Color.BLACK);
        organs.setOnAction(e -> showReceiverRegistrationScreen.run());

        Hyperlink medHistory = new Hyperlink("Medical History");
        medHistory.setTextFill(Color.BLACK);

        Hyperlink consentForm = new Hyperlink("Consent Form");
        consentForm.setTextFill(Color.BLACK);
        consentForm.setOnAction(e -> controller.showReceiverConsentformScreen());

        for (Hyperlink h : new Hyperlink[]{organs, medHistory, consentForm}) {
            h.setStyle("-fx-font-family: 'Georgia'; -fx-font-size: 20px;");
        }

        HBox navLinks = new HBox(50, organs, medHistory, consentForm);
        navLinks.setStyle("-fx-background-color: lightpink");
        navLinks.setAlignment(Pos.CENTER);

        Line separator = new Line(0, 0, 1270, 0);
        separator.setStroke(Color.BLACK);
        separator.setStrokeWidth(2);

        VBox headerBar = new VBox(10, title, navLinks, separator);
        headerBar.setStyle("-fx-background-color: lightpink");
        headerBar.setAlignment(Pos.CENTER);
        headerBar.setPadding(new Insets(40, 0, 40, 0));

        // Instructions
        Text instruction = new Text("Please Select Severity Level:");
        instruction.setStyle("-fx-font-family: 'Georgia'; -fx-font-size: 25px;");

        // File Chooser
        Button openImageBtn = new Button("Choose File");
        openImageBtn.setStyle("-fx-background-color: skyblue;-fx-text-fill: white;-fx-font-size: 16px;-fx-font-family: 'Georgia';-fx-background-radius: 8;-fx-padding: 8 16 8 16;");
        openImageBtn.setTooltip(new Tooltip("Choose medical report (PDF/image)"));
         Label fileLabel = new Label("No file selected.");
        fileLabel.setStyle("-fx-font-size: 14px; -fx-font-family: 'Georgia';");

        openImageBtn.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Pick Medical Document");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("PDF Files", "*.pdf"),
                    new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));

            // This is the missing line that actually opens the file dialog
            File file = fileChooser.showOpenDialog(null);

            // Now, handle the file that the user selected
            if (file != null) {
                // Store the selected file in the class variable so the "Submit" button can use
                // it
                this.selectedFile = file;
                fileLabel.setText("Selected: " + file.getName());
                System.out.println("File selected for upload: " + file.getAbsolutePath());
            } else {
                // This runs if the user closes the dialog without choosing a file
                this.selectedFile = null;
                fileLabel.setText("No file selected.");
            }
        });
        // Blood Group
        Text bloodgroup = new Text("Select Blood Group:");
        bloodgroup.setStyle("-fx-font-family: 'Georgia'; -fx-font-size: 25px;");

        ToggleGroup bloodGroupToggle = new ToggleGroup();
        String[] bloodGroups = {"A+", "A−", "B+", "B−", "AB+", "AB−", "O+", "O−"};
        HBox bloodGroupBox = new HBox(15);
        bloodGroupBox.setPadding(new Insets(10));
        bloodGroupBox.setAlignment(Pos.CENTER);

        for (String group : bloodGroups) {
            RadioButton rb = new RadioButton(group);
            rb.setToggleGroup(bloodGroupToggle);
            rb.setStyle("-fx-font-family: 'Georgia'; -fx-font-size: 14px;");
            bloodGroupBox.getChildren().add(rb);
        }

        VBox bloodGroupContainer = new VBox(bloodgroup, bloodGroupBox);
        bloodGroupContainer.setPadding(new Insets(30));
        bloodGroupContainer.setAlignment(Pos.CENTER_LEFT);
        bloodGroupContainer.setStyle("-fx-border-color: #130f0cff; -fx-border-width: 1px; -fx-border-radius: 8; -fx-background-radius: 8;");

        // Severity
        Text severityLabel = new Text("Severity Level:");
        severityLabel.setStyle("-fx-font-family: 'Georgia'; -fx-font-size: 16px;");

        ToggleGroup severityGroup = new ToggleGroup();
        RadioButton mild = new RadioButton("Mild");
        RadioButton moderate = new RadioButton("Moderate");
        RadioButton critical = new RadioButton("Critical");

        mild.setToggleGroup(severityGroup);
        moderate.setToggleGroup(severityGroup);
        critical.setToggleGroup(severityGroup);

        HBox severityBox = new HBox(15, mild, moderate, critical);
        severityBox.setAlignment(Pos.CENTER);
        HBox severitySection = new HBox(15, severityLabel, severityBox);
        severitySection.setAlignment(Pos.CENTER);

        // Submit Button
        Button submitButton = new Button("Submit");
        submitButton.setStyle("-fx-background-color: #FF6B00; -fx-text-fill: white; -fx-font-size: 18px;");
        submitButton.setOnAction(e -> {
            if (this.selectedFile == null) {
                /* ... validation ... */ return;
            }
            String currentUserId = ViewController.getCurrentUserId();
            if (currentUserId == null) {
                /* ... validation ... */ return;
            }

            submitButton.setDisable(true);
            submitButton.setText("Uploading...");

            CompletableFuture.runAsync(() -> {
                // ✅ USING THE SIMPLER, MORE RELIABLE ADMIN SDK
             
                String downloadUrl = uploadFileWithRest(this.selectedFile, currentUserId);

                Platform.runLater(() -> {
                    if (downloadUrl != null) {
                        showAlert("Success", "File uploaded successfully!");
                        System.out.println("File URL: " + downloadUrl);
                        // TODO: Save URL to Firestore
                        showReceiverConsentformScreen.run();
                    } else {
                        showAlert("Upload Failed", "An error occurred during upload.");
                    }
                    submitButton.setDisable(false);
                    submitButton.setText("Submit");
                });
            });
        });

        VBox fullForm = new VBox(40, instruction, bloodGroupContainer, severitySection, openImageBtn, submitButton);
        fullForm.setPrefHeight(790);
        fullForm.setPadding(new Insets(40));
        fullForm.setAlignment(Pos.TOP_CENTER);
        fullForm.setBackground(new Background(new BackgroundFill(Color.web("lightpink"), CornerRadii.EMPTY, Insets.EMPTY)));

        ScrollPane scrollPane = new ScrollPane(fullForm);
        scrollPane.setFitToWidth(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setBackground(new Background(new BackgroundFill(Color.web("lightpink"), CornerRadii.EMPTY, Insets.EMPTY)));

        VBox content = new VBox(headerBar, scrollPane);
        content.setAlignment(Pos.TOP_CENTER);
        VBox root = new VBox(topBar, content);
        root.setStyle("-fx-background-color: #f8fbfcff;");

        return root;
    }
    
    private String uploadFileWithRest(File file, String uid) {
        // Use the correct bucket name format
        final String BUCKET_ID = "javafxfirebase-eb545.firebasestorage.app";
      
        String storagePath = "ReceiverMedicalReports/" + uid + "_" + UUID.randomUUID() + "_"
                + file.getName().replace(" ", "_");

        try {
            // Dynamically determine the content type (MIME type)
            String contentType = Files.probeContentType(file.toPath());
            if (contentType == null) {
                contentType = "application/octet-stream"; // A generic fallback
            }

            // Construct the REST API URL for upload
            String urlString = "https://firebasestorage.googleapis.com/v0/b/" + BUCKET_ID + "/o?uploadType=media&name="
                    + URLEncoder.encode(storagePath, "UTF-8");
            URL url = new URL(urlString);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", contentType);

            // Stream the file content to the request body
            try (OutputStream os = conn.getOutputStream(); FileInputStream fis = new FileInputStream(file)) {
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = fis.read(buffer)) != -1) {
                    os.write(buffer, 0, bytesRead);
                }
            }

            int responseCode = conn.getResponseCode();
            System.out.println("Upload response code: " + responseCode);
            conn.disconnect();

            // Check if the upload was successful (HTTP 200 OK)
            if (responseCode == 200) {
                // Construct and return the public download URL
                return "https://firebasestorage.googleapis.com/v0/b/" + BUCKET_ID + "/o/"
                        + URLEncoder.encode(storagePath, "UTF-8") + "?alt=media";
            } else {
                return null; // Upload failed
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

}
