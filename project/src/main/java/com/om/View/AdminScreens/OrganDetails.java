package com.om.View.AdminScreens;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.text.Text;

import com.om.Controller.DonorDataController;
import com.om.model.Donormodel;

public class OrganDetails {

    public VBox OrganDetailScreen(com.om.Controller.ViewController controller) {
        VBox root = new VBox(20);
        root.setStyle("-fx-background-color: lightpink;");
        root.setPadding(new Insets(20, 40, 20, 40));

        // Back Arrow
        Button backArrow = new Button("←");
        backArrow.setFont(Font.font("Arial", 24));
        backArrow.setStyle("-fx-background-color: transparent; -fx-text-fill: black; -fx-cursor: hand;");
        backArrow.setOnAction(e -> controller.showAdminDashboardScreen());

        // Header Label
        Label header = new Label("ORGAN DETAILS");
        header.setFont(new Font("Arial", 28));
        header.setStyle("-fx-font-weight: bold; -fx-text-fill: black;");

        HBox headerBox = new HBox(10, backArrow, header);

        // Filters
        ComboBox<String> organTypeBox = new ComboBox<>();
        organTypeBox.getItems().addAll("Kidney", "Liver", "Heart", "Lungs");
        organTypeBox.setPromptText("Select Organ Type");

        ComboBox<String> bloodGroupBox = new ComboBox<>();
        bloodGroupBox.getItems().addAll("A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-");
        bloodGroupBox.setPromptText("Select Blood Group");

        HBox filterBox = new HBox(50, organTypeBox, bloodGroupBox);
        filterBox.setPadding(new Insets(30, 20, 20, 50));
        filterBox.setStyle("-fx-background-color: #fcdde8; -fx-border-radius: 10; -fx-background-radius: 10; -fx-font-size: 15px;");

        // Table Setup
        TableView<MatchEntry> matchTable = new TableView<>();

        TableColumn<MatchEntry, String> availableCol = new TableColumn<>("Available");
        availableCol.setCellValueFactory(new PropertyValueFactory<>("available"));
        availableCol.setMinWidth(800);

        // Enable word-wrap so long details don't get cut
        availableCol.setCellFactory(tc -> {
            TableCell<MatchEntry, String> cell = new TableCell<>() {
                private final Text text = new Text();
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setGraphic(null);
                    } else {
                        text.setText(item);
                        text.wrappingWidthProperty().bind(getTableColumn().widthProperty().subtract(10));
                        setGraphic(text);
                    }
                }
            };
            return cell;
        });

        matchTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        matchTable.getColumns().addAll(availableCol);
        matchTable.setStyle("-fx-font-size: 14px; -fx-background-color: #fcdde8; -fx-border-radius: 10; -fx-background-radius: 10;");

        // Buttons
        Button matchBtn = new Button("Matches Found");
        Button notifyBtn = new Button("🔔 Notify");
        Button refreshBtn = new Button("🔄 Refresh");

        matchBtn.setStyle("-fx-background-color: #ff6b6b; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10;");
        notifyBtn.setStyle("-fx-background-color: #ff6b6b; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10;");
        refreshBtn.setStyle("-fx-background-color: #ff6b6b; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10;");

        HBox buttonBox = new HBox(50, matchBtn, notifyBtn, refreshBtn);
        buttonBox.setPadding(new Insets(20));

        // Matching & Filter Logic
        matchBtn.setOnAction(e -> {
            String selectedOrgan = organTypeBox.getValue();
            String selectedBlood = bloodGroupBox.getValue();
            matchTable.getItems().clear();

            if (selectedOrgan == null || selectedBlood == null) {
                matchTable.getItems().add(new MatchEntry("❗ Please select both organ type and blood group."));
                return;
            }

            int matched = 0;

            for (Donormodel donor : DonorDataController.getAllDonors()) {
                boolean organMatch = false;

                if (donor.getOrgans() != null) {
                    for (String organ : donor.getOrgans()) {
                        if (organ != null && organ.trim().equalsIgnoreCase(selectedOrgan.trim())) {
                            organMatch = true;
                            break;
                        }
                    }
                }

                boolean bloodGroupMatch = donor.getBloodGroup() != null &&
                        donor.getBloodGroup().trim().equalsIgnoreCase(selectedBlood.trim());

                if (organMatch && bloodGroupMatch) {
                    matched++;
                    String row = "👤 Name: " + donor.getName()
                            + " | Gender: " + donor.getGender()
                            + " | DOB: " + donor.getDob()
                            + " | Blood: " + donor.getBloodGroup()
                            + " | Organs: " + (donor.getOrgans() != null
                            ? String.join(", ", donor.getOrgans()) : "");
                    matchTable.getItems().add(new MatchEntry(row));
                }
            }

            if (matched == 0) {
                matchTable.getItems().add(new MatchEntry("⚠ No matching donors found for selected criteria."));
            }
        });

        // Add everything to root layout
        root.getChildren().addAll(headerBox, filterBox, matchTable, buttonBox);
        return root;
    }

    // Row Model for TableView (Used by "Available" column)
    public static class MatchEntry {
        private final SimpleStringProperty available;
        public MatchEntry(String value) {
            this.available = new SimpleStringProperty(value);
        }
        public String getAvailable() { return available.get(); }
        public void setAvailable(String value) { available.set(value); }
    }
}
