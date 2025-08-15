package com.om.Controller;

import com.google.firebase.cloud.FirestoreClient;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class ReceiverRegController {

    public boolean saveReceiverData(String uid, String name, String gender, String dob, String bloodGroup, List<String> selectedOrgans) {
        try {
            if (uid == null || uid.isEmpty()) {
                System.err.println("UID is null or empty. Cannot proceed.");
                return false;
            }

            Firestore db = FirestoreClient.getFirestore();

            Map<String, Object> data = new HashMap<>();
            data.put("name", name);
            data.put("dob", dob);
            data.put("gender", gender);
            data.put("bloodGroup", bloodGroup);
            data.put("organs", selectedOrgans);
            data.put("timestamp", Timestamp.now());

            db.collection("receivers").document(uid).set(data).get();
            return true;

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return false;
        }
    }
}
