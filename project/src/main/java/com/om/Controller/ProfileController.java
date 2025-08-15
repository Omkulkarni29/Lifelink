package com.om.Controller;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.om.model.UserProfileModel;

import java.util.HashMap;
import java.util.Map;

public class ProfileController {

    public static UserProfileModel getUserProfile(String uid) {
        Firestore db = FirestoreClient.getFirestore();
        try {
            // Check donors first
            DocumentSnapshot doc = db.collection("donors").document(uid).get().get();
            if (doc.exists()) {
                return createUserProfileFromDoc(doc);
            }

            // If not a donor, check receivers
            doc = db.collection("receivers").document(uid).get().get();
            if (doc.exists()) {
                return createUserProfileFromDoc(doc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static UserProfileModel createUserProfileFromDoc(DocumentSnapshot doc) {
        return new UserProfileModel(
            doc.getString("name"),
            doc.getString("email"),
            doc.getString("username"),
            doc.getString("mobileNumber"),
            doc.getString("dob"),
            doc.getString("gender"),
            doc.getString("bloodGroup")
        );
    }

    public static ApiFuture<WriteResult> updateUserProfile(String uid, String name, String username, String mobileNumber) {
        Firestore db = FirestoreClient.getFirestore();
        // This assumes the document exists and you know whether it's in donors or receivers.
        // For simplicity, we'll try updating the donor document. A better approach would be to know the user's role.
        DocumentReference docRef = db.collection("donors").document(uid); 

        Map<String, Object> updates = new HashMap<>();
        updates.put("name", name);
        updates.put("username", username);
        updates.put("mobileNumber", mobileNumber);

        return docRef.update(updates);
    }
}
