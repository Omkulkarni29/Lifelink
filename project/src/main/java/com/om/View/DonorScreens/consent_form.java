package com.om.View.DonorScreens;

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

public class consent_form {

    public VBox ConsentformScreen(com.om.Controller.ViewController controller, Runnable showSignupScreen, Runnable showOrganmedReport, Runnable showDonorUiScreen,Runnable showDashboardScreen) {
        // Top bar
        Button backArrow = new Button("🏠");
        backArrow.setFont(Font.font("Georgia", 26));
        backArrow.setStyle("-fx-background-color: transparent; -fx-text-fill: black;");
        backArrow.setOnAction(e -> showDashboardScreen.run());

        Text t1 = new Text("Dashboard");
        t1.setFont(Font.font("Georgia", 25));

        HBox titleBox = new HBox(10, backArrow, t1);
        titleBox.setAlignment(Pos.CENTER_LEFT);

        ImageView profile = new ImageView(new Image("asset\\profile.jpg"));
        profile.setFitWidth(35);
        profile.setFitHeight(35);

        Text notification = new Text("🔔");
        notification.setFont(Font.font("Georgia", 35));

        HBox topBar = new HBox(20, titleBox, new Region(), new HBox(20, profile));
        HBox.setHgrow(topBar.getChildren().get(1), Priority.ALWAYS);
        topBar.setPadding(new Insets(5, 20, 0, 40));
        topBar.setStyle("-fx-background-color: #f3f5f6ff;");
        topBar.setAlignment(Pos.CENTER_LEFT);

        // Header
        Text title = new Text("Consent Form");
        title.setFont(Font.font("Georgia", 40));

        Hyperlink organs = new Hyperlink("Select Organ");
        organs.setOnAction(e -> showDonorUiScreen.run());

        Hyperlink medHistory = new Hyperlink("Medical History");
        medHistory.setOnAction(e -> showOrganmedReport.run());

        Hyperlink consentForm = new Hyperlink("Consent Form");

        for (Hyperlink h : new Hyperlink[]{organs, medHistory, consentForm}) {
            h.setFont(Font.font("Georgia", 20));
            h.setStyle("-fx-text-fill: black;");
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

        // Form Content
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
        formContent.setStyle("-fx-background-color: lightpink;");
        formContent.setAlignment(Pos.TOP_CENTER);

        ScrollPane formScrollPane = new ScrollPane(formContent);
        formScrollPane.setFitToWidth(true);
        formScrollPane.setPrefHeight(600);
        formScrollPane.setStyle("-fx-background-color: transparent;");
        formScrollPane.setPadding(new Insets(10));

        VBox fullForm = new VBox(10, headerBar, formScrollPane);
        fullForm.setStyle("-fx-background-color: lightpink;");
        fullForm.setPrefWidth(1270);
        fullForm.setPadding(new Insets(10));
        fullForm.setAlignment(Pos.TOP_CENTER);

        VBox root = new VBox(topBar, fullForm);
        root.setStyle("-fx-background-color: #f8fbfcff;");

        return root;
    }
}
