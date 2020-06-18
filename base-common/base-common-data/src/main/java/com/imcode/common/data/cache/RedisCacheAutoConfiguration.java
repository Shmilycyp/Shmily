//package com.imcode.common.data.cache;
//
//import com.sun.org.apache.regexp.internal.RE;
//import org.springframework.beans.factory.ObjectProvider;
//import org.springframework.boot.autoconfigure.AutoConfigureAfter;
//import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizers;
//import org.springframework.boot.autoconfigure.cache.CacheProperties;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.cache.CacheManager;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.ResourceLoader;
//import org.springframework.data.redis.cache.RedisCacheConfiguration;
//import org.springframework.data.redis.cache.RedisCacheManager;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.lang.Nullable;
//
//@Configuration
//@ConditionalOnBean({RedisConnectionFactory.class}) // 必须先有这个类
//@ConditionalOnMissingBean({CacheManager.class}) // 如果没有这个类的bean
//@AutoConfigureAfter({RedisAutoConfiguration.class}) // 在这个bean初始化之后
//@EnableConfigurationProperties(CacheProperties.class)
//public class RedisCacheAutoConfiguration {
//
//    private final CacheProperties cacheProperties;
//    private final CacheManagerCustomizers customizerInvoker;
//    @Nullable
//    private final RedisCacheConfiguration redisCacheConfiguration;
//
//    RedisCacheAutoConfiguration(CacheProperties cacheProperties,
//                                CacheManagerCustomizers customizerInvoker,
//                                ObjectProvider<RedisCacheConfiguration> redisCacheConfiguration) {
//        this.cacheProperties = cacheProperties;
//        this.customizerInvoker = customizerInvoker;
//        this.redisCacheConfiguration = redisCacheConfiguration.getIfAvailable();
//    }
//
//    @Bean
//    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory, ResourceLoader resourceLoader){
//
//        DefaultRedisCacheWriter redisCacheWriter = new DefaultRedisCacheWriter(connectionFactory);
//
//
//
//
//
//
//
//        return null;
//    }
//
//}
