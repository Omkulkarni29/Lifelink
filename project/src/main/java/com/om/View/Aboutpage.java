package com.om.View;

import org.checkerframework.checker.units.qual.t;

import com.om.Controller.ViewController;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Aboutpage {

     public BorderPane AboutpageScreen(ViewController controller, Runnable showDashboardScreen) {
        BorderPane mainbox = new BorderPane();
        mainbox.setStyle("-fx-background-color: lightpink; -fx-font-family: 'Inter', 'Segoe UI', sans-serif;");

        // SIDEBAR - Exact copy from documents.java
        VBox sidebar = new VBox(20);
        sidebar.setPadding(new Insets(20, 15, 20, 15));
        sidebar.setStyle("-fx-background-color: #f5f9ff; -fx-pref-width: 280px;");
        sidebar.setAlignment(Pos.TOP_CENTER);

        Label sidebarTitle = new Label("e-help Desk");
        sidebarTitle.setFont(Font.font("Inter", FontWeight.BOLD, 24));
        sidebarTitle.setStyle("-fx-text-fill: #1e3a8a; -fx-padding: 10px 0 30px 0;");

        Label profileIcon = new Label("👤");
        profileIcon.setFont(Font.font("System", 36));
        Label profileLabel = new Label("Profile");
        profileLabel.setFont(Font.font("Inter", FontWeight.SEMI_BOLD, 15));
        profileLabel.setStyle("-fx-text-fill: #4b5563;");
        HBox pBox = new HBox(15, profileIcon, profileLabel);
        pBox.setAlignment(Pos.CENTER_LEFT);
        pBox.setStyle("-fx-padding: 12px 15px; -fx-background-radius: 10px;");
        pBox.setOnMouseEntered(event -> pBox.setStyle("-fx-padding: 12px 15px; -fx-background-radius: 10px; -fx-background-color: #eef2ff;"));
        pBox.setOnMouseExited(event -> pBox.setStyle("-fx-padding: 12px 15px; -fx-background-radius: 10px; -fx-background-color: transparent;"));
        pBox.setOnMouseClicked(event -> {});

        VBox navButtons = new VBox(10);

        // Navigation Buttons - Exact copy from documents.java
        HBox navBtn1 = new HBox(15, new Label("📄"), new Label("Legal Case Management"));
        navBtn1.getChildren().get(0).setStyle("-fx-font-size: 24px;");
        navBtn1.getChildren().get(1).setStyle("-fx-font-size: 15px; -fx-font-weight: 500; -fx-text-fill: #4b5563;");
        navBtn1.setAlignment(Pos.CENTER_LEFT);
        navBtn1.setStyle("-fx-padding: 12px 15px; -fx-background-radius: 10px; -fx-background-color: transparent;");
        navBtn1.setCursor(Cursor.HAND);
        navBtn1.setOnMouseEntered(event -> navBtn1.setStyle("-fx-padding: 12px 15px; -fx-background-radius: 10px; -fx-background-color: #eef2ff;"));
        navBtn1.setOnMouseExited(event -> navBtn1.setStyle("-fx-padding: 12px 15px; -fx-background-radius: 10px; -fx-background-color: transparent;"));

        HBox navBtn2 = new HBox(15, new Label("📜"), new Label("Document & Certificate"));
        navBtn2.getChildren().get(0).setStyle("-fx-font-size: 24px;");
        navBtn2.getChildren().get(1).setStyle("-fx-font-size: 15px; -fx-font-weight: 500; -fx-text-fill: #4b5563;");
        navBtn2.setAlignment(Pos.CENTER_LEFT);
        navBtn2.setStyle("-fx-padding: 12px 15px; -fx-background-radius: 10px; -fx-background-color: transparent;");
        navBtn2.setCursor(Cursor.HAND);
        navBtn2.setOnMouseEntered(event -> navBtn2.setStyle("-fx-padding: 12px 15px; -fx-background-radius: 10px; -fx-background-color: #eef2ff;"));
        navBtn2.setOnMouseExited(event -> navBtn2.setStyle("-fx-padding: 12px 15px; -fx-background-radius: 10px; -fx-background-color: transparent;"));

        HBox navBtn3 = new HBox(15, new Label("🏠"), new Label("Land & Property Services"));
        navBtn3.getChildren().get(0).setStyle("-fx-font-size: 24px;");
        navBtn3.getChildren().get(1).setStyle("-fx-font-size: 15px; -fx-font-weight: 500; -fx-text-fill: #4b5563;");
        navBtn3.setAlignment(Pos.CENTER_LEFT);
        navBtn3.setStyle("-fx-padding: 12px 15px; -fx-background-radius: 10px; -fx-background-color: transparent;");
        navBtn3.setCursor(Cursor.HAND);
        navBtn3.setOnMouseEntered(event -> navBtn3.setStyle("-fx-padding: 12px 15px; -fx-background-radius: 10px; -fx-background-color: #eef2ff;"));
        navBtn3.setOnMouseExited(event -> navBtn3.setStyle("-fx-padding: 12px 15px; -fx-background-radius: 10px; -fx-background-color: transparent;"));

        HBox navBtn4 = new HBox(15, new Label("⇄"), new Label("RTI & Grievance"));
        navBtn4.getChildren().get(0).setStyle("-fx-font-size: 24px;");
        navBtn4.getChildren().get(1).setStyle("-fx-font-size: 15px; -fx-font-weight: 500; -fx-text-fill: #4b5563;");
        navBtn4.setAlignment(Pos.CENTER_LEFT);
        navBtn4.setStyle("-fx-padding: 12px 15px; -fx-background-radius: 10px; -fx-background-color: transparent;");
        navBtn4.setCursor(Cursor.HAND);
        navBtn4.setOnMouseEntered(event -> navBtn4.setStyle("-fx-padding: 12px 15px; -fx-background-radius: 10px; -fx-background-color: #eef2ff;"));
        navBtn4.setOnMouseExited(event -> navBtn4.setStyle("-fx-padding: 12px 15px; -fx-background-radius: 10px; -fx-background-color: transparent;"));

        HBox navBtn5 = new HBox(15, new Label("📚"), new Label("Legal Knowledge Base"));
        navBtn5.getChildren().get(0).setStyle("-fx-font-size: 24px;");
        navBtn5.getChildren().get(1).setStyle("-fx-font-size: 15px; -fx-font-weight: 500; -fx-text-fill: #4b5563;");
        navBtn5.setAlignment(Pos.CENTER_LEFT);
        navBtn5.setStyle("-fx-padding: 12px 15px; -fx-background-radius: 10px; -fx-background-color: transparent;");
        navBtn5.setCursor(Cursor.HAND);
        navBtn5.setOnMouseEntered(event -> navBtn5.setStyle("-fx-padding: 12px 15px; -fx-background-radius: 10px; -fx-background-color: #eef2ff;"));
        navBtn5.setOnMouseExited(event -> navBtn5.setStyle("-fx-padding: 12px 15px; -fx-background-radius: 10px; -fx-background-color: transparent;"));

        // Add "About Us" navigation item as active
        HBox navBtn6 = new HBox(15, new Label("ℹ"), new Label("About Us"));
        navBtn6.getChildren().get(0).setStyle("-fx-font-size: 24px;");
        navBtn6.getChildren().get(1).setStyle("-fx-font-size: 15px; -fx-font-weight: 600; -fx-text-fill: #3b82f6;");
        navBtn6.setAlignment(Pos.CENTER_LEFT);
        navBtn6.setStyle("-fx-padding: 12px 15px; -fx-background-radius: 10px; -fx-background-color: #e0e7ff;");
        navBtn6.setCursor(Cursor.HAND);

        navButtons.getChildren().addAll(navBtn1, navBtn2, navBtn3, navBtn4, navBtn5, navBtn6);

        Region sidebarSpacer = new Region();
        VBox.setVgrow(sidebarSpacer, Priority.ALWAYS);

        HBox links = new HBox(15);
        links.setAlignment(Pos.CENTER);
        Label contact = new Label("Contact");
        Label terms = new Label("Terms");
        Label privacy = new Label("Privacy");
        String footerStyle = "-fx-font-size: 13px; -fx-text-fill: #6b7280;";
        contact.setStyle(footerStyle);
        terms.setStyle(footerStyle);
        privacy.setStyle(footerStyle);
        links.getChildren().addAll(contact, terms, privacy);

        

        // MAIN CONTENT - Exact structure from documents.java
        VBox mainContent = new VBox(25);
        mainContent.setPadding(new Insets(20, 40, 40, 40));
        mainContent.setStyle("-fx-background-color: transparent;");

        // Top Navigation Bar - Exact copy from documents.java
        HBox topNav = new HBox(30);
        topNav.setAlignment(Pos.CENTER_LEFT);

       
        Region topNavSpacer = new Region();
        HBox.setHgrow(topNavSpacer, Priority.ALWAYS);

        Button notificationButton = new Button("🔔");
        notificationButton.setFont(Font.font("System", 14));
        notificationButton.setStyle("-fx-background-color: transparent; -fx-border-color: #d1d5db; -fx-border-width: 1.5px; -fx-border-radius: 8px; -fx-background-radius: 8px; -fx-padding: 8px 20px;");
        notificationButton.setCursor(Cursor.HAND);
        notificationButton.setOnMouseEntered(event -> notificationButton.setStyle("-fx-background-color: #f6f3f3; -fx-border-color: #d1d5db; -fx-border-width: 1.5px; -fx-border-radius: 8px; -fx-background-radius: 8px; -fx-padding: 8px 20px;"));
        notificationButton.setOnMouseExited(event -> notificationButton.setStyle("-fx-background-color: transparent; -fx-border-color: #d1d5db; -fx-border-width: 1.5px; -fx-border-radius: 8px; -fx-background-radius: 8px; -fx-padding: 8px 20px;"));

        Button logoutButton = new Button("Back");
        logoutButton.setFont(Font.font("Inter", FontWeight.BOLD, 14));
        logoutButton.setStyle("-fx-background-color: #f63b3b; -fx-background-radius: 8px; -fx-text-fill: white; -fx-padding: 8px 20px;");
        logoutButton.setCursor(Cursor.HAND);
        logoutButton.setOnMouseEntered(event -> logoutButton.setStyle("-fx-background-color: #eb2525; -fx-background-radius: 8px; -fx-text-fill: white; -fx-padding: 8px 20px;"));
        logoutButton.setOnMouseExited(event -> logoutButton.setStyle("-fx-background-color: #f63b3b; -fx-background-radius: 8px; -fx-text-fill: white; -fx-padding: 8px 20px;"));
        logoutButton.setOnAction(e->{
            showDashboardScreen.run();
        });

        HBox loginButtons = new HBox(10, logoutButton);
        loginButtons.setAlignment(Pos.CENTER);

        topNav.getChildren().addAll( topNavSpacer, loginButtons);

        // Main Title - Same style as documents.java
        Label mainTitle = new Label("About Us");
        mainTitle.setFont(Font.font("Inter", FontWeight.BOLD, 32));
        mainTitle.setStyle("-fx-text-fill: #1e3a8a; -fx-padding: 10px 0;");

        // Content container - Same structure as documents.java
        VBox content = new VBox(30);
        content.setPadding(new Insets(20, 0, 0, 0));

        // Core2Web Logo and Header Section
        VBox logoSection = new VBox(15);
        HBox logoContainer = new HBox(20);
        logoContainer.setAlignment(Pos.CENTER_LEFT);

        // Core2Web Logo
        
            ImageView logoImage = new ImageView();
            logoImage.setImage(new Image("asset\\core2web.png"));
            logoImage.setFitWidth(150);
            logoImage.setFitHeight(150);
            logoImage.setPreserveRatio(true);
            logoImage.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 8, 0.3, 0, 0);");
            logoContainer.getChildren().add(logoImage);
        

        VBox titleSection = new VBox(5);
        Label companyTitle = new Label("Core2Web");
        companyTitle.setFont(Font.font("Inter", FontWeight.BOLD, 28));
        companyTitle.setStyle("-fx-text-fill: #1e3a8a;");

        Label tagline = new Label("Empowering Learners, Building Careers");
        tagline.setFont(Font.font("Inter", FontWeight.SEMI_BOLD, 18));
        tagline.setStyle("-fx-text-fill: #2563eb;");

        titleSection.getChildren().addAll(companyTitle, tagline);
        logoContainer.getChildren().add(titleSection);
        logoSection.getChildren().add(logoContainer);

        // About Section with Enhanced Content
        VBox aboutSection = new VBox(20);
        Label aboutTitle = new Label("About Core2Web");
        aboutTitle.setFont(Font.font("Inter", FontWeight.SEMI_BOLD, 20));
        aboutTitle.setStyle("-fx-text-fill: #1e3a8a;");

        VBox aboutBox = new VBox(20);
        aboutBox.setStyle("-fx-background-color: rgba(255, 255, 255, 0.8); -fx-border-color: #a0b3d7; -fx-border-width: 1; -fx-border-radius: 15; -fx-background-radius: 15; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0.3, 0, 2);");
        aboutBox.setPadding(new Insets(30));
        aboutBox.setMinHeight(250);

        Label aboutDesc = new Label(
            "Core2Web is India's premier career transformation platform dedicated to bridging the gap between academic learning and industry requirements. " +
            "Founded with a vision to democratize quality tech education, we have successfully trained over 10,000+ students and helped them secure " +
            "positions in top companies like TCS, Infosys, Wipro, Accenture, and many startups.\n" +
            "We specialize in providing comprehensive training programs in Data Structures & Algorithms, Java Programming, Full Stack Web Development, " +
            "System Design, and Interview Preparation.  curriculum is designed by industry experts and updated regularly to match current market demands.\n" +
            "What sets us apart is  practical approach to learning - every concept is taught through real-world projects, coding challenges, and " +
            "hands-on exercises that prepare students for actual industry scenarios."
        );
        aboutDesc.setFont(Font.font("Inter", FontWeight.NORMAL, 16));
        aboutDesc.setStyle("-fx-text-fill: #374151; -fx-line-spacing: 5px;");
        aboutDesc.setWrapText(true);

        // Statistics Section
        HBox statsBox = new HBox(30);
        statsBox.setAlignment(Pos.CENTER);
        

        aboutBox.getChildren().addAll(aboutDesc, statsBox);
        aboutSection.getChildren().addAll(aboutTitle, aboutBox);

        // Mission & Vision Section Enhanced
        VBox missionSection = new VBox(20);
        Label missionTitle = new Label(" Mission & Vision");
        missionTitle.setFont(Font.font("Inter", FontWeight.SEMI_BOLD, 20));
        missionTitle.setStyle("-fx-text-fill: #1e3a8a;");

        HBox missionVisionContainer = new HBox(25);
        
        // Mission Box
        VBox missionBox = new VBox(15);
        missionBox.setStyle("-fx-background-color: rgba(255, 255, 255, 0.8); -fx-border-color: #a0b3d7; -fx-border-width: 1; -fx-border-radius: 15; -fx-background-radius: 15; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0.3, 0, 2);");
        missionBox.setPadding(new Insets(25));
        missionBox.setMinHeight(200);
        HBox.setHgrow(missionBox, Priority.ALWAYS);

        HBox missionHeader = new HBox(10);
        missionHeader.setAlignment(Pos.CENTER_LEFT);
        Label missionIcon = new Label("🎯");
        missionIcon.setFont(Font.font("System", 24));
        Label missionSubTitle = new Label("Mission");
        missionSubTitle.setFont(Font.font("Inter", FontWeight.BOLD, 18));
        missionSubTitle.setStyle("-fx-text-fill: #2563eb;");
        missionHeader.getChildren().addAll(missionIcon, missionSubTitle);

        Label missionText = new Label(
            "To bridge the gap between theoretical concepts and industry expectations by providing practical, hands-on learning experiences. " +
            "We are committed to making quality tech education accessible to everyone, regardless of their background, and ensuring every student " +
            "gains the confidence and skills needed for a successful tech career."
        );
        missionText.setFont(Font.font("Inter", FontWeight.NORMAL, 14));
        missionText.setStyle("-fx-text-fill: #4b5563; -fx-line-spacing: 3px;");
        missionText.setWrapText(true);

        missionBox.getChildren().addAll(missionHeader, missionText);

        // Vision Box
        VBox visionBox = new VBox(15);
        visionBox.setStyle("-fx-background-color: rgba(255, 255, 255, 0.8); -fx-border-color: #a0b3d7; -fx-border-width: 1; -fx-border-radius: 15; -fx-background-radius: 15; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0.3, 0, 2);");
        visionBox.setPadding(new Insets(25));
        HBox.setHgrow(visionBox, Priority.ALWAYS);

        HBox visionHeader = new HBox(10);
        visionHeader.setAlignment(Pos.CENTER_LEFT);
        Label visionIcon = new Label("🚀");
        visionIcon.setFont(Font.font("System", 24));
        Label visionSubTitle = new Label("Vision");
        visionSubTitle.setFont(Font.font("Inter", FontWeight.BOLD, 18));
        visionSubTitle.setStyle("-fx-text-fill: #2563eb;");
        visionHeader.getChildren().addAll(visionIcon, visionSubTitle);

        Label visionText = new Label(
            "To become India's leading platform for tech education and career transformation, creating a community of skilled professionals " +
            "ready to innovate and lead in the digital world. We envision a future where every aspiring developer has access to world-class " +
            "education and mentorship to achieve their career goals."
        );
        visionText.setFont(Font.font("Inter", FontWeight.NORMAL, 14));
        visionText.setStyle("-fx-text-fill: #4b5563; -fx-line-spacing: 3px;");
        visionText.setWrapText(true);

        visionBox.getChildren().addAll(visionHeader, visionText);

        missionVisionContainer.getChildren().addAll(missionBox, visionBox);
        missionSection.getChildren().addAll(missionTitle, missionVisionContainer);

        // Founder Section Enhanced
        VBox founderSection = new VBox(20);
        Label founderTitle = new Label("Meet  Founder");
        founderTitle.setFont(Font.font("Inter", FontWeight.SEMI_BOLD, 20));
        founderTitle.setStyle("-fx-text-fill: #1e3a8a;");

        VBox sirsectionBox = new VBox(20);
        sirsectionBox.setPadding(new Insets(30));
        sirsectionBox.setStyle("-fx-background-color: rgba(255, 255, 255, 0.8); -fx-border-color: #a0b3d7; -fx-border-width: 1; -fx-border-radius: 15; -fx-background-radius: 15; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0.3, 0, 2);");

        HBox sirsection = new HBox(30);
        sirsection.setAlignment(Pos.CENTER_LEFT);

        // Shashi Sir Image with enhanced styling
        VBox imageContainer = new VBox();
        imageContainer.setAlignment(Pos.CENTER);
        try {
            Image im1 = new Image("asset\\ShashiSir.png");
            ImageView sirpng = new ImageView(im1);
            sirpng.setFitHeight(200);
            sirpng.setPreserveRatio(true);
            sirpng.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 15, 0.5, 0, 0);");
            imageContainer.getChildren().add(sirpng);
        } catch (Exception e) {
            Label fallbackImage = new Label("👨‍💼");
            fallbackImage.setFont(Font.font("System", 100));
            fallbackImage.setStyle("-fx-text-fill: #3b82f6;");
            imageContainer.getChildren().add(fallbackImage);
        }
        sirsection.getChildren().add(imageContainer);

        // Shashi Sir Info Enhanced
        VBox sirInfo = new VBox(15);
        HBox.setHgrow(sirInfo, Priority.ALWAYS);

        Label sirName = new Label("Shashi Bagal");
        sirName.setFont(Font.font("Inter", FontWeight.BOLD, 26));
        sirName.setStyle("-fx-text-fill: #1e3a8a;");

        Label sirTitle1 = new Label("Founder & CEO, Core2Web");
        sirTitle1.setFont(Font.font("Inter", FontWeight.SEMI_BOLD, 18));
        sirTitle1.setStyle("-fx-text-fill: #2563eb;");

        Label sirDesc = new Label(
            "Shashi Bagal is a visionary educator and technology leader with over 10 years of experience in software development, " +
            "training, and mentoring. His jney began as a software engineer at leading IT companies, where he recognized the gap " +
            "between academic curriculum and industry requirements.\n\n" +
            "This realization led him to establish Core2Web with a mission to provide practical, industry-relevant education. " +
            "Under his leadership, Core2Web has grown from a small training center to India's most trusted platform for tech education. " +
            "His unique teaching methodology, combining theoretical concepts with real-world applications, has helped thousands of students " +
            "transition into successful tech careers.\n\n" +
            "Shashi believes in the power of personalized mentoring and ensures that every student receives individual attention and guidance " +
            "throughout their learning jney."
        );
        sirDesc.setFont(Font.font("Inter", FontWeight.NORMAL, 14));
        sirDesc.setStyle("-fx-text-fill: #4b5563; -fx-line-spacing: 4px;");
        sirDesc.setWrapText(true);

        // Achievements section for founder
        VBox achievementsBox = new VBox(8);
        Label achievementsTitle = new Label("Key Achievements:");
        achievementsTitle.setFont(Font.font("Inter", FontWeight.SEMI_BOLD, 14));
        achievementsTitle.setStyle("-fx-text-fill: #1e3a8a;");

        Label achievement1 = new Label("• Trained 10,000+ students with 95% placement success rate");
        Label achievement2 = new Label("• Industry expert in Java, Data Structures, and System Design");
        Label achievement3 = new Label("• Regular speaker at tech conferences and workshops");
        Label achievement4 = new Label("• Mentor to 500+ software engineers currently working in top companies");

        String achievementStyle = "-fx-font-size: 13px; -fx-text-fill: #059669; -fx-font-weight: 500;";
        achievement1.setStyle(achievementStyle);
        achievement2.setStyle(achievementStyle);
        achievement3.setStyle(achievementStyle);
        achievement4.setStyle(achievementStyle);

        achievementsBox.getChildren().addAll(achievementsTitle, achievement1, achievement2, achievement3, achievement4);

        sirInfo.getChildren().addAll(sirName, sirTitle1, sirDesc, achievementsBox);
        sirsection.getChildren().add(sirInfo);
        sirsectionBox.getChildren().add(sirsection);
        founderSection.getChildren().addAll(founderTitle, sirsectionBox);

       

        // Team Mentors Section Enhanced
      

        // Team Members Section Enhanced
        VBox teamSection = new VBox(20);
        Label teamTitle = new Label(" Development Team");
        teamTitle.setFont(Font.font("Inter", FontWeight.SEMI_BOLD, 20));
        teamTitle.setStyle("-fx-text-fill: #1e3a8a;");

        HBox teamContainer = new HBox(20);
        teamContainer.setAlignment(Pos.CENTER);

        // Enhanced Team Member Cards
        VBox member1 = createEnhancedTeamMemberCard("👩‍💻", "Om Kulkarni", "(Team Leader)", 
            "", "", "");
        VBox member2 = createEnhancedTeamMemberCard("👨‍💻", "Rohan More", "", 
            "", "", "");
        VBox member3 = createEnhancedTeamMemberCard("👩‍🎨", "Parth Ghotane", "", 
            "", "", "");
        VBox member4 = createEnhancedTeamMemberCard("👩‍🎨", "Rohan Ingale", "", "", "", "");
        teamContainer.getChildren().addAll(member1, member2, member3,member4);
        teamSection.getChildren().addAll(teamTitle, teamContainer);

        // Add all sections to content
        content.getChildren().addAll(logoSection, founderSection,aboutSection, missionSection,  
              teamSection);

        // Add everything to main content
        mainContent.getChildren().addAll(topNav, mainTitle, content);

        // Create ScrollPane and wrap the main content
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(mainContent);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setStyle("-fx-background-color: transparent; -fx-background: transparent;");
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        
        // Smooth scrolling
        scrollPane.getStyleClass().add("edge-to-edge");
        scrollPane.setStyle(scrollPane.getStyle() + "-fx-focus-color: transparent; -fx-faint-focus-color: transparent;");

        mainbox.setCenter(scrollPane);

        return mainbox;
    }

    // Helper method to create statistics cards
    private VBox createStatCard(String number, String label, String icon) {
        VBox statCard = new VBox(5);
        statCard.setAlignment(Pos.CENTER);
        statCard.setPadding(new Insets(15));
        statCard.setStyle("-fx-background-color: rgba(255, 255, 255, 0.9); -fx-border-color: #e5e7eb; -fx-border-width: 1; -fx-border-radius: 10; -fx-background-radius: 10;");
        statCard.setPrefWidth(120);

        Label statIcon = new Label(icon);
        statIcon.setFont(Font.font("System", 20));

        Label statNumber = new Label(number);
        statNumber.setFont(Font.font("Inter", FontWeight.BOLD, 18));
        statNumber.setStyle("-fx-text-fill: #2563eb;");

        Label statLabel = new Label(label);
        statLabel.setFont(Font.font("Inter", FontWeight.NORMAL, 12));
        statLabel.setStyle("-fx-text-fill: #6b7280;");
        statLabel.setWrapText(true);
        statLabel.setAlignment(Pos.CENTER);

        statCard.getChildren().addAll(statIcon, statNumber, statLabel);
        return statCard;
    }

    // Helper method to create enhanced program cards
    private VBox createEnhancedProgramCard(String icon, String title, String details, String duration) {
        VBox programCard = new VBox(12);
        programCard.setPadding(new Insets(20));
        programCard.setStyle("-fx-background-color: rgba(255, 255, 255, 0.9); -fx-border-color: #cbd5e1; -fx-border-width: 1; -fx-border-radius: 15; -fx-background-radius: 15; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 8, 0.3, 0, 2);");
        programCard.setPrefWidth(220);
        programCard.setAlignment(Pos.TOP_CENTER);

        Label programIcon = new Label(icon);
        programIcon.setFont(Font.font("System", 32));

        Label programTitle = new Label(title);
        programTitle.setFont(Font.font("Inter", FontWeight.BOLD, 14));
        programTitle.setStyle("-fx-text-fill: #1e3a8a;");
        programTitle.setWrapText(true);
        programTitle.setAlignment(Pos.CENTER);

        Label programDetail = new Label(details);
        programDetail.setFont(Font.font("Inter", FontWeight.NORMAL, 12));
        programDetail.setStyle("-fx-text-fill: #6b7280;");
        programDetail.setWrapText(true);
        programDetail.setAlignment(Pos.CENTER);

        Label programDur = new Label("Duration: " + duration);
        programDur.setFont(Font.font("Inter", FontWeight.SEMI_BOLD, 11));
        programDur.setStyle("-fx-text-fill: #059669;");

        programCard.getChildren().addAll(programIcon, programTitle, programDetail, programDur);
        return programCard;
    }

    // Helper method to create enhanced mentor cards
    private VBox createEnhancedMentorCard(String icon, String name, String role, String experience, String skills) {
        VBox mentorCard = new VBox(12);
        mentorCard.setPadding(new Insets(25));
        mentorCard.setStyle("-fx-background-color: rgba(255, 255, 255, 0.9); -fx-border-color: #cbd5e1; -fx-border-width: 1; -fx-border-radius: 15; -fx-background-radius: 15; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0.3, 0, 2);");
        mentorCard.setPrefWidth(280);
        mentorCard.setAlignment(Pos.CENTER);

        Label mentorIcon = new Label(icon);
        mentorIcon.setFont(Font.font("System", 50));

        Label mentorName = new Label(name);
        mentorName.setFont(Font.font("Inter", FontWeight.BOLD, 18));
        mentorName.setStyle("-fx-text-fill: #1e3a8a;");

        Label mentorRole = new Label(role);
        mentorRole.setFont(Font.font("Inter", FontWeight.SEMI_BOLD, 14));
        mentorRole.setStyle("-fx-text-fill: #2563eb;");

        Label mentorExp = new Label(experience);
        mentorExp.setFont(Font.font("Inter", FontWeight.NORMAL, 13));
        mentorExp.setStyle("-fx-text-fill: #6b7280;");

        Label mentorSkills = new Label(skills);
        mentorSkills.setFont(Font.font("Inter", FontWeight.NORMAL, 12));
        mentorSkills.setStyle("-fx-text-fill: #059669;");
        mentorSkills.setWrapText(true);
        mentorSkills.setAlignment(Pos.CENTER);

        mentorCard.getChildren().addAll(mentorIcon, mentorName, mentorRole, mentorExp, mentorSkills);
        return mentorCard;
    }

    // Helper method to create enhanced team member cards
    private VBox createEnhancedTeamMemberCard(String icon, String name, String role, String skills, String experience, String specialty) {
        VBox memberCard = new VBox(10);
        memberCard.setPadding(new Insets(20));
        memberCard.setStyle("-fx-background-color: rgba(255, 255, 255, 0.9); -fx-border-color: #cbd5e1; -fx-border-width: 1; -fx-border-radius: 12; -fx-background-radius: 12; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 8, 0.3, 0, 2);");
        memberCard.setPrefWidth(220);
        memberCard.setAlignment(Pos.CENTER);

        Label memberIcon = new Label(icon);
        memberIcon.setFont(Font.font("System", 40));

        Label memberName = new Label(name);
        memberName.setFont(Font.font("Inter", FontWeight.BOLD, 16));
        memberName.setStyle("-fx-text-fill: #1e3a8a;");

        Label memberRole = new Label(role);
        memberRole.setFont(Font.font("Inter", FontWeight.BOLD, 13));
        memberRole.setStyle("-fx-text-fill: #2563eb;");

        Label memberSkills = new Label(skills);
        memberSkills.setFont(Font.font("Inter", FontWeight.NORMAL, 11));
        memberSkills.setStyle("-fx-text-fill: #059669;");
        memberSkills.setWrapText(true);
        memberSkills.setAlignment(Pos.CENTER);

        Label memberExp = new Label(experience + "  " + specialty);
        memberExp.setFont(Font.font("Inter", FontWeight.NORMAL, 12));
        memberExp.setStyle("-fx-text-fill: #6b7280;");

        memberCard.getChildren().addAll(memberIcon, memberName, memberRole, memberSkills, memberExp);
        return memberCard;
    }

   
}
