package app.security;

import app.exception.JwtAuthenticationException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Value("${jwt.accessSecretKey}")
    private String accessSecretKey;
    @Value("${jwt.refreshSecretKey}")
    private String refreshSecretKey;
    @Value("${jwt.accessHeader}")
    private String accessHeader;
    @Value("${jwt.refreshHeader}")
    private String refreshHeader;
    @Value("${jwt.accessExpirationMs}")
    private long accessExpirationMs;
    @Value("${jwt.refreshExpirationMs}")
    private long refreshExpirationMs;


    @PostConstruct
    protected void init() {
        accessSecretKey = Base64.getEncoder().encodeToString(accessSecretKey.getBytes());
        refreshSecretKey = Base64.getEncoder().encodeToString(refreshSecretKey.getBytes());
    }

    public String createAccessToken(String username, String role) {
        Claims claims = Jwts.claims();
        claims.put("username", username);
        claims.put("role", role);
        Date now = new Date();
        Date validity = new Date(now.getTime() + accessExpirationMs);

        return Jwts.builder().setClaims(claims).setIssuedAt(now).setExpiration(validity).signWith(SignatureAlgorithm.HS256, accessSecretKey).compact();
    }

    public String createRefreshToken(long id, String role) {
        Claims claims = Jwts.claims();
        claims.put("id", id);
        claims.put("role", role);
        Date now = new Date();
        Date validity = new Date(now.getTime() + refreshExpirationMs);

        return Jwts.builder().setClaims(claims).setIssuedAt(now).setExpiration(validity).signWith(SignatureAlgorithm.HS256, refreshSecretKey).compact();
    }

    public boolean validateAccessToken(String accessToken) {
        return validateToken(accessToken, accessSecretKey);
    }

    public boolean validateRefreshToken(String refreshToken) {
        return validateToken(refreshToken, refreshSecretKey);
    }

    public boolean validateToken(String token, String secretKey) {
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return !claimsJws.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            throw new JwtAuthenticationException("JWT token is expired or invalid", HttpStatus.UNAUTHORIZED);
        }
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUsername(String token) {
        return (String) Jwts.parser().setSigningKey(accessSecretKey).parseClaimsJws(token).getBody().get("username");
    }

    public Long getId(String token) {
        return Long.valueOf((Integer) Jwts.parser().setSigningKey(refreshSecretKey).parseClaimsJws(token).getBody().get("id"));
    }


    public String resolveAccessToken(HttpServletRequest request) {
        return request.getHeader(accessHeader);
    }

    public String resolveRefreshToken(HttpServletRequest request) {
        return request.getHeader(refreshHeader);
    }
}
