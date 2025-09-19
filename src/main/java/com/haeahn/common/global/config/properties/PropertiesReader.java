package com.haeahn.common.global.config.properties;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
@RequiredArgsConstructor
public class PropertiesReader {
    private final Environment env;

    public String getProperty(String key) {
        return this.env.getProperty(key);
    }

    public String getProperty(String... keys) {
        return this.env.getProperty(String.join(".", keys));
    }
}
