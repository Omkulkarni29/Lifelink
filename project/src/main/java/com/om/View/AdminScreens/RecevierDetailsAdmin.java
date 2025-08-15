package com.om.View.AdminScreens;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import com.om.Controller.ReceiverDataController;
import com.om.model.ReceiverModel;

import javafx.beans.property.SimpleStringProperty;

public class RecevierDetailsAdmin {

    public static class MatchEntry {
        private final SimpleStringProperty name;
        private final SimpleStringProperty organNeeded;
        private final SimpleStringProperty urgency;

        public MatchEntry(String name, String organNeeded, String urgency) {
            this.name = new SimpleStringProperty(name);
            this.organNeeded = new SimpleStringProperty(organNeeded);
            this.urgency = new SimpleStringProperty(urgency);
        }

        public String getName() {
            return name.get();
        }

        public String getOrganNeeded() {
            return organNeeded.get();
        }

        public String getUrgency() {
            return urgency.get();
        }
    }

    public VBox RecevierDetailsScreen(com.om.Controller.ViewController controller) {
        VBox root = new VBox(20);
        root.setStyle("-fx-background-color: lightpink;");
        root.setPadding(new Insets(20, 40, 20, 40));

        // Transparent back arrow button
        Button backArrow = new Button("←");
        backArrow.setFont(Font.font("Georgia", 26));
        backArrow.setStyle("-fx-background-color: transparent; -fx-text-fill: black; -fx-cursor: hand;");

        // You can add navigation functionality here if needed
        backArrow.setOnAction(e -> {
            controller.showAdminDashboardScreen(); // Optional
        });

        // Header
        Label header = new Label("RECEIVER DETAILS");
        header.setFont(new Font("Arial", 28));
        header.setStyle("-fx-text-fill: black; -fx-font-weight: bold;");

        HBox topBar = new HBox(10, backArrow, header);
        topBar.setPadding(new Insets(0, 0, 20, 0));

        // Filters
        ComboBox<String> organTypeBox = new ComboBox<>();
        organTypeBox.getItems().addAll("Kidney", "Liver", "Heart", "Lungs");
        organTypeBox.setPromptText("Select Organ Needed");

        ComboBox<String> bloodGroupBox = new ComboBox<>();
        bloodGroupBox.getItems().addAll("A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-");
        bloodGroupBox.setPromptText("Select Blood Group");

        HBox filterBox = new HBox(50, organTypeBox, bloodGroupBox);
        filterBox.setPadding(new Insets(30, 20, 20, 50));
        filterBox.setStyle(
                "-fx-background-color: #fcdde8; -fx-border-radius: 10; -fx-background-radius: 10; -fx-font-size: 15px;");
        filterBox.setSpacing(100);

        // Table
        TableView<MatchEntry> matchTable = new TableView<>();

        TableColumn<MatchEntry, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameCol.setMinWidth(300);

        TableColumn<MatchEntry, String> organCol = new TableColumn<>("Organ Needed");
        organCol.setCellValueFactory(new PropertyValueFactory<>("organNeeded"));
        organCol.setMinWidth(250);

        TableColumn<MatchEntry, String> urgencyCol = new TableColumn<>("Urgency");
        urgencyCol.setCellValueFactory(new PropertyValueFactory<>("urgency"));
        urgencyCol.setMinWidth(250);

        matchTable.getColumns().addAll(nameCol, organCol, urgencyCol);
        matchTable.setStyle(
                "-fx-font-size: 14px; -fx-background-color: #e9dfdfff; -fx-border-radius: 20; -fx-background-radius: 10;");

        // Buttons
        Button matchBtn = new Button("Matches Found");
        matchBtn.setOnAction(e -> {
            String selectedOrgan = organTypeBox.getValue();
            String selectedBlood = bloodGroupBox.getValue();
            matchTable.getItems().clear();

            if (selectedOrgan == null || selectedBlood == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please select both organ and blood group.");
                alert.show();
                return;
            }

            int matchCount = 0;

            for (ReceiverModel receiver : com.om.Controller.ReceiverDataController.getAllReceivers()) {
                boolean organMatch = false;
                if (receiver.getOrgans() != null) {
                    for (String o : receiver.getOrgans()) {
                        if (o != null && o.trim().equalsIgnoreCase(selectedOrgan.trim())) {
                            organMatch = true;
                            break;
                        }
                    }
                }

                boolean bloodMatch = receiver.getBloodGroup() != null &&
                        receiver.getBloodGroup().trim().equalsIgnoreCase(selectedBlood.trim());

                if (organMatch && bloodMatch) {
                    matchTable.getItems().add(new MatchEntry(
                            receiver.getName(),
                            selectedOrgan,
                            receiver.getUrgency() != null ? receiver.getUrgency() : "Not Specified"));
                    matchCount++;
                }
            }

            if (matchCount == 0) {
                matchTable.getItems().add(new MatchEntry("No Match Found", "-", "-"));
            }
        });

        Button refreshBtn = new Button("🔄 Refresh");

        matchBtn.setStyle(
                "-fx-background-color: #ff6b6b; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10;");
        refreshBtn.setStyle(
                "-fx-background-color: #ff6b6b; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10;");

        HBox buttonBox = new HBox(50, matchBtn, refreshBtn);
        buttonBox.setPadding(new Insets(20));

        refreshBtn.setOnAction(e -> matchTable.getItems().clear());

        root.getChildren().addAll(topBar, filterBox, matchTable, buttonBox);
        return root;
    }
}
