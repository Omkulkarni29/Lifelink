package com.om.View.AdminScreens;

import com.om.Controller.UserVerificationController;
import com.om.Controller.ViewController;
import com.om.model.UserVerificationModel; // Using the specified UserVerificationModel

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.util.concurrent.CompletableFuture;
import java.util.ArrayList;
import java.util.List; // Needed for List

public class Userverification {

    // Declaring UI components at the class level for broader access
    private TableView<UserVerificationModel> userTable;
    private ProgressIndicator loadingIndicator;

    public VBox UserVerificationScreen(ViewController controller) {

        // --- Header Section ---
        Button backArrow = new Button("←");
        backArrow.setFont(Font.font("Georgia", 26));
        backArrow.setStyle("-fx-background-color: transparent; -fx-text-fill: black;");
        backArrow.setOnAction(e -> controller.showAdminDashboardScreen());

        Label header = new Label("🛂 Pending Verification Requests");
        header.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #000000;");

        HBox headerBox = new HBox(10, backArrow, header);
        headerBox.setPadding(new Insets(10));
        headerBox.setAlignment(Pos.CENTER_LEFT); // Align header to left

        // --- Loading Indicator (placed centrally above the table) ---
        loadingIndicator = new ProgressIndicator();
        loadingIndicator.setMaxSize(50, 50);
        loadingIndicator.setVisible(false); // Hidden by default

        // --- Table for Pending Users ---
        userTable = new TableView<>();
        userTable.setPlaceholder(new Label("No pending verification requests.")); // Message when table is empty
        userTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); // Auto-resize columns

        // Define Table Columns, linking to UserVerificationModel's getters via PropertyValueFactory
        TableColumn<UserVerificationModel, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<UserVerificationModel, String> roleCol = new TableColumn<>("Role");
        roleCol.setCellValueFactory(new PropertyValueFactory<>("role"));

        TableColumn<UserVerificationModel, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<UserVerificationModel, String> bloodCol = new TableColumn<>("Blood Group");
        bloodCol.setCellValueFactory(new PropertyValueFactory<>("bloodGroup"));

        TableColumn<UserVerificationModel, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        
        // Optional: Add other fields as needed for admin review
        TableColumn<UserVerificationModel, String> dobCol = new TableColumn<>("DOB");
        dobCol.setCellValueFactory(new PropertyValueFactory<>("dob"));

        TableColumn<UserVerificationModel, String> genderCol = new TableColumn<>("Gender");
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));

        TableColumn<UserVerificationModel, String> mobileCol = new TableColumn<>("Mobile");
        mobileCol.setCellValueFactory(new PropertyValueFactory<>("mobileNumber"));

        TableColumn<UserVerificationModel, List<String>> organsCol = new TableColumn<>("Organs");
        organsCol.setCellValueFactory(new PropertyValueFactory<>("organs"));
        // Custom cell factory for List<String> to display them nicely
        organsCol.setCellFactory(tc -> new TableCell<UserVerificationModel, List<String>>() {
            @Override
            protected void updateItem(List<String> items, boolean empty) {
                super.updateItem(items, empty);
                if (empty || items == null || items.isEmpty()) {
                    setText(null);
                } else {
                    setText(String.join(", ", items));
                }
            }
        });


        // Action column for individual approve/reject buttons
        TableColumn<UserVerificationModel, Void> actionsCol = new TableColumn<>("Actions");
        actionsCol.setPrefWidth(200); // Increased width to fit buttons
        actionsCol.setResizable(false);
        actionsCol.setSortable(false);
        actionsCol.setCellValueFactory(param -> null); // Not tied to a model property directly

        actionsCol.setCellFactory(tc -> new TableCell<UserVerificationModel, Void>() {
            private final Button approveBtn = new Button("Approve");
            private final Button rejectBtn = new Button("Reject");
            private final HBox pane = new HBox(5, approveBtn, rejectBtn); // Spacing for buttons

            { // Initializer block for the cell
                approveBtn.setStyle("-fx-background-color: #32CD32; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 10px;");
                rejectBtn.setStyle("-fx-background-color: #FF6347; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 10px;");
                
                // Set button actions
                approveBtn.setOnAction(event -> {
                    UserVerificationModel user = getTableView().getItems().get(getIndex());
                    processUserVerification(user, true, ""); // Approve action
                });

                rejectBtn.setOnAction(event -> {
                    UserVerificationModel user = getTableView().getItems().get(getIndex());
                    // Prompt admin for a rejection reason
                    TextInputDialog dialog = new TextInputDialog();
                    dialog.setTitle("Reject User");
                    dialog.setHeaderText("Reject User: " + user.getName());
                    dialog.setContentText("Please enter reason for rejection (optional):"); // Made optional in text
                    dialog.showAndWait().ifPresent(reason -> {
                        processUserVerification(user, false, reason); // Reject action
                    });
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null); // No buttons if cell is empty
                } else {
                    setGraphic(pane); // Show buttons if cell has content
                }
            }
        });

        // Add all desired columns to the table
        userTable.getColumns().addAll(nameCol, roleCol, emailCol, mobileCol, dobCol, genderCol, bloodCol, organsCol, statusCol, actionsCol);


        // --- Bulk Action Buttons (Optional, but good for admin) ---
        Button approveSelectedBtn = new Button("✅ Approve Selected");
        Button rejectSelectedBtn = new Button("❌ Reject Selected");

        approveSelectedBtn.setStyle("-fx-background-color: #32CD32; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 16px;");
        rejectSelectedBtn.setStyle("-fx-background-color: #FF6347; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 16px;");

        HBox bulkActionBox = new HBox(15, approveSelectedBtn, rejectSelectedBtn);
        bulkActionBox.setPadding(new Insets(10, 0, 0, 0));
        bulkActionBox.setAlignment(Pos.CENTER); // Center the bulk action buttons

        // Enable multiple selection in TableView
        userTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        // Bulk Approve listener
        approveSelectedBtn.setOnAction(e -> {
            List<UserVerificationModel> selectedUsers = new ArrayList<>(userTable.getSelectionModel().getSelectedItems());
            if (selectedUsers.isEmpty()) {
                showAlert("No Selection", "Please select users to approve.");
                return;
            }
            // Process each selected user
            selectedUsers.forEach(user -> processUserVerification(user, true, ""));
            userTable.getSelectionModel().clearSelection(); // Clear selection after processing
        });

        // Bulk Reject listener
        rejectSelectedBtn.setOnAction(e -> {
            List<UserVerificationModel> selectedUsers = new ArrayList<>(userTable.getSelectionModel().getSelectedItems());
            if (selectedUsers.isEmpty()) {
                showAlert("No Selection", "Please select users to reject.");
                return;
            }
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Bulk Reject Users");
            dialog.setHeaderText("Reject Selected Users");
            dialog.setContentText("Please enter a common reason for rejection (optional):");
            dialog.showAndWait().ifPresent(reason -> {
                selectedUsers.forEach(user -> processUserVerification(user, false, reason));
                userTable.getSelectionModel().clearSelection(); // Clear selection after processing
            });
        });

        // --- Root Layout Assembly ---
        VBox root = new VBox(15);
        root.setPadding(new Insets(30));
        root.setStyle("-fx-background-color: lightpink;");
        root.setAlignment(Pos.TOP_CENTER); // Center content within the root

        root.getChildren().addAll(
                headerBox,
                new Separator(),
                loadingIndicator,
                userTable,
                bulkActionBox
        );

        // --- Load pending users when the screen is initialized ---
        loadPendingUsers();

        return root;
    }

    /**
     * Fetches pending users from Firestore and updates the TableView.
     */
    private void loadPendingUsers() {
        loadingIndicator.setVisible(true); // Show loading indicator
        userTable.getItems().clear(); // Clear existing items in table

        CompletableFuture.supplyAsync(UserVerificationController::getPendingUsers)
            .thenAccept(users -> Platform.runLater(() -> {
                userTable.getItems().addAll(users); // Add fetched users to the table
                loadingIndicator.setVisible(false); // Hide loading indicator
                if (users.isEmpty()) {
                    userTable.setPlaceholder(new Label("No pending verification requests."));
                }
            }))
            .exceptionally(ex -> {
                // Handle any errors during fetching gracefully
                Platform.runLater(() -> {
                    loadingIndicator.setVisible(false);
                    showAlert("Error", "Failed to load pending users: " + ex.getMessage());
                    userTable.setPlaceholder(new Label("Error loading data. Please check console for details."));
                    ex.printStackTrace(); // Print full stack trace for debugging
                });
                return null;
            });
    }

    /**
     * Processes the approval or rejection of a single user.
     * @param user The UserVerificationModel object representing the user to process.
     * @param approved True to approve, false to reject.
     * @param reason The rejection reason (only used if 'approved' is false).
     */
    private void processUserVerification(UserVerificationModel user, boolean approved, String reason) {
        String actionDescription = approved ? "Approving" : "Rejecting";
        showAlert("Processing", actionDescription + " " + user.getName() + "..."); // Immediate feedback

        CompletableFuture.supplyAsync(() ->
                UserVerificationController.updateUserApprovalStatus(user.getUid(), user.getRole(), approved, reason))
            .thenAccept(success -> Platform.runLater(() -> {
                if (success) {
                    showAlert("Success", user.getName() + " has been " + (approved ? "approved." : "rejected."));
                    loadPendingUsers(); // Reload the list to remove the processed user
                } else {
                    showAlert("Error", "Failed to " + (approved ? "approve" : "reject") + " " + user.getName() + ". Please try again.");
                }
            }))
            .exceptionally(ex -> {
                Platform.runLater(() -> {
                    showAlert("Error", "An error occurred during processing: " + ex.getMessage());
                    ex.printStackTrace(); // Print full stack trace for debugging
                });
                return null;
            });
    }

    // Helper method for showing simple alert dialogs
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
