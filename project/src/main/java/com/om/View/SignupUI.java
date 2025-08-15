package com.om.View;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.om.Controller.AuthController;
import com.om.Controller.SignupController;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SignupUI  {
        
        public HBox SignupScreen(com.om.Controller.ViewController controller){
            SignupController signupController = new SignupController(controller);

        // Left Side (Logo & Text)
        Label title = new Label("LifeLink");
        title.setFont(Font.font("Arial", 80));
        title.setStyle("-fx-text-fill: linear-gradient(to right, #ff0000, #ff7f00); -fx-font-weight: bold;");

        ImageView heartImage = new ImageView(new Image("asset/Life.png"));
        heartImage.setFitWidth(180);
        heartImage.setPreserveRatio(true);

        Label slogan = new Label("Every Link Saves a Life");
        slogan.setFont(Font.font("Arial", 20));
        slogan.setStyle("-fx-text-fill: linear-gradient(to right, #ff0000, #ff7f00); -fx-font-weight: bold;");

        VBox leftPane = new VBox(20, title, heartImage, slogan);
        leftPane.setStyle("-fx-background-color: lightpink;");
        leftPane.setAlignment(Pos.CENTER);
        leftPane.setPadding(new Insets(20));

        // Labels + Fields neatly aligned
        Label createLabel = new Label("Create your Account");
        createLabel.setFont(Font.font("Arial", 45));
        createLabel.setStyle("-fx-text-fill: linear-gradient(to right, #ff0000, #ff7f00); -fx-font-weight: bold;");

        Text Name = new Text("Name");
        Name.setStyle("-fx-font-size: 18px; -fx-fill: linear-gradient(to right, #ff0000, #ff7f00);");
        TextField tx= new TextField();
        tx.setFocusTraversable(false);
        Text email = new Text("email");
        email.setStyle("-fx-font-size: 18px; -fx-fill: linear-gradient(to right, #ff0000, #ff7f00);");
        TextField tx1= new TextField();
        tx1.setFocusTraversable(false);
        Text password = new Text("Password");
        password.setStyle("-fx-font-size: 18px; -fx-fill: linear-gradient(to right, #ff0000, #ff7f00);");
        PasswordField tx2 = new PasswordField();
        tx2.setFocusTraversable(false);
        Text pass = new Text("Confirm Password");
        pass.setStyle("-fx-font-size: 18px; -fx-fill: linear-gradient(to right, #ff0000, #ff7f00);");
        PasswordField tx3 = new PasswordField();
        tx3.setFocusTraversable(false);
        Label resultLabel = new Label();
        resultLabel.setStyle(" -fx-font-weight: bold;");


        VBox formFields = new VBox(15,Name,tx,email,tx1,password,tx2,pass,tx3);
        formFields.setAlignment(Pos.TOP_LEFT);

        

        Button signupButton = new Button("SignUp");
        signupButton.setStyle(
            "-fx-background-color: #ff1e00ff; " +
            "-fx-text-fill: white; " +
            "-fx-font-size: 14px; " +
            "-fx-pref-width: 200px;"
        );

        signupButton.setOnAction(evt -> {
                 signupController.handleSignup(tx, tx1, tx2, tx3);
        });;

        

        Hyperlink loginLink = new Hyperlink("Already have an Account? Login");
        loginLink.setStyle("-fx-text-fill: black; -fx-font-size: 16px");
        loginLink.setOnAction(evt -> controller.showloginScreen());

        Hyperlink AdminloginLink = new Hyperlink("Admin Login ?");
        AdminloginLink.setStyle("-fx-text-fill: black; -fx-font-size: 16px");
        AdminloginLink.setOnAction(evt -> controller.showAdminloginScreen());

        VBox formContent = new VBox(15, createLabel, formFields, signupButton, loginLink,AdminloginLink,resultLabel);
        formContent.setAlignment(Pos.TOP_LEFT);
        formContent.setPadding(new Insets(30));
        formContent.setPrefWidth(300);

        VBox loginCard = new VBox(formContent);
        loginCard.setAlignment(Pos.CENTER);
        
        loginCard.setPadding(new Insets(30));
        loginCard.setStyle(
            "-fx-background-color: #f8f8f8; " +
            "-fx-background-radius: 20; " +
            "-fx-border-radius: 20; " +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0.5, 0, 0);"
        );

        VBox rightPane = new VBox(loginCard);
        rightPane.setAlignment(Pos.CENTER);
        rightPane.setPadding(new Insets(40, 100, 40, 40));
        rightPane.setStyle("-fx-background-color: lightpink;");

        // Root Layout
        HBox root = new HBox(leftPane, rightPane);
        HBox.setHgrow(leftPane, Priority.ALWAYS);
        HBox.setHgrow(rightPane, Priority.ALWAYS);
        leftPane.setMaxWidth(Double.MAX_VALUE);
        rightPane.setMaxWidth(Double.MAX_VALUE);

        return root;
    }

    // Utility to build label + field with consistent spacing
    private VBox createInput(String labelText, TextField input) {
        Text label = new Text(labelText);
        label.setStyle("-fx-font-size: 16px; -fx-fill: linear-gradient(to right, #ff0000, #ff7f00);");
        input.setPromptText(labelText);
        input.setMaxWidth(350);
        return new VBox(5, label, input);
    }
    
}

