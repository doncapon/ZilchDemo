package org.zilch.com.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationManager {
    private  Properties configProperties = new Properties();

    public ConfigurationManager(String configFileName) {
        try (FileInputStream fis = new FileInputStream("src/main/resources/config/"+configFileName+".properties")) {
            configProperties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load configuration properties");
        }
    }
    // Method to retrieve proper from config file with default value if needed
    public String getConfigProperty(String key, String defaultValue) {
        return configProperties.getProperty(key, defaultValue);
    }

    // Method to get a property value from the config file
    public String getConfigProperty(String key) {
        return configProperties.getProperty(key);
    }
}
