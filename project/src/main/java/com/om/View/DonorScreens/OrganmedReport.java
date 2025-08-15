package com.om.View.DonorScreens;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import com.om.Controller.ViewController;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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

public class OrganmedReport {
    private File selectedFile;

    public VBox OrganmedReportScreen(com.om.Controller.ViewController controller, Runnable showSignupScreen,
            Runnable showDonorUiScreen, Runnable showDashboardScreen,Runnable showConsentform) {

        // Top Bar
        Button backArrow = new Button("🏠");
        backArrow.setFont(Font.font("Georgia", 26));
        backArrow.setStyle("-fx-background-color: transparent; -fx-text-fill: black;");
        backArrow.setOnAction(e -> showDashboardScreen.run());

        Text t1 = new Text("DashBoard");
        t1.setStyle("-fx-font-family: 'Georgia'; -fx-font-size: 25px;");

        HBox titleBox = new HBox(10, backArrow, t1);
        titleBox.setAlignment(Pos.CENTER_LEFT);

        ImageView profile = new ImageView(new Image("asset\\profile.jpg"));
        profile.setFitWidth(35);
        profile.setFitHeight(35);

        Text notification = new Text("🔔");
        notification.setStyle("-fx-font-family: 'Georgia'; -fx-font-size: 35px;");

        HBox topBar = new HBox(1170, titleBox, new HBox(20,  profile));
        topBar.setPadding(new Insets(5, 20, 0, 40));
        topBar.setStyle("-fx-background-color: #f9fdfdff;");

        // Page Header
        Text title = new Text("Medical Report");
        title.setStyle("-fx-font-family: 'Georgia'; -fx-font-size: 40px; -fx-font-weight: bold;");

        // Tabs
        Hyperlink organs = new Hyperlink("Select Organ");
        organs.setTextFill(Color.BLACK);
        organs.setPadding(new Insets(0, 50, 0, 50));
        organs.setOnAction(e -> showDonorUiScreen.run());

        Hyperlink medHistory = new Hyperlink("Medical History");
        medHistory.setTextFill(Color.BLACK);
        medHistory.setPadding(new Insets(0, 50, 0, 50));

        Hyperlink consentForm = new Hyperlink("Consent Form");
        consentForm.setTextFill(Color.BLACK);
        consentForm.setPadding(new Insets(0, 50, 0, 50));
        consentForm.setOnAction(e -> controller.showConsentform());

        HBox navLinks = new HBox(50, organs, medHistory, consentForm);
        navLinks.setPadding(new Insets(0, 100, 0, 100));
        navLinks.setStyle("-fx-font-family: 'Georgia'; -fx-font-size: 20px;");
        navLinks.setAlignment(Pos.CENTER);

        Line line = new Line(0, 0, 1270, 0);
        line.setStroke(Color.BLACK);
        line.setStrokeWidth(2);

        VBox headerBar = new VBox(30, title, navLinks, line);
        headerBar.setAlignment(Pos.CENTER);
        headerBar.setPadding(new Insets(40, 0, 40, 0));

        // Medical Questions
        Text intro = new Text("Please Answer The following Questions:");
        intro.setStyle("-fx-font-family: 'Georgia'; -fx-font-size: 30px;");

        Label q1 = new Label("1. Do you have any current or past major medical conditions?");
        q1.setStyle("-fx-font-family: 'Georgia'; -fx-font-size: 20px;");
        TextArea medicalHistory = new TextArea();
        medicalHistory.setPromptText("E.g., diabetes, heart disease...");
        medicalHistory.setPrefRowCount(1);

        Label q2 = new Label("2. Are you currently taking any prescribed medications?");
        q2.setStyle("-fx-font-family: 'Georgia'; -fx-font-size: 20px;");
        TextArea medications = new TextArea();
        medications.setPromptText("E.g., insulin, blood pressure meds...");
        medications.setPrefRowCount(1);

        Label q3 = new Label("3. Have you ever tested positive for HIV/AIDS?");
        q3.setStyle("-fx-font-family: 'Georgia'; -fx-font-size: 20px;");
        ToggleGroup hivGroup = new ToggleGroup();
        HBox hivBox = new HBox(15, new RadioButton("Yes"), new RadioButton("No"));
        hivBox.getChildren().forEach(n -> ((RadioButton) n).setToggleGroup(hivGroup));

        Label q4 = new Label("4. Have you ever had tuberculosis (TB)?");
        q4.setStyle("-fx-font-family: 'Georgia'; -fx-font-size: 20px;;");
        ToggleGroup tbGroup = new ToggleGroup();
        HBox tbBox = new HBox(15, new RadioButton("Yes"), new RadioButton("No"));
        tbBox.getChildren().forEach(n -> ((RadioButton) n).setToggleGroup(tbGroup));

        Label q5 = new Label("5. Any mental health conditions that may affect decision-making?");
        q5.setStyle("-fx-font-family: 'Georgia'; -fx-font-size: 20px;");
        ToggleGroup mentalGroup = new ToggleGroup();
        HBox mentalBox = new HBox(15, new RadioButton("Yes"), new RadioButton("No"));
        mentalBox.getChildren().forEach(n -> ((RadioButton) n).setToggleGroup(mentalGroup));

        // File Upload ⚡ NEW CODE STARTS HERE
        Button openImageBtn = new Button("Upload Report File");
        openImageBtn.setStyle(
                "-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-family: 'Georgia'; -fx-background-radius: 8;");
        openImageBtn.setTooltip(new Tooltip("Click to select medical document"));

        Label fileLabel = new Label("No file selected.");
        fileLabel.setStyle("-fx-font-size: 14px; -fx-font-family: 'Georgia';");
        Button submitButton = new Button("Submit");
        submitButton.setStyle("-fx-background-color: #FF6B00; -fx-text-fill: white; -fx-font-size: 18px;");

        // ✅ CORRECTED AND WORKING CODE
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

        // ... inside the submitButton.setOnAction(...) handler ...
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
                        showConsentform.run();
                    } else {
                        showAlert("Upload Failed", "An error occurred during upload.");
                    }
                    submitButton.setDisable(false);
                    submitButton.setText("Submit");
                });
            });
        });

        // Medical Form VBox
        VBox formContent = new VBox(10, q1, medicalHistory, q2, medications, q3, hivBox, q4, tbBox, q5, mentalBox);
        formContent.setAlignment(Pos.TOP_LEFT);
        formContent.setPadding(new Insets(20));

        VBox fullForm = new VBox(25, intro, formContent, openImageBtn, fileLabel, submitButton);
        fullForm.setBackground(
                new Background(new BackgroundFill(Color.web("lightpink"), CornerRadii.EMPTY, Insets.EMPTY)));
        fullForm.setPrefWidth(1270);
        fullForm.setPadding(new Insets(40));
        fullForm.setAlignment(Pos.TOP_CENTER);

        ScrollPane scrollPane = new ScrollPane(fullForm);
        scrollPane.setFitToWidth(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setBackground(
                new Background(new BackgroundFill(Color.web("lightpink"), CornerRadii.EMPTY, Insets.EMPTY)));

        VBox leftPanel = new VBox(headerBar, scrollPane);
        leftPanel.setAlignment(Pos.TOP_CENTER);
        leftPanel.setBackground(
                new Background(new BackgroundFill(Color.web("lightpink"), CornerRadii.EMPTY, Insets.EMPTY)));

        VBox root = new VBox(topBar, leftPanel);
        root.setStyle("-fx-background-color: #f8fbfcff;");

        return root;
    }

    private String uploadFileWithRest(File file, String uid) {
        // Use the correct bucket name format
        final String BUCKET_ID = "javafxfirebase-eb545.firebasestorage.app";
        // Create a unique path for the donor's report
        String storagePath = "donorMedicalReports/" + uid + "_" + UUID.randomUUID() + "_"
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