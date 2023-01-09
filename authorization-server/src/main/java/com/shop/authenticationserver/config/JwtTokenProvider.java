package com.shop.authenticationserver.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

    @Value("${jwt.accessHeader}")
    private String accessHeader;
    @Value("${jwt.accessExpirationMs}")
    private int accessExpirationMs;
    @Value("${jwt.refreshHeader}")
    private String refreshHeader;
    @Value("${jwt.refreshExpirationMs}")
    private int refreshExpirationMs;

    @Value("${jwt.tokenType}")
    private String tokenType;

    @Value("${app.auth-server}")
    private String authServer;


    public String createAccessToken(String subject, Map<String, String> claims) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + accessExpirationMs);

        JWTCreator.Builder jwtBuilder = JWT.create().withSubject(subject);

        claims.put(tokenType, accessHeader);
        claims.forEach(jwtBuilder::withClaim);

        return jwtBuilder
                .withNotBefore(new Date())
                .withExpiresAt(validity)
                .sign(Algorithm.RSA256(Jwks.publicKey, Jwks.privateKey));
    }

    public String createRefreshToken(String subject, Map<String, String> claims) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + refreshExpirationMs);

        JWTCreator.Builder jwtBuilder = JWT.create().withSubject(subject);

        claims.put("token_type", refreshHeader);
        claims.forEach(jwtBuilder::withClaim);

        return jwtBuilder
                .withNotBefore(new Date())
                .withExpiresAt(validity)
                .sign(Algorithm.RSA256(Jwks.publicKey, Jwks.privateKey));
    }

    public Map<String, String> createClaims(UserDetails userDetails) {
        Map<String, String> claims = new HashMap<>();
        claims.put("username", userDetails.getUsername());

        String authorities = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        claims.put("authorities", authorities);
        claims.put("iss", authServer);
        return claims;
    }

    public boolean validateAccessToken(Jwt jwt) {
        String type = jwt.getClaims().get(tokenType).toString();
        Date expiration = Date.from((Instant) jwt.getClaims().get("exp"));
        return accessHeader.equals(type) && expiration.after(new Date());
    }

    public boolean validateRefreshToken(Jwt jwt) {
        String type = jwt.getClaims().get(tokenType).toString();
        Date expiration = Date.from((Instant) jwt.getClaims().get("exp"));
        return refreshHeader.equals(type) && expiration.after(new Date());
    }

}
