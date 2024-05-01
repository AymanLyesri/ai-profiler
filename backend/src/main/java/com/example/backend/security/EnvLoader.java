package com.example.backend.security;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class EnvLoader {

    public static Properties loadEnv() throws IOException {
        Properties properties = new Properties();
        FileInputStream fis = new FileInputStream(".env");
        properties.load(fis);
        fis.close();
        return properties;
    }

//    public static void main(String[] args) throws IOException {
//        Properties env = loadEnv();
//        String openaiApiKey = env.getProperty("OPENAI_API_KEY");
//        System.out.println(openaiApiKey);
//    }
}
