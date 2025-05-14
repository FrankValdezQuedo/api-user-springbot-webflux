package com.flcode.api_user_flcode.infrastructure.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;


@Data
@Configuration
@ConfigurationProperties(prefix = "exceptions")

public class ExceptionProperties {
    private Map<String, Map<String, String>> errors;
}
