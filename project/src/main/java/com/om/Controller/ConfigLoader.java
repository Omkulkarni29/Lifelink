package com.om.Controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    private Properties properties = new Properties();

    public void loadProperties() {
        // Try to load credentials.properties from resources
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("credentials.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find credentials.properties");
                return;
            }
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String getApiKey() {
        return properties.getProperty("api_key");
    }

    public String getBucketId() {
        return properties.getProperty("bucket_id");
    }
    public String getjson() {
        return properties.getProperty("json_file");
    }
}
