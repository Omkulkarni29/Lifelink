package com.om.View.ReceiverScreens;


import com.om.Controller.ReceiverRegController;
import com.om.Controller.ViewController;
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

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReceiverRegistration {
    final File[] selectedFile = new File[1];


    public VBox ReceiverRegistrationScreen(ViewController controller, Runnable showSignupScreen, Runnable showDashboardScreen,Runnable showReceivermedreportScreen) {
        // Top bar and logo
        Button backArrow = new Button("🏠");
        backArrow.setFont(Font.font("Georgia", 30));
        backArrow.setStyle("-fx-background-color: transparent; -fx-text-fill: black;");
        backArrow.setOnAction(e -> showDashboardScreen.run());

        Text t1 = new Text("Dashboard");
        t1.setStyle("-fx-font-family: 'Georgia'; -fx-font-size: 30;");
        HBox dashboardBox = new HBox(10, backArrow, t1);
        //dashboardBox.setAlignment(Pos.CENTER_LEFT);

        ImageView profile = new ImageView(new Image("asset/profile.jpg"));
        profile.setFitWidth(35);
        profile.setFitHeight(35);
        profile.setOnMouseClicked(e->{
            controller.showDashboardProfileScreen();
        });

        Text notification = new Text("🔔");
        notification.setStyle("-fx-font-family: 'Georgia'; -fx-font-size: 35px;");

        HBox Icon = new HBox(20,  profile);
        HBox topBar = new HBox(1150, dashboardBox, Icon);
        topBar.setPadding(new Insets(5, 20, 0, 40));
        topBar.setStyle("-fx-background-color: #fef8faff;");

        Text title = new Text("Receiver Registration");
        title.setFont(Font.font("Georgia", 40));
        VBox titleBox = new VBox(title);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setPadding(new Insets(10, 50, 10, 50));

        // Navigation links
        Hyperlink organs = new Hyperlink("Select Organ");
        organs.setTextFill(Color.BLACK);
        organs.setPadding(new Insets(0, 50, 0, 50));

        Hyperlink medHistory = new Hyperlink("Medical History");
        medHistory.setTextFill(Color.BLACK);
        medHistory.setPadding(new Insets(0, 50, 0, 50));
        medHistory.setOnAction(e -> controller.showReceivermedreportScreen());

        Hyperlink consentForm = new Hyperlink("Consent Form");
        consentForm.setTextFill(Color.BLACK);
        consentForm.setPadding(new Insets(0, 50, 0, 50));
        consentForm.setOnAction(e -> controller.showReceiverConsentformScreen());

        HBox navLinks = new HBox(50, organs, medHistory, consentForm);
        navLinks.setAlignment(Pos.CENTER);
        navLinks.setPadding(new Insets(0, 100, 0, 100));
        navLinks.setStyle("-fx-font-family: 'Georgia'; -fx-font-size: 20px;");

        Line line = new Line(0, 0, 1270, 0);
        line.setStroke(Color.BLACK);
        line.setStrokeWidth(2);

        VBox header = new VBox(10,navLinks, line);
        header.setAlignment(Pos.CENTER);
        header.setPadding(new Insets(10));

        // Organ Selection
        Text selectLabel = new Text("Select The Organs You Wish To Receive:");
        selectLabel.setStyle("-fx-font-family: 'Georgia'; -fx-font-size: 25px;");

        ToggleButton heartBtn = makeOrganToggleButton("Heart", "asset/icons8-heart-50.png");
        ToggleButton kidneyBtn = makeOrganToggleButton("Kidney", "asset/icons8-kidney-50.png");
        ToggleButton lungsBtn = makeOrganToggleButton("Lungs", "asset/icons8-lungs-50-2.png");
        ToggleButton eyesBtn = makeOrganToggleButton("Eyes", "asset/icons8-eye-50.png");
        ToggleButton liverBtn = makeOrganToggleButton("Liver", "asset/liver.png");
        ToggleButton intestineBtn = makeOrganToggleButton("Intestine", "asset/icons8-intestines-50.png");

        HBox organsBox = new HBox(20, heartBtn, kidneyBtn, lungsBtn, eyesBtn, liverBtn, intestineBtn);
        organsBox.setAlignment(Pos.CENTER);
        organsBox.setPadding(new Insets(10, 30, 10, 30));

        VBox organSection = new VBox(15, selectLabel, organsBox);
        organSection.setPadding(new Insets(10));

        // Receiver Fields
        TextField nameField = new TextField();
        nameField.setMaxWidth(300);

        DatePicker calendarField = new DatePicker(LocalDate.now());

        ToggleGroup genderGroup = new ToggleGroup();
        RadioButton maleRadio = new RadioButton("Male");
        maleRadio.setToggleGroup(genderGroup);
        RadioButton femaleRadio = new RadioButton("Female");
        femaleRadio.setToggleGroup(genderGroup);
        RadioButton otherRadio = new RadioButton("Other");
        otherRadio.setToggleGroup(genderGroup);

        HBox genderBox = new HBox(20, maleRadio, femaleRadio, otherRadio);
        genderBox.setPadding(new Insets(10));

        ToggleGroup bloodGroupToggle = new ToggleGroup();
        String[] bloodGroups = {"A+", "A−", "B+", "B−", "AB+", "AB−", "O+", "O−"};
        HBox bloodGroupBox = new HBox(15);
        for (String group : bloodGroups) {
            RadioButton rb = new RadioButton(group);
            rb.setToggleGroup(bloodGroupToggle);
            bloodGroupBox.getChildren().add(rb);
        }

        VBox bgContainer = new VBox(bloodGroupBox);
        bgContainer.setPadding(new Insets(10));
        bgContainer.setStyle("-fx-border-color: #130f0cff; -fx-border-width: 1px; -fx-border-radius: 8; -fx-background-radius: 8;");

        GridPane detailsGrid = new GridPane();
        detailsGrid.setAlignment(Pos.CENTER);
        detailsGrid.setHgap(10);
        detailsGrid.setVgap(15);
        detailsGrid.setPadding(new Insets(10));
        detailsGrid.setStyle("-fx-border-color: #0d0602ff; -fx-border-radius: 10; -fx-background-radius: 10;");

        detailsGrid.add(new Text("Name:"), 0, 0);
        detailsGrid.add(nameField, 1, 0);
        detailsGrid.add(new Text("Gender:"), 0, 1);
        detailsGrid.add(genderBox, 1, 1);
        detailsGrid.add(new Text("D.O.B:"), 0, 2);
        detailsGrid.add(calendarField, 1, 2);
        detailsGrid.add(new Text("Blood Group:"), 0, 3);
        detailsGrid.add(bgContainer, 1, 3);

        // Submit Button
        Button submit = new Button("Submit");
        submit.setAlignment(Pos.CENTER);
        submit.setStyle("-fx-background-color: #FF6B00; -fx-text-fill: white; -fx-font-size: 22px; -fx-font-family: 'Georgia'; -fx-background-radius: 8;");
        submit.setOnAction(e -> {
            String uid = ViewController.getCurrentUserId();

            if (uid == null || uid.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Error", "User ID missing. Please log in.");
                return;
            }

            String name = nameField.getText().trim();
            String dob = calendarField.getValue() != null ? calendarField.getValue().toString() : "";

            RadioButton selectedGender = (RadioButton) genderGroup.getSelectedToggle();
            String gender = selectedGender != null ? selectedGender.getText() : "";

            RadioButton selectedBlood = (RadioButton) bloodGroupToggle.getSelectedToggle();
            String bloodGroup = selectedBlood != null ? selectedBlood.getText() : "";

            List<String> selectedOrgans = new ArrayList<>();
            if (heartBtn.isSelected()) selectedOrgans.add("Heart");
            if (kidneyBtn.isSelected()) selectedOrgans.add("Kidney");
            if (lungsBtn.isSelected()) selectedOrgans.add("Lungs");
            if (eyesBtn.isSelected()) selectedOrgans.add("Eyes");
            if (liverBtn.isSelected()) selectedOrgans.add("Liver");
            if (intestineBtn.isSelected()) selectedOrgans.add("Intestine");

            if (name.isEmpty() || dob.isEmpty() || gender.isEmpty() || bloodGroup.isEmpty() || selectedOrgans.isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Validation Error", "Please fill all fields and select at least one organ.");
                return;
            }

            ReceiverRegController regController = new ReceiverRegController();
            boolean saved = regController.saveReceiverData(uid, name, gender, dob, bloodGroup, selectedOrgans);

            if (saved) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Receiver data saved successfully! 📝");
                showReceivermedreportScreen.run();
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Something went wrong. Try again.");
            }
        });

        VBox form = new VBox(20, organSection, detailsGrid, submit);
        form.setPrefHeight(790);
        form.setAlignment(Pos.CENTER);
        form.setPadding(new Insets(30));

        VBox body = new VBox(titleBox, header, form);
        body.setAlignment(Pos.TOP_CENTER);
        body.setPadding(new Insets(20));
        body.setBackground(new Background(new BackgroundFill(Color.LIGHTPINK, CornerRadii.EMPTY, Insets.EMPTY)));

        return new VBox(topBar, body);
    }

    private ToggleButton makeOrganToggleButton(String label, String imagePath) {
        ImageView image = new ImageView(new Image(imagePath));
        image.setFitWidth(60);
        image.setFitHeight(60);

        ToggleButton button = new ToggleButton(label, image);
        button.setContentDisplay(ContentDisplay.TOP);
        button.setStyle("-fx-font-family: 'Georgia'; -fx-font-size: 14px; -fx-background-radius: 10;");
        return button;
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
