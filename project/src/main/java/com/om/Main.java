package com.om;

import com.om.Controller.FirebaseSDK;
import com.om.Controller.ViewController;
import com.om.View.DashboardUI;
import com.om.View.LoginPageUI;
import com.om.View.SignupUI;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{
    

    @Override
    public void start(Stage myStage) throws Exception {
        FirebaseSDK.initialize();
        ViewController view =new ViewController(myStage);
        myStage.setTitle("LifeLink Project");
        // myStage.setFullScreen(true); 
        myStage.show();

        
    }
    public static void main(String[] args) {
        System.out.println("Hello world!");
        launch(args);
    }
}