package com.haeahn.common.global.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "app.jwt")
public class AuthTokenProperties {
//    private Map<String, String> expiration;
    private List<String> whitelistUrls;
    private List<String> adminOnlyUrls;
}
