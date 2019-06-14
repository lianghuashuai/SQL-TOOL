package com.sql.tool.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "report")
@EnableConfigurationProperties(Config.class)
@Data
public class Config {
    private Map<String, String> keymap = new HashMap<>();

}
