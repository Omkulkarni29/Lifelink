package com.om.Controller;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.om.model.UserVerificationModel; // Using the specified UserVerificationModel

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class UserVerificationController {

    // Helper method to create a UserVerificationModel from a Firestore document
    private static UserVerificationModel createUserVerificationModelFromDoc(QueryDocumentSnapshot doc, String role) {
        return new UserVerificationModel(
            doc.getId(), // Document ID as UID
            doc.getString("name"),
            doc.getString("email"),
            doc.getString("username"),
            doc.getString("mobileNumber"),
            doc.getString("dob"),
            doc.getString("gender"),
            doc.getString("bloodGroup"),
            (List<String>) doc.get("organs"), // Cast for List<String>
            role, // Role ("Donor" or "Receiver")
            // Ensure these fields exist in Firestore, or handle nulls in your model constructor
            doc.getBoolean("isApproved") != null ? doc.getBoolean("isApproved") : false, 
            doc.getString("status") != null ? doc.getString("status") : "pending", 
            doc.getString("rejectionReason")
        );
    }

    /**
     * Fetches all users (donors and receivers) regardless of their verification status.
     *
     * @return A list of UserVerificationModel objects for all users.
     */
    public static List<UserVerificationModel> getPendingUsers() { // Name remains getPendingUsers for compatibility
        Firestore db = FirestoreClient.getFirestore();
        List<UserVerificationModel> allUsers = new ArrayList<>(); // Changed name to allUsers for clarity
        
        System.out.println("DEBUG: Attempting to fetch all users (donors and receivers) from Firestore...");

        try {
            // Get all donors from 'donors' collection, without filtering by isApproved
            List<QueryDocumentSnapshot> donorDocs = db.collection("donors")
                .get().get().getDocuments(); // Removed .whereEqualTo("isApproved", false)
            System.out.println("DEBUG: Found " + donorDocs.size() + " donor documents.");
            for (QueryDocumentSnapshot doc : donorDocs) {
                System.out.println("DEBUG: Processing donor: " + doc.getId() + " - " + doc.getString("name"));
                allUsers.add(createUserVerificationModelFromDoc(doc, "Donor"));
            }

            // Get all receivers from 'receivers' collection, without filtering by isApproved
            List<QueryDocumentSnapshot> receiverDocs = db.collection("receivers")
                .get().get().getDocuments(); // Removed .whereEqualTo("isApproved", false)
            System.out.println("DEBUG: Found " + receiverDocs.size() + " receiver documents.");
            for (QueryDocumentSnapshot doc : receiverDocs) {
                System.out.println("DEBUG: Processing receiver: " + doc.getId() + " - " + doc.getString("name"));
                allUsers.add(createUserVerificationModelFromDoc(doc, "Receiver"));
            }
            System.out.println("DEBUG: Total users fetched: " + allUsers.size());

        } catch (InterruptedException | ExecutionException e) {
            System.err.println("ERROR: Failed to fetch users from Firestore: " + e.getMessage());
            e.printStackTrace();
        }
        return allUsers; // Return all users, not just pending
    }

    /**
     * Updates a user's approval status in Firestore.
     * @param uid The UID of the user to update.
     * @param role The role of the user ("Donor" or "Receiver") to determine the collection.
     * @param approved True for approval, false for rejection.
     * @param reason Optional rejection reason (only relevant if 'approved' is false).
     * @return True if the update was successful, false otherwise.
     */
    public static boolean updateUserApprovalStatus(String uid, String role, boolean approved, String reason) {
        Firestore db = FirestoreClient.getFirestore();
        String collectionPath = role.equalsIgnoreCase("Donor") ? "donors" : "receivers";

        Map<String, Object> updates = new HashMap<>();
        updates.put("isApproved", approved); 
        updates.put("status", approved ? "approved" : "rejected"); 
        updates.put("rejectionReason", approved ? null : reason); 

        try {
            ApiFuture<WriteResult> future = db.collection(collectionPath).document(uid).update(updates);
            future.get(); 
            return true; 
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error updating user approval status for UID " + uid + ": " + e.getMessage());
            e.printStackTrace();
            return false; 
        }
    }
}
