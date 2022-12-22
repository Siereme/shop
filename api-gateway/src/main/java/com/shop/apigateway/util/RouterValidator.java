package com.shop.apigateway.util;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RouterValidator {

    @Value("${nonSecuredRoutes}")
    private final List<String> routes;

    public boolean isSecured(ServerHttpRequest request){
        return routes.stream()
                .noneMatch(uri -> request.getURI().getPath().contains(uri));
    }

}
