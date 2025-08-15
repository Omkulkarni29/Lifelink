package com.om.Controller;

import com.google.cloud.firestore.Firestore;
import com.google.cloud.storage.*;
import com.google.firebase.cloud.FirestoreClient;
import com.google.cloud.Timestamp;

import java.io.File;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.UUID;

public class ReceiverReportcontroller {

    ConfigLoader configLoader = new ConfigLoader();
    String apiKey = configLoader.getApiKey();
    String bucketId = configLoader.getBucketId();

    // Use apiKey and bucketId where needed

    // Upload medical report to Firebase Storage
    public String uploadFileToStorage(File file, String uid) {

        try {
            Storage storage = StorageOptions.getDefaultInstance().getService();

            // File naming: receiverReports/{uid}_{UUID}_{fileName}
            String fileName = "receiverReports/" + uid + "_" + UUID.randomUUID() + "_" + file.getName();
            System.out.println(">> Uploading to bucket: " + bucketId);
            System.out.println(">> File absolute path: " + file.getAbsolutePath());
            System.out.println(">> Uploading to path: " + fileName);

            BlobId blobId = BlobId.of(bucketId, fileName);
            BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                    .setContentType(Files.probeContentType(file.toPath()))
                    .build();

            storage.create(blobInfo, Files.readAllBytes(file.toPath()));

            System.out.println(" Uploaded report to Firebase Storage at: " + fileName);

            // Return public URL
            return "https://storage.googleapis.com/" + bucketId + "/" + fileName;

        } catch (Exception e) {
            System.err.println(" File upload failed:");
            e.printStackTrace();
            return null;
        }
    }

    // Save medical report metadata to Firestore
    public boolean saveReportData(String uid, String bloodGroup, String severity, String fileUrl) {
        try {
            if (uid == null || uid.isEmpty())
                return false;
            if (bloodGroup == null || bloodGroup.isEmpty() || severity == null || severity.isEmpty() || fileUrl == null
                    || fileUrl.isEmpty()) {
                System.err.println(" One or more metadata fields are empty.");
                return false;
            }

            Firestore db = FirestoreClient.getFirestore();

            Map<String, Object> data = new HashMap<>();
            data.put("bloodGroup", bloodGroup);
            data.put("severity", severity);
            data.put("reportUrl", fileUrl);
            data.put("timestamp", Timestamp.now());

            db.collection("receiver_reports").document(uid).set(data).get();

            System.out.println("✅ Report metadata saved to Firestore for UID: " + uid);
            return true;

        } catch (InterruptedException | ExecutionException e) {
            System.err.println(" Failed to save Firestore metadata:");
            e.printStackTrace();
            return false;
        }
    }
}
