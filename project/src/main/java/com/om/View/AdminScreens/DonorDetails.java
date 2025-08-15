package com.om.View.AdminScreens;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import com.om.Controller.DonorDataController;
import com.om.model.Donormodel;

import javafx.beans.property.SimpleStringProperty;

public class DonorDetails{

    public VBox DonorDetailsScreen(com.om.Controller.ViewController controller) {
        VBox root = new VBox(20);
        root.setStyle("-fx-background-color: lightpink;");
        root.setPadding(new Insets(20, 40, 20, 40));

        // Back Arrow Button
        Button backArrow = new Button("←");
        backArrow.setFont(Font.font("Arial", 24));
        backArrow.setStyle("-fx-background-color: transparent; -fx-text-fill: black; -fx-cursor: hand;");
        backArrow.setOnAction(e -> {
            controller.showAdminDashboardScreen();
        });

        // Header
        Label header = new Label("Donor DETAILS");
        header.setFont(new Font("Arial", 28));
        header.setStyle("-fx-font-weight: bold;" +
                        "-fx-text-fill:black;");

        // Combine back arrow and header
        HBox headerBox = new HBox(10, backArrow, header);

        // Organ Type & Blood Group Filters
        ComboBox<String> organTypeBox = new ComboBox<>();
        organTypeBox.getItems().addAll("Kidney", "Liver", "Heart", "Lungs");
        organTypeBox.setPromptText("Select Organ Type");

        ComboBox<String> bloodGroupBox = new ComboBox<>();
        bloodGroupBox.getItems().addAll("A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-");
        bloodGroupBox.setPromptText("Select Blood Group");

        HBox filterBox = new HBox(50, organTypeBox, bloodGroupBox);
        filterBox.setPadding(new Insets(30, 20, 20, 50));
        filterBox.setStyle("-fx-background-color: #fcdde8; -fx-border-radius: 10; -fx-background-radius: 10; -fx-font-size: 15px;");

        // TableView
        TableView<MatchEntry> matchTable = new TableView<>();

        TableColumn<MatchEntry, String> availableCol = new TableColumn<>("Available");
        availableCol.setCellValueFactory(new PropertyValueFactory<>("available"));
        availableCol.setPrefWidth(1000);


        matchTable.getColumns().addAll(availableCol);
        matchTable.setStyle("-fx-font-size: 14px; -fx-background-color: #fcdde8; -fx-border-radius: 10; -fx-background-radius: 10;");

        // Buttons
        Button matchBtn = new Button("Matches Found");
        matchBtn.setStyle("-fx-background-color: #ff6b6b; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10;");
        Button notifyBtn = new Button("🔔 Notify");
        notifyBtn.setStyle("-fx-background-color: #ff6b6b; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10;");
        Button refreshBtn = new Button("🔄 Refresh");
        refreshBtn.setStyle("-fx-background-color: #ff6b6b; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10;");

        // Robust, case- and whitespace-insensitive filter!
        matchBtn.setOnAction(e -> {
            String selectedOrgan = organTypeBox.getValue();
            String selectedBlood = bloodGroupBox.getValue();
            matchTable.getItems().clear();

            if (selectedOrgan == null || selectedBlood == null) {
                matchTable.getItems().add(new MatchEntry("❗ Please select organ and blood group"));
                return;
            }

            int matchedCount = 0;

            for (Donormodel donor : DonorDataController.getAllDonors()) {
                boolean organMatch = false;
                if (donor.getOrgans() != null) {
                    for (String organ : donor.getOrgans()) {
                        if (organ != null &&
                            organ.trim().equalsIgnoreCase(selectedOrgan.trim())) {
                            organMatch = true;
                            break;
                        }
                    }
                }
                boolean bloodMatch = donor.getBloodGroup() != null &&
                        donor.getBloodGroup().trim().equalsIgnoreCase(selectedBlood.trim());
                if (bloodMatch && organMatch) {
                    matchedCount++;
                    String row = "👤 Name: " + donor.getName() +
                            " | Gender: " + donor.getGender() +
                            " | DOB: " + donor.getDob() +
                            " | Blood: " + donor.getBloodGroup() +
                            " | Organs: " + (donor.getOrgans() == null ? "" : String.join(", ", donor.getOrgans()));
                    matchTable.getItems().add(new MatchEntry(row));
                }
            }

            if (matchedCount == 0) {
                matchTable.getItems().add(new MatchEntry("⚠ No donors match the selected filters."));
            }
        });

        HBox buttonBox = new HBox(50, matchBtn, notifyBtn);
        buttonBox.setPadding(new Insets(20));

        root.getChildren().addAll(headerBox, filterBox, matchTable, buttonBox);
        return root;
    }

    // Static inner class for table data
    public static class MatchEntry {
        private final SimpleStringProperty available;
        public MatchEntry(String value) { this.available = new SimpleStringProperty(value); }
        public String getAvailable() { return available.get(); }
        public void setAvailable(String value) { available.set(value); }
    }
}
