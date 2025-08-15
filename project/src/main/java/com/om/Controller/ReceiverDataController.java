package com.om.Controller;

import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;

import com.om.model.ReceiverModel;

import java.util.ArrayList;
import java.util.List;

public class ReceiverDataController {

    public static List<ReceiverModel> getAllReceivers() {
        List<ReceiverModel> receiverList = new ArrayList<>();
        try {
            Firestore db = FirestoreClient.getFirestore();
            QuerySnapshot docs = db.collection("receivers").get().get();
            for (QueryDocumentSnapshot doc : docs) {
                String name = doc.getString("name");
                String bloodGroup = doc.getString("bloodGroup");
                List<String> organs = (List<String>) doc.get("organs");
                String urgency = doc.getString("urgency");

                receiverList.add(new ReceiverModel(name, bloodGroup, organs, urgency));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return receiverList;
    }
}
