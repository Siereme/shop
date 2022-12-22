package com.shop.apigateway.util;

import com.shop.apigateway.exception.JwtAuthenticationException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${jwt.accessSecretKey}")
    private String accessSecretKey;
    @Value("${jwt.accessHeader}")
    private String accessHeader;


    @PostConstruct
    protected void init() {
        accessSecretKey = Base64.getEncoder().encodeToString(accessSecretKey.getBytes());
    }

    public boolean validateToken(String token) throws JwtAuthenticationException {
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(accessSecretKey).parseClaimsJws(token);
            return !claimsJws.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            throw new JwtAuthenticationException("JWT token is expired or invalid", HttpStatus.UNAUTHORIZED);
        }
    }


    public String resolveToken(ServerHttpRequest request) {
        return request.getHeaders().getFirst(accessHeader);
    }
}
