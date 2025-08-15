package com.om.Controller;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NotificationController {

    private static final Firestore db = FirestoreClient.getFirestore();

    public static List<Map<String, Object>> getNotifications() {
        List<Map<String, Object>> userList = new ArrayList<>();
        try {
            ApiFuture<QuerySnapshot> future = db.collection("users").get();
            QuerySnapshot querySnapshot = future.get();

            System.out.println("Users found: " + querySnapshot.size());

            for (QueryDocumentSnapshot doc : querySnapshot.getDocuments()) {
                Map<String, Object> user = doc.getData();
                
                System.out.println("User ID: " + doc.getId());
                System.out.println("User Data: " + user);
                
                userList.add(user);
            }
        } catch (Exception e) {
            System.err.println("Error fetching users: " + e.getMessage());
            e.printStackTrace();
        }
        return userList;
    }
}
