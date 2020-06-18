//package com.imcode.common.data.cache;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.data.redis.cache.RedisCache;
//import org.springframework.data.redis.cache.RedisCacheConfiguration;
//import org.springframework.data.redis.cache.RedisCacheManager;
//import org.springframework.data.redis.cache.RedisCacheWriter;
//import org.springframework.util.StringUtils;
//
//import java.time.Duration;
//import java.util.Map;
//
///**
// * 在注解中用分割符 # 来分割名称和过期时间  ->  name#ttl
// */
//@Slf4j
//public class RedisAutoCacheManager extends RedisCacheManager {
//
//
//    /**分隔符*/
//    private static final String SPLIT_FLAG = "#";
//
//    /**缓存名称分隔之后的长度*/
//    private static final int CACHE_LENGTH = 2 ;
//
//
//    RedisAutoCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration,
//                          Map<String, RedisCacheConfiguration> initialCacheConfigurations, boolean allowInFlightCacheCreation) {
//        super(cacheWriter, defaultCacheConfiguration, initialCacheConfigurations, allowInFlightCacheCreation);
//    }
//
//
//    @Override
//    protected RedisCache createRedisCache(String name, RedisCacheConfiguration cacheConfig) {
//
//        // 如果不带分隔符 不处理
//        if(StringUtils.isEmpty(name) || !name.contains(SPLIT_FLAG)){
//            return super.createRedisCache(name , cacheConfig);
//        }
//        String[] split = name.split(SPLIT_FLAG);
//
//        if(split.length < CACHE_LENGTH){
//            return super.createRedisCache(name , cacheConfig);
//        }
//
//        if(cacheConfig != null){
//            try {
//                // 设置超时时间
//                long l = Long.parseLong(split[1]);
//                cacheConfig = cacheConfig.entryTtl(Duration.ofSeconds(l));
//            }catch (Exception e){
//                log.warn("SET CACHE TTL FAIL ！AFTER # MUST BE LONG");
//            }
//        }
//
//        return super.createRedisCache(name, cacheConfig);
//    }
//}
//
//
