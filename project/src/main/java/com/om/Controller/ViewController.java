package com.om.Controller;

import com.om.View.Aboutpage;
import com.om.View.DashboardProfile;
import com.om.View.DashboardUI;
import com.om.View.HelpPage;
import com.om.View.LoginPageUI;
import com.om.View.NotificationUI;
//import com.om.View.Notification;
import com.om.View.SignupUI;
import com.om.View.AdminScreens.AdminDashBoard;
import com.om.View.AdminScreens.AdminLoginPageUI;
import com.om.View.AdminScreens.DonorDetails;
import com.om.View.AdminScreens.MatchingSystem;
import com.om.View.AdminScreens.OrganDetails;
import com.om.View.AdminScreens.RecevierDetailsAdmin;
import com.om.View.AdminScreens.Userverification;
import com.om.View.DonorScreens.DonorReg;
import com.om.View.DonorScreens.OrganmedReport;
import com.om.View.DonorScreens.consent_form;
import com.om.View.ReceiverScreens.ReceiverConcentForm;
import com.om.View.ReceiverScreens.ReceiverMedReport;
import com.om.View.ReceiverScreens.ReceiverRegistration;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ViewController  {
         
      private SignupController signupController;
        
      public void setSignupController(SignupController signupController) {
         this.signupController = signupController;
      }
        private Stage mainStage;
        private Scene SignupScene,LoginScene,DashboardScene,AdminloginScene,DonoruiScene,OrganmedReportScene,
        ConsentformScene,DashboardProfileScene,AdminDashBoardScene,userVerificationscene,MatchingSystemScene,
        OrganDetailsScene,RecevierDetailsScene,ReceiverRegistrationScene,ReceiverMedReportScene,ReceiverConcentScene
        ,DonorDetailsScene,AboutpageScene,HelppageScene,NotificationScene;

        public ViewController(Stage viewStage){
            this.mainStage=viewStage;
            showSignupScreen();

        }
         private static String CurrentUserId;

        public static String getCurrentUserId() {
            return CurrentUserId;
         }
        
         public static void setCurrentUserId(String currentUserId) {
            CurrentUserId = currentUserId;
         }
        private void showSignupScreen() {
           SignupUI signup = new SignupUI();
           SignupScene = new Scene(signup.SignupScreen(this),1560,790);
           mainStage.setScene(SignupScene);
        }
        public void showloginScreen() {
           LoginPageUI loginpage = new LoginPageUI();
           LoginScene = new Scene(loginpage.LoginScreen(this, this::showSignupScreen),1560,790);
           mainStage.setScene(LoginScene);
        }
        public void showDashboardScreen() {
           DashboardUI Dashboardpage = new DashboardUI();
           DashboardScene = new Scene(Dashboardpage.DashboardScreen(this,this::showSignupScreen,this::showDonorUiScreen),1560,790);
           mainStage.setScene(DashboardScene);
        }
        public void showAdminloginScreen() {
           AdminLoginPageUI loginpage = new AdminLoginPageUI();
           AdminloginScene = new Scene(loginpage.AdminLoginScreen(this,this::showSignupScreen),1560,790);
           mainStage.setScene(AdminloginScene);
        }
        public void showDonorUiScreen(){
            DonorReg Donorui = new DonorReg();
            DonoruiScene = new Scene(Donorui.DonorUiScreen(this,this::showSignupScreen,this::showDashboardScreen,this::showOrganmedReport),1560,790);
            mainStage.setScene(DonoruiScene);

        }
        public void showOrganmedReport(){
         OrganmedReport medreport = new OrganmedReport();
         OrganmedReportScene = new Scene(medreport.OrganmedReportScreen(this,this::showSignupScreen,this::showDonorUiScreen,this::showDashboardScreen,this::showConsentform),1560,790);
         mainStage.setScene(OrganmedReportScene);
        }
        public void showConsentform(){
         consent_form consent = new consent_form();
         ConsentformScene = new Scene(consent.ConsentformScreen(this,this::showSignupScreen,this::showOrganmedReport,this::showDonorUiScreen,this::showDashboardScreen),1560,790);
         mainStage.setScene(ConsentformScene);

        } 
        public void showDashboardProfileScreen(){
         DashboardProfile profile = new DashboardProfile();
         DashboardProfileScene = new Scene(profile.DashboardProfileScreen(this,this::showSignupScreen,this::showDashboardScreen),1560,790);
         mainStage.setScene(DashboardProfileScene);
        }
        public void showAdminDashboardScreen(){
         AdminDashBoard admin = new AdminDashBoard();
         AdminDashBoardScene = new Scene(admin.AdminDashBoardScreen(this,this::showSignupScreen),1560,790);
         mainStage.setScene(AdminDashBoardScene);
        }
        public void showUserVerificationScreen(){
         Userverification verify = new Userverification();
         userVerificationscene = new Scene(verify.UserVerificationScreen(this),1560,790);
         mainStage.setScene(userVerificationscene);
        }
        public void showMatchingsystem(){
         MatchingSystem match = new MatchingSystem();
         MatchingSystemScene = new Scene(match.MatchingSystemScreen(this),1560,790);
         mainStage.setScene(MatchingSystemScene);
        }
        public void showOrganDetails(){
         OrganDetails organs = new OrganDetails();
         OrganDetailsScene = new Scene(organs.OrganDetailScreen(this),1560,790);
         mainStage.setScene(OrganDetailsScene);
        }
        public void showDonorDetails(){
         DonorDetails donor = new DonorDetails();
         DonorDetailsScene = new Scene(donor.DonorDetailsScreen(this),1560,790);
         mainStage.setScene(DonorDetailsScene);
        }
        public void showReceiverDetails(){
         RecevierDetailsAdmin receive= new RecevierDetailsAdmin();
         RecevierDetailsScene = new Scene(receive.RecevierDetailsScreen(this),1560,790);
         mainStage.setScene(RecevierDetailsScene);
        }
        public void showReceiverRegistrationScreen(){
         ReceiverRegistration registration= new ReceiverRegistration();
         ReceiverRegistrationScene = new Scene(registration.ReceiverRegistrationScreen(this,this::showSignupScreen,this::showDashboardScreen,this::showReceivermedreportScreen),1560,790);
         mainStage.setScene(ReceiverRegistrationScene);
        }
        public void showReceivermedreportScreen(){
         ReceiverMedReport med= new ReceiverMedReport();
         ReceiverMedReportScene = new Scene(med.ReceiverMedReportScreen(this,this::showSignupScreen,this::showReceiverRegistrationScreen,this::showDashboardScreen,this::showReceiverConsentformScreen),1560,790);
         mainStage.setScene(ReceiverMedReportScene);
        }
        public void showReceiverConsentformScreen(){
         ReceiverConcentForm recConsent = new ReceiverConcentForm();
         ReceiverConcentScene = new Scene(recConsent.ReceiverConcentFormScreen(this,this::showSignupScreen,this::showReceivermedreportScreen,this::showReceiverRegistrationScreen,this::showDashboardScreen),1560,790);
         mainStage.setScene(ReceiverConcentScene);

        }
        public void showAboutpageScreen(){
         Aboutpage about= new Aboutpage();
         AboutpageScene = new Scene(about.AboutpageScreen(this,this::showDashboardScreen),1560,790);
         mainStage.setScene(AboutpageScene);

        }
        public void showHelppageScreen(){
         HelpPage help= new HelpPage();
         HelppageScene = new Scene(help.HelppageScreen(this,this::showDashboardScreen),1560,790);
         mainStage.setScene(HelppageScene);

        }
        public void showNotificationScreen() {
            NotificationUI notification = new NotificationUI();
            NotificationScene = new Scene(notification.NotificationsScreen(this), 1560, 790);
            mainStage.setScene(NotificationScene);
        }
        
    }


    

