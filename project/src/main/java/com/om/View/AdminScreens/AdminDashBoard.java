package com.om.View.AdminScreens;

import com.om.Controller.ViewController;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AdminDashBoard  {

        public BorderPane AdminDashBoardScreen(com.om.Controller.ViewController controller,Runnable showSignupScreen) {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #ffeeee;");

        

        Text dashboardTitle = new Text("🏠 Admin Dashboard");
        dashboardTitle.setFill(Color.web("#140b04ff"));
        dashboardTitle.setFont(Font.font("Arial", FontWeight.BOLD, 24));
    
        

        Label bell = new Label("🔔");
        bell.setFont(Font.font("Arial", 26));
        bell.setOnMouseClicked(e->{
                controller.showNotificationScreen();
        });
        Label user = new Label("👤");
        user.setFont(Font.font("Arial", 26));

       HBox hb = new HBox(20,bell,user);
       HBox hb1 = new HBox(1150,dashboardTitle,hb);
       HBox topBar = new HBox(100,hb1);
       hb.setAlignment(Pos.TOP_RIGHT);
       topBar.setPadding(new Insets(20));
        topBar.setBackground(new Background(new BackgroundFill(
                Color.web("#faf8f8ff"), CornerRadii.EMPTY, Insets.EMPTY
        )));;

        // ===== Separator Line =====
        Separator line = new Separator();
        line.setStyle("-fx-background-color: transparent;");
        line.setPrefHeight(2);
        line.setMaxWidth(Double.MAX_VALUE);
        line.setOpacity(1);
        line.setBorder(new Border(new BorderStroke(Color.web("#090601ff"), BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY, new BorderWidths(2, 0, 0, 0))));

        VBox topSection = new VBox(15,topBar);
        root.setTop(topSection);

        // ===== Right Side Text Menu =====
        VBox rightMenu = new VBox(20);
        rightMenu.setPadding(new Insets(20, 10, 10, 20));
        rightMenu.setPrefWidth(200);
        rightMenu.setStyle("-fx-border-color: red; -fx-border-width: 0 0 0 2px;");
        rightMenu.setAlignment(Pos.TOP_LEFT);

        Text profileText = new Text("Profile");
        profileText.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        profileText.setFill(Color.web("#0c0603ff"));

        Text organText = new Text("Organ Details");
        organText.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        organText.setFill(Color.web("#050301ff"));
        organText.setOnMouseClicked(e->{
                controller.showOrganDetails();
        });

        Text donorText = new Text("Donor Details");
        donorText.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        donorText.setFill(Color.web("#090401ff"));
        donorText.setOnMouseClicked(e->{
                controller.showDonorDetails();
        });

        Text receiverText = new Text("Receiver Details");
        receiverText.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        receiverText.setFill(Color.web("#140b07ff"));
        receiverText.setOnMouseClicked(e->{
                controller.showReceiverDetails();
        });

        Text matchingText = new Text("Matching Details");
        matchingText.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        matchingText.setFill(Color.web("#070300ff"));
        matchingText.setOnMouseClicked(e->{
                controller.showMatchingsystem();
        });
        Text about = new Text("About us");
        about.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        about.setFill(Color.web("#090401ff"));
        about.setOnMouseClicked(e->{
                controller.showAboutpageScreen();
        });


        Text Logout = new Text("Logout ");
        Logout.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        Logout.setFill(Color.web("#070402ff"));
        Logout.setOnMouseClicked(e->{
                showSignupScreen.run();
        });

        rightMenu.getChildren().addAll( organText, donorText, receiverText, matchingText,Logout);
        root.setRight(rightMenu);

        // ===== Center Grid =====
        GridPane centerGrid = new GridPane();
        centerGrid.setPadding(new Insets(30));
        centerGrid.setHgap(80);
        centerGrid.setVgap(80);
        centerGrid.setStyle("-fx-font-size: 30px; -fx-font-family: 'Arial';");
        centerGrid.setAlignment(Pos.TOP_CENTER);

        // Organ Box
        StackPane organBox = createStyledBox("Organ Details", "#ff6666");
        centerGrid.add(organBox, 0, 0);
        organBox.setMinSize(350, 100);
        organBox.setOnMouseClicked(e->{
                controller.showOrganDetails();
        });
        

        // Donor Box
        StackPane donorBox = createStyledBox("Donor Details", "#ff6666");
        centerGrid.add(donorBox, 1, 0);
        donorBox.setMinSize(350, 100);
        donorBox.setOnMouseClicked(e->{
                controller.showDonorDetails();
        });

        // Receiver Box
        StackPane receiverBox = createStyledBox("Receiver Details", "#ff6666");
        centerGrid.add(receiverBox, 0, 1);
        receiverBox.setMinSize(350, 100);
        receiverBox.setOnMouseClicked(e->{
                controller.showReceiverDetails();
        });

        // Matching Box
        StackPane matchingBox = createStyledBox("Matching Details", "#ff6666");
        centerGrid.add(matchingBox, 1, 1);
        matchingBox.setMinSize(350, 100);
        matchingBox.setOnMouseClicked(e->{
                controller.showMatchingsystem();
        });

        // Verification Box
        StackPane verificationBox = createStyledBox("Verification of Users", "#ff3300");
        verificationBox.setMinSize(450, 150);
        centerGrid.add(verificationBox, 0, 2, 2, 1);
        verificationBox.setOnMouseClicked(e->{
                controller.showUserVerificationScreen();
        });

        root.setCenter(centerGrid);

        return root;
    }

    private StackPane createStyledBox(String labelText, String borderColor) {
        StackPane box = new StackPane();
        box.setMinSize(180, 80);
        box.setStyle(
                "-fx-background-color: #fff3f3;" +
                        "-fx-border-color: " + borderColor + ";" +
                        "-fx-border-radius: 12;" +
                        "-fx-background-radius: 12;" +
                        "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 6, 0, 0, 2);" +
                        "-fx-cursor: hand;"
        );

        Text label = new Text(labelText);
        label.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        label.setFill(Color.web(borderColor));
        box.getChildren().add(label);

        // Hover effect
        box.setOnMouseEntered(e -> box.setStyle(
                "-fx-background-color: #ffe6e6;" +
                        "-fx-border-color: " + borderColor + ";" +
                        "-fx-border-radius: 12;" +
                        "-fx-background-radius: 12;" +
                        "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 10, 0, 0, 4);" +
                        "-fx-cursor: hand;"
        ));

        box.setOnMouseExited(e -> box.setStyle(
                "-fx-background-color: #fff3f3;" +
                        "-fx-border-color: " + borderColor + ";" +
                        "-fx-border-radius: 12;" +
                        "-fx-background-radius: 12;" +
                        "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 6, 0, 0, 2);" +
                        "-fx-cursor: hand;"
        ));

        return box;
    }


   
}