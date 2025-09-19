//package com.haeahn.common.global.storage.cache.redis.reference;
//
//import com.haeahn.common.global.config.env.ConfigReader;
//import lombok.RequiredArgsConstructor;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
//import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
//import java.util.HashMap;
//import java.util.Map;
//
////@Slf4j
//@Configuration
//@EnableCaching
//@RequiredArgsConstructor
//public class MultiRedisConnection {
//    private final MultiRedisConfig redisConfig;
//
////    @Bean
////    public LettuceConnectionFactory redisConnectionFactory() {
////        return new LettuceConnectionFactory(); // Spring이 자동으로 afterPropertiesSet 호출
////    }
//
////    --------------------------------
////    1)
////@Bean
////public RedisConnectionFactory redisConnectionFactory() {
////    return new LettuceConnectionFactory(); // 자동 초기화됨
////}
////    2)
////    spring:
////      data:
////       redis:
////          host: localhost
////          port: 6379
////          database: 0
////    1, 2 사용 시 > 자동 초기화 (LettuceConnectionFactory를 생성 + afterPropertiesSet() 실행)
////    --------------------------------
//
//
//    //  Lettuce 사용
////    @Bean
//    public RedisConnectionFactory redisConnectionFactory(String templateName) {
////        RedisConfig.config config = Optional.ofNullable(
////                        redisConfig.getTemplates())
////                .map(m -> m.get(template))
////                .orElseThrow(() -> new IllegalArgumentException("Invalid redis/db: " + template));
//
//        MultiRedisConfig.RedisProperties property = redisConfig.getProperty(templateName);
//        if (property == null) {
//            throw new IllegalArgumentException("Redis configuration not found for template: " + templateName);
//        }
//
//        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
//        redisStandaloneConfiguration.setHostName(property.getHost());
//        redisStandaloneConfiguration.setPort(property.getPort());
////        database 번호 설정 (0~15), 기본값: 0
//        redisStandaloneConfiguration.setDatabase(property.getDatabase());
////        redisStandaloneConfiguration.setUsername(pusername);
////        redisStandaloneConfiguration.setPassword(password);
////        return new LettuceConnectionFactory(redisStandaloneConfiguration);
//
//        LettuceConnectionFactory connectionFactory = new LettuceConnectionFactory(redisStandaloneConfiguration);
//
////        Bean 설정 후 초기화 작업 실행 시
//        connectionFactory.afterPropertiesSet();
//        return connectionFactory;
//    }
//
//    //  Redis Template 사용 - 직렬화 제공, serializer 설정으로 redis-cli를 통해 직접 데이터를 조회할 수 있도록 설정
////    @Bean
//    public RedisTemplate<String, Object> redisTemplate(String templateName) {
//        RedisTemplate<String, Object> template = new RedisTemplate<>();
//        template.setConnectionFactory(redisConnectionFactory(templateName));
//
//        // Key-Value 형태로 직렬화
//        template.setKeySerializer(new StringRedisSerializer());
//        template.setValueSerializer(new StringRedisSerializer()); // 또는 Jackson2JsonRedisSerializer 등
//        // Hash Key-Value 형태로 직렬화
//        template.setHashKeySerializer(new StringRedisSerializer());
//        template.setHashValueSerializer(new StringRedisSerializer());
////        // 기본적으로 직렬화를 수행
////        template.setDefaultSerializer(new StringRedisSerializer());
//
////        Bean 설정 후 초기화 작업 실행 시
//        template.afterPropertiesSet();
//        return template;
//    }
//
//    @Bean
//    public Map<String, RedisTemplate<String, Object>> redisTemplates() {
//        Map<String, RedisTemplate<String, Object>> templates = new HashMap<>();
//
//        for (String key : redisConfig.getInstance().keySet()) {
//            System.out.println("redis key : " + key);
//            RedisTemplate<String, Object> template = redisTemplate(key);
//            templates.put(key, template);
//        }
//        return templates;
//    }
//}