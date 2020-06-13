package com.imcode.base_gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "com.imcode.gateway")
@Data
public class CustomProperties {

    private String nacosAddr;

}
