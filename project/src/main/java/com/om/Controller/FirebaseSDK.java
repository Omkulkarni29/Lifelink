package com.om.Controller;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.io.InputStream;
import java.io.IOException;

public class FirebaseSDK {

    public static void initialize() {
        try {
            ConfigLoader configLoader = new ConfigLoader();
            String json_file = configLoader.getjson();
            InputStream serviceAccount = FirebaseSDK.class.getResourceAsStream("/" + json_file);

            if (serviceAccount == null) {
                throw new IOException(json_file + " not found on the classpath. Make sure it's in src/main/resources.");
            }

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setStorageBucket("javafxfirebase-eb545.firebasestorage.app") // ✅ must match
                    .build();
            FirebaseApp.initializeApp(options);

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
                System.out.println("Firebase initialized successfully.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
