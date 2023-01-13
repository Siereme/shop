package com.shop.orderserver.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Configuration
@Slf4j
public class WebConfig {

    @LoadBalanced
    @Bean
    WebClient.Builder webClientBuilder() {
        return WebClient.builder()
                .filter(ExchangeFilterFunction.ofRequestProcessor(
                        request -> Mono.just(Objects.requireNonNull(withBearerAuth(request)))));
    }

    private static ClientRequest withBearerAuth(ClientRequest request) {
        try {
            SecurityContext context = SecurityContextHolder.getContext();
            Authentication authentication = context.getAuthentication();
            Jwt jwt = (Jwt) authentication.getPrincipal();
            return ClientRequest.from(request)
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwt.getTokenValue())
                    .build();
        } catch (RuntimeException ex) {
            log.debug("Error adding a token to the request header");
            return ClientRequest.from(request).build();
        }
    }

}
