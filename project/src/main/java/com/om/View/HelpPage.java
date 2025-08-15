package com.om.View;

import com.om.Controller.ViewController;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HelpPage {

        public BorderPane HelppageScreen(ViewController controller,Runnable showDashboardScreen) {
                // Header
                Label bell = new Label("🔔");
                bell.setFont(Font.font("Arial", 36));
                Label user = new Label("👤");
                user.setFont(Font.font("Arial", 36));

                Hyperlink backArrow = new Hyperlink("<-");
                backArrow.setFont(Font.font(20));
                backArrow.setBorder(Border.EMPTY);
                backArrow.setStyle("-fx-text-fill: black; -fx-underline: false;");
                backArrow.setOnAction(e->{
                        showDashboardScreen.run();
                });

                Text heading = new Text("Help");
                heading.setFont(Font.font(28));

                Region spacer = new Region();
                HBox.setHgrow(spacer, Priority.ALWAYS);

                HBox header = new HBox(10, backArrow, heading, spacer, bell, user);
                header.setPadding(new Insets(15));
                header.setStyle("-fx-background-color: white;");
                header.setAlignment(Pos.CENTER_LEFT);
                header.setPrefHeight(60);

                // Left Content
                VBox leftContent = new VBox(25);
                leftContent.setPadding(new Insets(30));
                leftContent.setAlignment(Pos.TOP_LEFT);

                Text helpCenter = new Text("❓ Help Center");
                Text contactSupport = new Text("📞 Contact Support");
                Text aboutUs = new Text("ℹ About Us");
                Text privacy = new Text("📄 Terms & Privacy");
                Text troubleshooting = new Text("🛠 Troubleshooting");
                Text appointment = new Text("📅 Appointment Info");
                Text navigation = new Text("🌐 Website Navigation");

                for (Text txt : new Text[] { helpCenter, contactSupport, aboutUs, privacy, troubleshooting, appointment,
                                navigation }) {
                        txt.setFont(Font.font(18));
                }

                leftContent.getChildren().addAll(helpCenter, contactSupport, aboutUs, privacy, troubleshooting,
                                navigation);

                // Center Message Display
                TextArea messageArea = new TextArea();
                messageArea.setWrapText(true);
                messageArea.setEditable(false);
                messageArea.setFont(Font.font("Georgia", 16));
                messageArea.setPrefWidth(800);
                messageArea.setStyle("-fx-control-inner-background: #fffaf0;");

                // Right Menu
                VBox rightMenu = new VBox(20);
                rightMenu.setPadding(new Insets(20));
                rightMenu.setAlignment(Pos.TOP_CENTER);
                rightMenu.setPrefWidth(200);
              //  rightMenu.setStyle("-fx-background-color: lightpink;");

                Hyperlink profileLink = createHyperlink("Profile");
                Hyperlink accountStatusLink = createHyperlink("Account Status");
                Hyperlink settingsLink = createHyperlink("Settings");
                Hyperlink helpLink = createHyperlink("Help");
                Hyperlink feedbackLink = createHyperlink("Feedback");
                Hyperlink logoutLink = createHyperlink("Logout");

                // rightMenu.getChildren().addAll(
                //                 profileLink, accountStatusLink, settingsLink,
                //                 helpLink, feedbackLink, logoutLink);

                // Set Click Events
                helpCenter.setOnMouseClicked(e -> messageArea.setText(
                                "📌 Need Help? We’ve Got You Covered!\n\n" +
                                                "Welcome to the Help Center of LifeLink — your go-to resource for getting assistance and understanding how to use the platform effectively.\n\n"
                                                +
                                                "🧭 Navigation:\n" +
                                                "- Use the dashboard to access your profile, organ donation records, matching status, and account details.\n"
                                                +
                                                "- Click the sections on the right sidebar to switch between pages like About, Help, and Logout.\n\n"
                                                +
                                                "📝 Common Questions:\n\n" +
                                                "1️⃣ How do I register as a Donor or Receiver?\n→ Use the Registration tab on the main dashboard and fill in the required fields accurately.\n\n"
                                                +
                                                "2️⃣ How is organ matching done?\n→ Our system uses medical and location data to automatically match donors and receivers.\n\n"
                                                +
                                                "3️⃣ How do I update my profile?\n→ Click on the Profile section from the sidebar and use the edit option to update your details.\n\n"
                                                +
                                                "4️⃣ What if I forget my login credentials?\n→ Use the “Forgot Password” option on the login screen to reset via email.\n\n"
                                                +
                                                "📞 Need More Support?\n" +
                                                "📧 Email: support@lifelink.org\n" +
                                                "📱 Helpline: +91-9876543210\n\n" +
                                                "We're here to help you 24x7!"));

                contactSupport.setOnMouseClicked(e -> messageArea.setText(
                                "📞 Contact Support\n\n" +
                                                "If you're facing issues or need assistance, feel free to reach out to us!\n\n"
                                                +
                                                "📧 Email Support:\n" +
                                                "support@lifelink.org\n\n" +
                                                "📱 24x7 Helpline:\n" +
                                                "+91-9876543210\n\n" +
                                                "🌐 Website:\n" +
                                                "www.lifelink.org/help\n\n" +
                                                "🏢 Address:\n" +
                                                "Pune, Maharashtra - 411001\n\n" +
                                                "We're always here to help you with your LifeLink journey. ❤️"));

                aboutUs.setOnMouseClicked(e -> messageArea.setText(
                                "🏥 About LifeLink\n\n" +
                                                "LifeLink is a non-profit platform dedicated to bridging the gap between organ donors and recipients across India. "
                                                +
                                                "Our mission is to make organ donation accessible, transparent, and efficient through technology.\n\n"
                                                +
                                                "💖 Our Vision:\n" +
                                                "To save lives by creating a centralized system that simplifies donor registrations, receiver matching, and healthcare coordination.\n\n"
                                                +
                                                "🌟 What We Do:\n" +
                                                "- Allow individuals to register as organ donors or recipients.\n" +
                                                "- Use intelligent matching algorithms to connect compatible donors and receivers.\n"
                                                +
                                                "- Provide real-time access to organ status, profile management, and support services.\n\n"
                                                +
                                                "👥 Our Team:\n" +
                                                "- A passionate group of engineers, designers, and healthcare professionals.\n"
                                                +
                                                "- Backed by support from medical institutions and public health organizations.\n\n"
                                                +
                                                "📌 Join the movement. Be a Lifesaver. Be a LifeLinker. 💚"));

                privacy.setOnMouseClicked(e -> messageArea.setText(
                                "📄 Terms & Privacy Policy\n\n" +
                                                "By using LifeLink, you agree to abide by our terms and policies. We are committed to protecting your privacy and handling your data responsibly.\n\n"
                                                +
                                                "🔐 Data Privacy:\n" +
                                                "- All personal and medical data is securely stored and used only for donor-receiver matching and health record maintenance.\n"
                                                +
                                                "- We do not sell or share your information with third parties without your consent.\n\n"
                                                +
                                                "📝 User Responsibilities:\n" +
                                                "- You must provide accurate and up-to-date information during registration.\n"
                                                +
                                                "- Misuse or false registration may lead to account suspension.\n\n" +
                                                "📋 Consent:\n" +
                                                "- By submitting your details, you consent to LifeLink processing your data for organ donation-related purposes.\n\n"
                                                +
                                                "📎 Cookies:\n" +
                                                "- LifeLink may use cookies to enhance user experience, track user activity, and store session data securely.\n\n"
                                                +
                                                "⚖️ Legal Compliance:\n" +
                                                "- LifeLink follows Indian laws under the Transplantation of Human Organs and Tissues Act (THOTA).\n\n"
                                                +
                                                "📞 For More Information:\n" +
                                                "Email: privacy@lifelink.org\n" +
                                                "Website: www.lifelink.org/privacy\n\n" +
                                                "Your trust is our responsibility. 💙"));

                troubleshooting.setOnMouseClicked(e -> messageArea.setText(
                                "🛠️ Troubleshooting Guide\n\n" +
                                                "Facing issues while using LifeLink? Here's how you can resolve common problems quickly:\n\n"
                                                +
                                                "1️⃣ ❌ App Not Launching:\n" +
                                                "→ Ensure JavaFX is installed and added to your project setup.\n" +
                                                "→ Try cleaning and rebuilding the project (Maven: mvn clean javafx:run).\n\n"
                                                +
                                                "2️⃣ 📨 Not Receiving Verification Emails:\n" +
                                                "→ Check your spam/junk folder.\n" +
                                                "→ Ensure your email address was entered correctly during registration.\n\n"
                                                +
                                                "3️⃣ 🔒 Can't Login:\n" +
                                                "→ Double-check email and password.\n" +
                                                "→ Use 'Forgot Password' to reset credentials.\n\n" +
                                                "4️⃣ 📤 Signup Form Not Submitting:\n" +
                                                "→ Make sure all required fields are filled correctly.\n" +
                                                "→ Check your internet connection.\n\n" +
                                                "5️⃣ 🧾 Data Not Saving or Displaying:\n" +
                                                "→ Ensure you are connected to the internet.\n" +
                                                "→ Firebase or Firestore might be temporarily down. Try again after some time.\n\n"
                                                +
                                                "📞 Still facing issues?\n" +
                                                "Contact Support:\n" +
                                                "Email: support@lifelink.org\n" +
                                                "Helpline: +91-9876543210\n\n" +
                                                "We’re here to help you 24/7. 🔧"));

                appointment.setOnMouseClicked(e -> messageArea.setText(
                                "📅 Appointment Info:\n\n" +
                                                "• Appointment scheduling is available after medical approval.\n" +
                                                "• Notifications will be sent via SMS and email.\n" +
                                                "• Carry your documents and consent form when visiting.\n\n" +
                                                "📍 Location: Registered LIFELINK hospitals."));

                navigation.setOnMouseClicked(e -> messageArea.setText(
                                "🌐 Website Navigation Guide\n\n" +
                                                "Navigate the LifeLink platform with ease using this quick guide:\n\n" +
                                                "🏠 Home:\n" +
                                                "→ Get an overview of organ donation, platform features, and recent updates.\n\n"
                                                +
                                                "🧍‍♂️ Register:\n" +
                                                "→ Choose to register as a Donor or Receiver.\n" +
                                                "→ Fill out all required personal and medical details carefully.\n\n" +
                                                "📊 Dashboard:\n" +
                                                "→ View your profile, donor/receiver status, and matching updates.\n" +
                                                "→ Access sections like About, Help, and Logout from the right sidebar.\n\n"
                                                +
                                                "📁 Upload Reports:\n" +
                                                "→ Submit required health documents like blood group reports, fitness certificates, etc.\n\n"
                                                +
                                                "🔍 Matching Status:\n" +
                                                "→ View compatible matches found by our intelligent matching system.\n\n"
                                                +
                                                "👤 Profile:\n" +
                                                "→ Update your information and view registration status.\n\n" +
                                                "📞 Support:\n" +
                                                "→ Visit Help Center or Contact pages for assistance.\n\n" +
                                                "💡 Tip: Always save your data before switching tabs to avoid losing progress.\n\n"
                                                +
                                                "Happy Saving Lives with LifeLink 💚"));

                // Main Layout
                BorderPane contentArea = new BorderPane();
                contentArea.setLeft(leftContent);
                contentArea.setCenter(messageArea);
                contentArea.setRight(rightMenu);
                BorderPane.setMargin(leftContent, new Insets(20));
                BorderPane.setMargin(messageArea, new Insets(20));
                BorderPane.setMargin(rightMenu, new Insets(20));

                BorderPane root = new BorderPane();
                root.setTop(header);
                root.setCenter(contentArea);
                root.setStyle("-fx-background-color: #F4C7CC;");

                return root;
        }

        private Hyperlink createHyperlink(String text) {
                Hyperlink link = new Hyperlink(text);
                link.setFont(Font.font(20));
                link.setStyle("-fx-text-fill: black;");
                return link;
        }

}
