package com.om.Controller;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.google.cloud.firestore.DocumentSnapshot;

public class AdminController {

    public static String getAdminEmail() {
        try {
            Firestore db = FirestoreClient.getFirestore();
            // Adjust collection/doc name as per Firestore structure
            DocumentSnapshot snapshot = db.collection("admin").document("adminDetails").get().get();
            return snapshot.getString("email");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
