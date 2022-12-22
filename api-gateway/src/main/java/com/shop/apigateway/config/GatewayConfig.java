package com.shop.apigateway.config;

import com.shop.apigateway.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class GatewayConfig {

    private final JwtAuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("auth", r -> r.path("/auth/**").filters(f -> f.filter(filter)).uri("lb://auth"))
                .route("product", r -> r.path("/api/v1/product-catalog/**").filters(f -> f.filter(filter)).uri("lb://product-server"))
                .route("order", r -> r.path("/api/v1/order/**").filters(f -> f.filter(filter)).uri("lb://order-server"))
                .route("customer", r -> r.path("/api/v1/customer/**").filters(f -> f.filter(filter)).uri("lb://customer-server"))
                .route("shopping-cart", r -> r.path("/api/v1/shopping-cart/**").filters(f -> f.filter(filter)).uri("lb://shopping-cart-server"))
                .build();
    }

}
