package com.om.View.AdminScreens;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import com.om.model.Donormodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.cell.PropertyValueFactory;

public class MatchingSystem {

    // 🧬 Data Model for Match Entry
    public static class MatchEntry {
        private final SimpleStringProperty donor;
        private final SimpleStringProperty receiver;

        public MatchEntry(String donor, String receiver) {
            this.donor = new SimpleStringProperty(donor);
            this.receiver = new SimpleStringProperty(receiver);
        }

        public String getDonor() {
            return donor.get();
        }

        public String getReceiver() {
            return receiver.get();
        }
    }

    // 🎯 Matching System UI
    public VBox MatchingSystemScreen(com.om.Controller.ViewController controller) {
        VBox root = new VBox(20);
        root.setStyle("-fx-background-color:lightpink ;");
        root.setPadding(new Insets(20, 40, 20, 40));

        // ⬅️ Back Arrow Button
        Button backArrow = new Button("←");
        backArrow.setFont(Font.font("Georgia", 26));
        backArrow.setStyle("-fx-background-color: transparent; -fx-text-fill: black;");
        backArrow.setOnAction(e -> {
            controller.showAdminDashboardScreen(); // Your method to navigate back
        });

        // 🔗 Header
        Label header = new Label(" Match Donors & Receivers");
        header.setFont(new Font("Arial", 28));
        header.setStyle("-fx-font-weight: bold; -fx-text-fill: black;");

        HBox backBox = new HBox(backArrow, header);
        backBox.setPadding(new Insets(10, 0, 10, 0));

        // 📋 Filter Section
        ComboBox<String> organTypeBox = new ComboBox<>();
        organTypeBox.getItems().addAll("Kidney", "Liver", "Heart", "Lungs");
        organTypeBox.setPromptText("Select Organ Type");

        ComboBox<String> bloodGroupBox = new ComboBox<>();
        bloodGroupBox.getItems().addAll("A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-");
        bloodGroupBox.setPromptText("Select Blood Group");

        HBox filterBox = new HBox(50, organTypeBox, bloodGroupBox);
        filterBox.setPadding(new Insets(30, 20, 20, 50));
        filterBox.setStyle(
                "-fx-background-color: #fcdde8; -fx-border-radius: 10; -fx-background-radius: 10; -fx-font-size: 15px;");

        // 📊 Table for Matches
        TableView<MatchEntry> matchTable = new TableView<>();

        TableColumn<MatchEntry, String> donorCol = new TableColumn<>("Donor");
        donorCol.setCellValueFactory(new PropertyValueFactory<>("donor"));
        donorCol.setMinWidth(250);

        TableColumn<MatchEntry, String> receiverCol = new TableColumn<>("Receiver");
        receiverCol.setCellValueFactory(new PropertyValueFactory<>("receiver"));
        receiverCol.setMinWidth(250);

        matchTable.getColumns().addAll(donorCol, receiverCol);
        matchTable.setStyle(
                "-fx-font-size: 14px; -fx-background-color: #fcdde8; -fx-border-radius: 10; -fx-background-radius: 10;");

        // 🔘 Buttons
        Button matchBtn = new Button("🔍 Auto Match");
        Button notifyBtn = new Button("📩 Notify Matches");
        Button refreshBtn = new Button("🔄 Refresh");

        matchBtn.setStyle(
                "-fx-background-color: #ff6b6b; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10;");
        notifyBtn.setStyle(
                "-fx-background-color: #ff6b6b; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10;");
        refreshBtn.setStyle(
                "-fx-background-color: #ff6b6b; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10;");

        HBox buttonBox = new HBox(50, matchBtn, notifyBtn, refreshBtn);
        buttonBox.setPadding(new Insets(20));

        // ⚙️ Matching Logic
        matchBtn.setOnAction(e -> {
            String selectedOrgan = organTypeBox.getValue();
            String selectedBlood = bloodGroupBox.getValue();
            matchTable.getItems().clear();

            if (selectedOrgan == null || selectedBlood == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please select both organ and blood group.");
                alert.show();
                return;
            }

            int matches = 0;

            for (Donormodel donor : com.om.Controller.DonorDataController.getAllDonors()) {
                if (donor.getOrgans() == null || donor.getBloodGroup() == null)
                    continue;

                boolean organMatch = donor.getOrgans().stream()
                        .anyMatch(organ -> organ != null && organ.trim().equalsIgnoreCase(selectedOrgan.trim()));
                boolean bloodMatch = donor.getBloodGroup().trim().equalsIgnoreCase(selectedBlood.trim());

                if (organMatch && bloodMatch) {
                    for (com.om.model.ReceiverModel receiver : com.om.Controller.ReceiverDataController
                            .getAllReceivers()) {
                        if (receiver.getOrgans() == null || receiver.getBloodGroup() == null)
                            continue;

                        boolean receiverOrganMatch = receiver.getOrgans().stream()
                                .anyMatch(
                                        organ -> organ != null && organ.trim().equalsIgnoreCase(selectedOrgan.trim()));
                        boolean receiverBloodMatch = receiver.getBloodGroup().trim()
                                .equalsIgnoreCase(selectedBlood.trim());

                        if (receiverOrganMatch && receiverBloodMatch) {
                            matchTable.getItems().add(new MatchEntry(donor.getName(), receiver.getName()));
                            matches++;
                        }
                    }
                }
            }

            if (matches == 0) {
                matchTable.getItems().add(new MatchEntry("No Match Found", "-"));
            }
        });

        // 🧩 Assemble
        root.getChildren().addAll(backBox, filterBox, matchTable, buttonBox);
        return root;
    }
}
