package com.om.View.DonorScreens;

import com.om.Controller.DonorRegController;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DonorReg {

    public VBox DonorUiScreen(ViewController controller, Runnable showSignupScreen, Runnable showDashboardScreen,Runnable showOrganmedReport) {

        // ========== Top Bar ==========
        Button backArrow = new Button("🏠");
        backArrow.setFont(Font.font("Georgia", 26));
        backArrow.setStyle("-fx-background-color: transparent; -fx-text-fill: black;");
        backArrow.setOnAction(e -> showDashboardScreen.run());

        Text t1 = new Text("DashBoard");
        t1.setStyle("-fx-font-family: 'Georgia'; -fx-font-size: 30;");

        HBox titleBar = new HBox(10, backArrow, t1);
        titleBar.setAlignment(Pos.CENTER_LEFT);

        ImageView profile = new ImageView(new Image("asset/profile.jpg"));
        profile.setFitWidth(35);
        profile.setFitHeight(35);
        profile.setOnMouseClicked(e->{
            controller.showDashboardProfileScreen();
        });

        Text notification = new Text("🔔");
        notification.setStyle("-fx-font-family: 'Georgia'; -fx-font-size: 35px;");

        HBox icon = new HBox(20,  profile);
        icon.setAlignment(Pos.CENTER_RIGHT);

        HBox logo = new HBox(1150, titleBar, icon);
        logo.setPadding(new Insets(5, 20, 0, 40));
        logo.setStyle("-fx-background-color: #fef8faff;");

        // ========== Title ==========
        Text t3 = new Text("Donor Registration");
        t3.setStyle("-fx-font-family: 'Georgia'; -fx-font-size: 40px; -fx-font-weight: bold;");
        VBox vb3 = new VBox(t3);
        vb3.setAlignment(Pos.CENTER);
        vb3.setPadding(new Insets(30, 50, 10, 50));

        // ========== Navigation Hyperlinks ==========
        Hyperlink link1 = new Hyperlink("Select Organ");
        link1.setTextFill(Color.BLACK);
        Hyperlink link2 = new Hyperlink("Medical History");
        link2.setTextFill(Color.BLACK);
        link2.setOnAction(e->{
            controller.showOrganmedReport();
        });
        Hyperlink link3 = new Hyperlink("Consent Form");
        link3.setTextFill(Color.BLACK);
        link3.setOnAction(e->{
            controller.showConsentform();
        });

        for (Hyperlink link : List.of(link1, link2, link3)) {
            link.setStyle("-fx-font-family: 'Georgia'; -fx-font-size: 18px;");
        }

        HBox navLinks = new HBox(50, link1, link2, link3);
        navLinks.setAlignment(Pos.CENTER);
        navLinks.setPadding(new Insets(0, 0, 10, 0));

        Line line = new Line(0, 0, 1300, 0);
        line.setStroke(Color.BLACK);
        line.setStrokeWidth(1.3);

        VBox navBar = new VBox(navLinks, line);
        navBar.setAlignment(Pos.CENTER);

        // ========== Organ Selection ==========
        Text select = new Text("Select The Organs You Wish To Donate:");
        select.setStyle("-fx-font-family: 'Georgia'; -fx-font-size: 25px;");

        ToggleButton heartBtn = makeOrganToggle("Heart", "asset/icons8-heart-50.png");
        ToggleButton kidneyBtn = makeOrganToggle("Kidney", "asset/icons8-kidney-50.png");
        ToggleButton lungsBtn = makeOrganToggle("Lungs", "asset/icons8-lungs-50-2.png");
        ToggleButton eyesBtn = makeOrganToggle("Eyes", "asset/icons8-eye-50.png");
        ToggleButton liverBtn = makeOrganToggle("Liver", "asset/liver.png");
        ToggleButton intestineBtn = makeOrganToggle("Intestine", "asset/icons8-intestines-50.png");

        HBox organList = new HBox(20, heartBtn, kidneyBtn, lungsBtn, eyesBtn, liverBtn, intestineBtn);
        organList.setPadding(new Insets(10, 30, 10, 30));
        organList.setAlignment(Pos.CENTER);

        VBox organSection = new VBox(15, select, organList);
        organSection.setPadding(new Insets(20));
        organSection.setPrefWidth(1250);

        // ========== Donor Details ==========
        final TextField nameField = new TextField();
        final DatePicker calendarField = new DatePicker(LocalDate.now());
        final ToggleGroup genderGroup = new ToggleGroup();
        final ToggleGroup bloodGroupToggle = new ToggleGroup();

        Text nameLabel = new Text("Name:");
        nameLabel.setStyle("-fx-font-family: 'Georgia'; -fx-font-size: 25px;");
        nameField.setMaxWidth(300);

        Text date = new Text("D.O.B:");
        date.setStyle("-fx-font-family: 'Georgia'; -fx-font-size: 25px;");

        Text bloodgroup = new Text("Select Blood Group:");
        bloodgroup.setStyle("-fx-font-family: 'Georgia'; -fx-font-size: 25px;");

        String[] groups = {"A+", "A−", "B+", "B−", "AB+", "AB−", "O+", "O−"};
        HBox bloodGroupBox = new HBox(15);
        bloodGroupBox.setPadding(new Insets(10));
        bloodGroupBox.setAlignment(Pos.CENTER_LEFT);
        for (String group : groups) {
            RadioButton rb = new RadioButton(group);
            rb.setToggleGroup(bloodGroupToggle);
            rb.setStyle("-fx-font-family: 'Georgia'; -fx-font-size: 16px;");
            bloodGroupBox.getChildren().add(rb);
        }

        Text genderText = new Text("Gender:");
        genderText.setStyle("-fx-font-family: 'Georgia'; -fx-font-size: 25px;");
        RadioButton maleRadio = new RadioButton("Male");
        RadioButton femaleRadio = new RadioButton("Female");
        RadioButton otherRadio = new RadioButton("Other");
        maleRadio.setToggleGroup(genderGroup);
        femaleRadio.setToggleGroup(genderGroup);
        otherRadio.setToggleGroup(genderGroup);

        HBox genderBox = new HBox(20, maleRadio, femaleRadio, otherRadio);
        genderBox.setPadding(new Insets(10));

        GridPane donorDetails = new GridPane();
        donorDetails.setVgap(15);
        donorDetails.setHgap(10);
        donorDetails.setPadding(new Insets(10));
        donorDetails.setAlignment(Pos.CENTER_LEFT);
        donorDetails.add(nameLabel, 0, 0);
        donorDetails.add(nameField, 1, 0);
        donorDetails.add(genderText, 0, 1);
        donorDetails.add(genderBox, 1, 1);
        donorDetails.add(date, 0, 2);
        donorDetails.add(calendarField, 1, 2);
        donorDetails.add(bloodgroup, 0, 3);
        donorDetails.add(bloodGroupBox, 1, 3);

        // ========== Submit ==========
        Button submit = new Button("Submit");
        submit.setStyle("-fx-background-color: #FF6B00; -fx-text-fill: white; -fx-font-size: 22px; -fx-font-family: 'Georgia'; -fx-background-radius: 8;");
        submit.setOnAction(e -> {
            String uid = ViewController.getCurrentUserId();
            if (uid == null || uid.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "❌ Error", "User ID is missing. Please login again.");
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
            if (intestineBtn.isSelected()) selectedOrgans.add("Intestine");
            if (liverBtn.isSelected()) selectedOrgans.add("Liver");

            if (name.isEmpty() || gender.isEmpty() || bloodGroup.isEmpty() || dob.isEmpty() || selectedOrgans.isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Validation Error", "Please fill all fields and select organ(s).");
                return;
            }

            DonorRegController regController = new DonorRegController();
            boolean saved = regController.saveDonorData(uid, name, gender, dob, bloodGroup, selectedOrgans);

            if (saved) {
                showAlert(Alert.AlertType.INFORMATION, "✅ Success", "Donor data saved successfully!");
                showOrganmedReport.run();
            } else {
                showAlert(Alert.AlertType.ERROR, "❌ Error", "Failed to save. Please try again.");
            }
        });

        // ========== Assemble ==========
        VBox form = new VBox(20, donorDetails, organSection, submit);
        form.setPadding(new Insets(30));

        VBox mainContent = new VBox(vb3, navBar, form);
        mainContent.setAlignment(Pos.TOP_CENTER);
        mainContent.setPrefHeight(790);
        mainContent.setPadding(new Insets(0, 20, 0, 20));
        mainContent.setBackground(new Background(new BackgroundFill(Color.web("lightpink"), CornerRadii.EMPTY, Insets.EMPTY)));

        VBox root = new VBox(logo, mainContent);
        root.setStyle("-fx-background-color: #f8fbfcff;");
        return root;
    }

    private ToggleButton makeOrganToggle(String organName, String iconPath) {
        ImageView icon = new ImageView(new Image(iconPath));
        icon.setFitWidth(60);
        icon.setFitHeight(60);
        ToggleButton button = new ToggleButton(organName, icon);
        button.setContentDisplay(ContentDisplay.TOP);
        button.setPrefWidth(100);
        button.setStyle("-fx-font-family: 'Georgia'; -fx-font-size: 14px;");
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
