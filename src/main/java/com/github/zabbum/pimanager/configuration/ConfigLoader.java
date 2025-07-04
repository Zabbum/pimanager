package com.github.zabbum.pimanager.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/** Class for loading configuration. */
@Configuration
public class ConfigLoader {

  /**
   * Bean for loading configuration.
   *
   * @return Config object.
   */
  @Bean
  public Config loadConfiguration() {
    ObjectMapper objectMapper = new ObjectMapper();
    ClassPathResource configResource = new ClassPathResource("config.json");
    if (!configResource.exists()) {
      throw new RuntimeException("Could not find config.json");
    }
    try {
      return objectMapper.readValue(configResource.getInputStream(), Config.class);

    } catch (IOException e) {
      throw new RuntimeException("Could not read config.json", e);
    }
  }
}
