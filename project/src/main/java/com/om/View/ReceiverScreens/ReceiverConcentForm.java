package com.om.View.ReceiverScreens;

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

public class ReceiverConcentForm {

    public VBox ReceiverConcentFormScreen(com.om.Controller.ViewController controller, Runnable showSignupScreen, Runnable showReceivermedreportScreen, Runnable showReceiverRegistrationScreen,Runnable showDashboardScreen) {

        // 🏠 Back Button (Transparent Background)
        Button backArrow = new Button("🏠");
        backArrow.setFont(Font.font("Georgia", 26));
        backArrow.setStyle("-fx-background-color: transparent; -fx-text-fill: black;");
        backArrow.setOnAction(e -> showDashboardScreen.run());

        // Dashboard title
        Text dashboard = new Text("Dashboard");
        dashboard.setFont(Font.font("Georgia", 25));

        HBox dashboardBox = new HBox(10, backArrow, dashboard);
        dashboardBox.setAlignment(Pos.CENTER_LEFT);

        // Profile and bell
        ImageView profilePic = new ImageView(new Image("asset\\profile.jpg"));
        profilePic.setFitWidth(35);
        profilePic.setFitHeight(35);

        Text bell = new Text("🔔");
        bell.setFont(Font.font("Georgia", 35));

        HBox rightIcons = new HBox(20, profilePic);
        rightIcons.setAlignment(Pos.CENTER_RIGHT);

        HBox topBar = new HBox(1100, dashboardBox, rightIcons); // Adjusted spacing
        topBar.setPadding(new Insets(5, 20, 0, 40));
        topBar.setStyle("-fx-background-color: #f3f5f6ff;");

        // Header
        Text title = new Text("Receiver Consent Form");
        title.setFont(Font.font("Georgia", 40));

        Hyperlink organs = new Hyperlink("Select Organ");
        organs.setTextFill(Color.BLACK);
        organs.setOnAction(e -> showReceiverRegistrationScreen.run());

        Hyperlink medHistory = new Hyperlink("Medical History");
        medHistory.setTextFill(Color.BLACK);
        medHistory.setOnAction(e -> showReceivermedreportScreen.run());

        Hyperlink consentForm = new Hyperlink("Consent Form");
        consentForm.setTextFill(Color.BLACK);

        for (Hyperlink h : new Hyperlink[]{organs, medHistory, consentForm}) {
            h.setFont(Font.font("Georgia", 20));
        }

        HBox navLinks = new HBox(50, organs, medHistory, consentForm);
        navLinks.setAlignment(Pos.CENTER);

        Line line = new Line(0, 0, 1270, 0);
        line.setStroke(Color.BLACK);
        line.setStrokeWidth(2);

        VBox headerBar = new VBox(10, title, navLinks, line);
        headerBar.setStyle("-fx-background-color: lightpink;");
        headerBar.setAlignment(Pos.CENTER);
        headerBar.setPadding(new Insets(30, 0, 20, 0));

        // Form Fields
        Label nameLabel = new Label("Full Name:");
        nameLabel.setFont(Font.font("Georgia", 20));
        TextField nameField = new TextField();
        nameField.setMaxWidth(400);

        Label ageLabel = new Label("Age:");
        ageLabel.setFont(Font.font("Georgia", 20));
        TextField ageField = new TextField();
        ageField.setMaxWidth(100);

        VBox personalBox = new VBox(10, nameLabel, nameField, ageLabel, ageField);
        personalBox.setAlignment(Pos.CENTER);
        personalBox.setPadding(new Insets(10));

        Label organsLabel = new Label("Organs you wish to donate:");
        organsLabel.setFont(Font.font("Georgia", 20));
        CheckBox heart = new CheckBox("Heart");
        heart.setFont(Font.font("Georgia", 20));

        CheckBox lungs = new CheckBox("Lungs");
        lungs.setFont(Font.font("Georgia", 20));
        CheckBox kidneys = new CheckBox("Kidneys");
        kidneys.setFont(Font.font("Georgia", 20));
        CheckBox liver = new CheckBox("Liver");
        liver.setFont(Font.font("Georgia", 20));
        CheckBox eyes = new CheckBox("Eyes");
        eyes.setFont(Font.font("Georgia", 20));

        VBox organBox = new VBox(5, organsLabel, heart, lungs, kidneys, liver, eyes);
        organBox.setAlignment(Pos.CENTER);
        organBox.setPadding(new Insets(10));

        Label consentLabel = new Label("Consent Message:");
        consentLabel.setFont(Font.font("Georgia", 20));
        TextArea consentArea = new TextArea("I hereby give my full and free consent for organ donation. I confirm that I am doing this voluntarily and have read and understood the legal rights and responsibilities.");
        consentArea.setWrapText(true);
        consentArea.setPrefRowCount(4);
        consentArea.setMaxWidth(700);

        Label declaration = new Label("Legal & Medical Acknowledgment:");
        declaration.setFont(Font.font("Georgia", 20));
        TextArea declarationArea = new TextArea(
                "- I confirm that I have disclosed all known medical conditions.\n" +
                        "- I understand that medical assessment will determine final donation eligibility.\n\n" +
                        "- I understand this decision is voluntary and free from coercion.\n" +
                        "- I understand that organ donation is governed by the Transplantation of Human Organs and Tissues Act (THOTA), India.\n" +
                        "- I consent to my data being used solely for organ donation and registration purposes.\n\n" +
                        "- I understand I can withdraw my consent at any time prior to donation."
        );
        declarationArea.setWrapText(true);
        declarationArea.setEditable(false);
        declarationArea.setMaxWidth(700);
        declarationArea.setPrefRowCount(10);

        CheckBox agreeCheck = new CheckBox("I agree to all the above terms and conditions.");

        Button submitButton = new Button("Submit");
        submitButton.setStyle("-fx-background-color: #FF6B00; -fx-text-fill: white; -fx-font-size: 18px; -fx-font-family: 'Georgia'; -fx-background-radius: 8;");
        submitButton.setOnAction(e -> {
            if (agreeCheck.isSelected()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Consent Submitted Successfully!", ButtonType.OK);
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please agree to the terms before submitting.", ButtonType.OK);
                alert.showAndWait();
            }
        });

        VBox formContent = new VBox(15, personalBox, organBox, consentLabel, consentArea, declaration, declarationArea, agreeCheck, submitButton);
        formContent.setPadding(new Insets(20));
        formContent.setAlignment(Pos.TOP_CENTER);
        formContent.setStyle("-fx-background-color: lightpink;");

        ScrollPane scrollPane = new ScrollPane(formContent);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefHeight(600);
        scrollPane.setStyle("-fx-background-color: transparent;");

        VBox fullForm = new VBox(10, headerBar, scrollPane);
        fullForm.setPrefHeight(790);
        fullForm.setStyle("-fx-background-color: lightpink;");
        fullForm.setPrefWidth(1270);
        fullForm.setPadding(new Insets(10));
        fullForm.setAlignment(Pos.TOP_CENTER);

        VBox root = new VBox(topBar, fullForm);
        root.setStyle("-fx-background-color: #f8fbfcff;");

        return root;
    }
}
