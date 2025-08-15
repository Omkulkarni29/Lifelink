package com.om.Controller;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class DonorRegController {

    public boolean saveDonorData(String uid, String name, String gender, String dob, String bloodGroup, List<String> selectedOrgans) {
        try {
            if (uid == null || uid.isEmpty()) {
                System.err.println("❌ UID is null or empty - cannot proceed.");
                return false;
            }

            Firestore db = FirestoreClient.getFirestore();

            Map<String, Object> donorData = new HashMap<>();
            donorData.put("name", name);
            donorData.put("gender", gender);
            donorData.put("dob", dob);
            donorData.put("bloodGroup", bloodGroup);
            donorData.put("organs", selectedOrgans);
            donorData.put("timestamp", com.google.cloud.Timestamp.now());

            db.collection("donors").document(uid).set(donorData).get();

            System.out.println("✅ Donor data saved successfully for UID: " + uid);
            return true;

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return false;
        }
    }
}
