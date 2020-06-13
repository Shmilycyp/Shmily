package com.imcode.base_gateway.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * 路由限流配置
 */
@Configuration
public class RateLimiterConfiguration {


    /**
     * 根据hostName进行过滤
     * @return 处理器
     */
    @Bean(name = "remoteAddrKeyResolver")
    @Primary
    public KeyResolver remoteAddrKeyResolver(){

        return exchange -> Mono.just(Objects.requireNonNull(exchange.getRequest().getRemoteAddress()).getAddress().getHostName());
    }

}
