package com.navangs.maribong.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "prop")
public class PropertyConfig {
    private String hostUrl;
}
