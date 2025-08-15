package com.om.Controller;

import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import com.om.model.Donormodel;

import java.util.ArrayList;
import java.util.List;

public class DonorDataController {
    public static List<Donormodel> getAllDonors() {
        List<Donormodel> donorList = new ArrayList<>();
        try {
            Firestore db = FirestoreClient.getFirestore();
            QuerySnapshot docs = db.collection("donors").get().get();
            for (QueryDocumentSnapshot doc : docs) {
                String name = doc.getString("name");
                String dob = doc.getString("dob");
                String gender = doc.getString("gender");
                String bloodGroup = doc.getString("bloodGroup");
                List<String> organs = (List<String>) doc.get("organs");
                donorList.add(new Donormodel(name, dob, gender, bloodGroup, organs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return donorList;
    }
}
