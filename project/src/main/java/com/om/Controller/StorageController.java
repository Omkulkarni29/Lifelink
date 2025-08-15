package com.om.Controller;

import com.google.cloud.storage.*;
import java.io.File;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.util.UUID;

public class StorageController {

    ConfigLoader configLoader = new ConfigLoader();

    String apiKey = configLoader.getApiKey();
    String bucketId = configLoader.getBucketId();

    // Use apiKey and bucketId where needed

    public String uploadFile(File file, String uid) {
        try {
            // Initialize Firebase Storage
            Storage storage = StorageOptions.getDefaultInstance().getService();

            // Unique file path in your bucket
            String fileName = "receiverMedicalReports/" + uid + "_" + UUID.randomUUID() + "_" + file.getName();

            // Debug output
            System.out.println("🟡 Uploading file to Firebase Storage:");
            System.out.println("├─ Bucket: " + bucketId);
            System.out.println("├─ Path: " + fileName);
            System.out.println("├─ Original file: " + file.getAbsolutePath());

            // Create the blob ID and blob info
            BlobId blobId = BlobId.of(bucketId, fileName);
            BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                    .setContentType(Files.probeContentType(file.toPath()))
                    .build();

            // Upload the file
            storage.create(blobInfo, Files.readAllBytes(file.toPath()));

            System.out.println(" File upload success!");

            // Return the public URL
            String downloadUrl = "https://firebasestorage.googleapis.com/v0/b/" + bucketId + "/o/"
                    + URLEncoder.encode(fileName, "UTF-8") + "?alt=media";
            System.out.println("📎 File accessible at: " + downloadUrl);

            return downloadUrl;

        } catch (Exception e) {
            System.err.println(" Upload failed: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
