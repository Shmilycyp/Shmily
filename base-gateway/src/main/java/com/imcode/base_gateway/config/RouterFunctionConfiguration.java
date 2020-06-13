package com.imcode.base_gateway.config;

import com.imcode.base_gateway.handle.HystrixFallbackHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Slf4j
@Configuration
@AllArgsConstructor
public class RouterFunctionConfiguration {

    private final HystrixFallbackHandler hystrixFallbackHandler;


    @Bean
    public RouterFunction<ServerResponse> routerFunction() {
        return RouterFunctions.route(
                RequestPredicates.path("/fallback")
                        .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), hystrixFallbackHandler);
//                .andRoute(RequestPredicates.GET("/code")
//                        .and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), imageCodeHandler)
//                .andRoute(RequestPredicates.GET("/swagger-resources")
//                        .and(RequestPredicates.accept(MediaType.ALL)), swaggerResourceHandler)
//                .andRoute(RequestPredicates.GET("/swagger-resources/configuration/ui")
//                        .and(RequestPredicates.accept(MediaType.ALL)), swaggerUiHandler)
//                .andRoute(RequestPredicates.GET("/swagger-resources/configuration/security")
//                        .and(RequestPredicates.accept(MediaType.ALL)), swaggerSecurityHandler);

    }

//    @Bean
//    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
//
//        RedisRateLimiter redisRateLimiter = new RedisRateLimiter( 2 ,20);
//
//        return builder.routes()
//                .route("path_route", r -> r.path("/get")
//                        .uri("http://httpbin.org"))
//                .route("host_route", r -> r.host("*.myhost.org")
//                        .uri("http://httpbin.org"))
//                .route("rewrite_route", r -> r.host("*.rewrite.org")
//                        .filters(f -> f.rewritePath("/foo/(?<segment>.*)", "/${segment}"))
//                        .uri("http://httpbin.org"))
//                .route("hystrix_route", r -> r.host("*.hystrix.org")
//                        .filters(f -> f.hystrix(c -> c.setName("slowcmd")))
//                        .uri("http://httpbin.org"))
//                .route("hystrix_fallback_route", r -> r.host("*.hystrixfallback.org")
//                        .filters(f -> f.hystrix(c -> c.setName("slowcmd").setFallbackUri("forward:/hystrixfallback")))
//                        .uri("http://httpbin.org"))
//                .route("limit_route", r -> r
//                        .host("*.limited.org").and().path("/anything/**")
//                        .filters(f -> f.requestRateLimiter(c -> c.setRateLimiter(redisRateLimiter)))
//                        .uri("http://httpbin.org"))
//                .build();
//    }


}
