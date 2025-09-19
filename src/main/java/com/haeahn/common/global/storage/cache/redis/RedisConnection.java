package com.haeahn.common.global.storage.cache.redis;

import com.haeahn.common.global.config.properties.PropertiesReader;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

//@Slf4j
@Configuration
@EnableCaching
@RequiredArgsConstructor
public class RedisConnection {
    private final PropertiesReader propertiesReader;

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory(); // Spring이 자동으로 afterPropertiesSet 호출
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        String host = propertiesReader.getProperty("database.redis.default.host");
        int port = Integer.parseInt(propertiesReader.getProperty("database.redis.default.port"));;
        int database = Integer.parseInt(propertiesReader.getProperty("database.redis.default.database"));

        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(host);
        redisStandaloneConfiguration.setPort(port);
        redisStandaloneConfiguration.setDatabase(database);
//        redisStandaloneConfiguration.setUsername(pusername);
//        redisStandaloneConfiguration.setPassword(password);
        LettuceConnectionFactory connectionFactory = new LettuceConnectionFactory(redisStandaloneConfiguration);
        connectionFactory.afterPropertiesSet();

// --------------
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        // Key-Value 형태로 직렬화
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());
        // Hash Key-Value 형태로 직렬화
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new StringRedisSerializer());
        return template;
    }
}