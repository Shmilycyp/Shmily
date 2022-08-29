package com.imcode.getway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author shmilyah@163.com
 * @date 2022-08-29
 */
@Configuration
public class RouteConfig {


    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/hello")
                        .uri("https://baidu.com")
                )
                .route(r -> r.host("**.abc.org").and().path("/image/png")
                        .filters(f ->
                                f.addResponseHeader("X-TestHeader", "foobar"))
                        .uri("http://httpbin.org:80")
                )
                .route(r -> r.path("/image/webp")
                        .filters(f ->
                                f.addResponseHeader("X-AnotherHeader", "baz"))
                        .uri("http://httpbin.org:80")
                )
                .build();
    }


}
