package com.github.zabbum.pimanager.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.naming.ConfigurationException;
import java.io.IOException;

@Configuration
public class ConfigLoader {

    @Bean
    public Config loadConfiguration() throws ConfigurationException {
        ObjectMapper objectMapper = new ObjectMapper();
        ClassPathResource configResource = new ClassPathResource("config.json");
        if (!configResource.exists()) {
            throw new ConfigurationException("Could not find config.json");
        }
        try {
            return objectMapper.readValue(configResource.getInputStream(), Config.class);

        } catch (IOException e) {
            throw new RuntimeException("Could not read config.json", e);
        }
    }
}
