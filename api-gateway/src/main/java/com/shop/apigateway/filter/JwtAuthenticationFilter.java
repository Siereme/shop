package com.shop.apigateway.filter;

import com.shop.apigateway.exception.JwtAuthenticationException;
import com.shop.apigateway.util.JwtTokenProvider;
import com.shop.apigateway.util.RouterValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter implements GatewayFilter {

    private final JwtTokenProvider tokenProvider;
    private final RouterValidator routerValidator;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

//        if(routerValidator.isSecured(request)){
//            String token = tokenProvider.resolveToken(request);
//            try {
//                tokenProvider.validateToken(token);
//            } catch (JwtAuthenticationException e) {
//                ServerHttpResponse response = exchange.getResponse();
//                response.setStatusCode(HttpStatus.BAD_REQUEST);
//                return response.setComplete();
//            }
//        }
        return chain.filter(exchange);
    }

}
