package com.om.Controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

public class AuthController {

    ConfigLoader configLoader = new ConfigLoader();
    String apiKey = configLoader.getApiKey();
    String bucketId = configLoader.getBucketId();
    // Use apiKey and bucketId where needed

    public String signUp(String email1, String password1) {
        try {
            URL url = new URL("https://identitytoolkit.googleapis.com/v1/accounts:signUp?key=" + apiKey);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            String payloadString = "{\"email\":\"%s\",\"password\":\"%s\",\"returnSecureToken\": true}"
                    .formatted(email1, password1);

            try (OutputStream os = conn.getOutputStream()) {
                os.write(payloadString.getBytes());
            }

            return readResponse(conn);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String signIn(String email, String password) {
        try {
            // 🔐 Firebase Auth REST API endpoint for sign-in
            URL url = new URL("https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=" + apiKey);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            // 📦 Prepare JSON payload
            String payload = String.format(
                    "{\"email\":\"%s\", \"password\":\"%s\", \"returnSecureToken\": true}",
                    email, password);

            // 🔼 Send payload
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = payload.getBytes();
                os.write(input, 0, input.length);
            }

            // 📥 Read response
            int responseCode = conn.getResponseCode();
            InputStreamReader reader;
            if (responseCode >= 200 && responseCode < 300) {
                reader = new InputStreamReader(conn.getInputStream());
            } else {
                reader = new InputStreamReader(conn.getErrorStream());
            }

            BufferedReader in = new BufferedReader(reader);
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = in.readLine()) != null) {
                response.append(line);
            }

            in.close();

            System.out.println("🔐 Firebase Login Response: " + response);
            return response.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String readResponse(HttpsURLConnection conn) throws Exception {
        int responseCode = conn.getResponseCode();
        BufferedReader reader;
        if (responseCode >= 200 && responseCode < 300) {
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        System.out.println("Firebase Auth Response: " + response.toString());
        return response.toString();
    }

}