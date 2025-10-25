package com.example.legacy.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

@Configuration
public class AppSettingsConfig {
    private static final Logger logger = LoggerFactory.getLogger(AppSettingsConfig.class);

    @Bean
    public AppSettings appSettings(ObjectMapper mapper) {
        ClassPathResource res = new ClassPathResource("appsettings.json");
        try {
            AppSettings settings = mapper.readValue(res.getInputStream(), AppSettings.class);
            logger.info("Loaded appsettings.json: serviceName={} environment={}",
                    settings.getServiceName(), settings.getEnvironment());
            return settings;
        } catch (IOException e) {
            logger.error("Failed to load appsettings.json", e);
            AppSettings fallback = new AppSettings();
            fallback.setServiceName("legacy-service");
            fallback.setEnvironment("local");
            return fallback;
        }
    }
}
