package com.shop.authenticationserver.service;

import com.shop.authenticationserver.config.JwtTokenProvider;
import com.shop.authenticationserver.dto.AuthenticationResponse;
import com.shop.authenticationserver.dto.UserDTO;
import com.shop.authenticationserver.exception.JwtAuthenticationException;
import com.shop.authenticationserver.model.UserDetailsImpl;
import com.shop.authenticationserver.utils.constant.ServiceUrl;
import com.shop.authenticationserver.utils.validation.UserValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserValidation userValidation;
    private final InMemoryUserDetailsManager userDetailsManager;
    private final WebClient.Builder webClientBuilder;

    @Value("${jwt.tokenType}")
    private String tokenType;

    @Value("${jwt.refreshHeader}")
    private String refreshHeader;

    public AuthenticationResponse login(String email, String password) throws JwtAuthenticationException {
        UserDetails userDetails;
        try {
            if (userDetailsManager.userExists(email)) {
                userDetails = userDetailsManager.loadUserByUsername(email);
            } else {
                UserDTO user = webClientBuilder.build()
                        .get().uri(ServiceUrl.CUSTOMER_GET_EMAIL + email)
                        .retrieve().bodyToMono(UserDTO.class).block();
                Optional.ofNullable(user)
                        .orElseThrow(() -> new JwtAuthenticationException("User not found"));
                userDetails = UserDetailsImpl.fromUser(user);
            }
        } catch (UsernameNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
        }

        userValidation.validateLogin(userDetails, email, password);
        if(!userDetailsManager.userExists(userDetails.getUsername())){
            userDetailsManager.createUser(userDetails);
        }

        Map<String, String> claims = jwtTokenProvider.createClaims(userDetails);
        String accessToken = jwtTokenProvider.createAccessToken(email, claims);
        String refreshToken = jwtTokenProvider.createRefreshToken(email, claims);

        return new AuthenticationResponse(accessToken, refreshToken);
    }

    public AuthenticationResponse loginUpdate(Map<String, String> user) throws JwtAuthenticationException {
        UserDTO userDTO = webClientBuilder.build()
                .put().uri(ServiceUrl.CUSTOMER_UPDATE)
                .body(BodyInserters.fromValue(user))
                .retrieve().bodyToMono(UserDTO.class).block();
        Optional.ofNullable(userDTO)
                .orElseThrow(() -> new JwtAuthenticationException("User not found"));

        UserDetails userDetails = UserDetailsImpl.fromUser(userDTO);
        try {
            String email = user.get("lastEmail");
            userDetailsManager.deleteUser(email);
            userDetailsManager.createUser(userDetails);
        } catch (UsernameNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
        }

        Map<String, String> claims = jwtTokenProvider.createClaims(userDetails);
        String accessToken = jwtTokenProvider.createAccessToken(userDetails.getUsername(), claims);
        String refreshToken = jwtTokenProvider.createRefreshToken(userDetails.getUsername(), claims);

        return new AuthenticationResponse(accessToken, refreshToken);
    }

    public AuthenticationResponse loginAnonymous() throws JwtAuthenticationException {
        UserDTO user = webClientBuilder.build()
                .get().uri(ServiceUrl.CUSTOMER_ADD_ANONYMOUS)
                .retrieve().bodyToMono(UserDTO.class).block();
        Optional.ofNullable(user)
                .orElseThrow(() -> new JwtAuthenticationException("Error when creating an anonymous user"));

        UserDetails userDetails = UserDetailsImpl.fromUser(user);
        userDetailsManager.createUser(userDetails);

        Map<String, String> claims = jwtTokenProvider.createClaims(userDetails);
        String accessToken = jwtTokenProvider.createAccessToken(user.getEmail(), claims);
        String refreshToken = jwtTokenProvider.createRefreshToken(user.getEmail(), claims);

        return new AuthenticationResponse(accessToken, refreshToken);
    }

    public AuthenticationResponse registration(Map<String, String> user) throws JwtAuthenticationException {
        UserDTO userDTO = webClientBuilder.build()
                .post().uri(ServiceUrl.CUSTOMER_ADD)
                .body(BodyInserters.fromValue(user))
                .retrieve().bodyToMono(UserDTO.class).block();
        Optional.ofNullable(userDTO)
                .orElseThrow(() -> new JwtAuthenticationException("User not found"));

        UserDetails userDetails = UserDetailsImpl.fromUser(userDTO);
        try {
            userDetailsManager.createUser(userDetails);
        } catch (UsernameNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
        }

        Map<String, String> claims = jwtTokenProvider.createClaims(userDetails);
        String accessToken = jwtTokenProvider.createAccessToken(userDetails.getUsername(), claims);
        String refreshToken = jwtTokenProvider.createRefreshToken(userDetails.getUsername(), claims);

        return new AuthenticationResponse(accessToken, refreshToken);
    }

    public AuthenticationResponse refreshAuthentication(Jwt jwt, Principal principal) {
        if(!jwtTokenProvider.validateRefreshToken(jwt)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid refresh token");
        }

        UserDetails userDetails;
        try {
            userDetails = userDetailsManager.loadUserByUsername(principal.getName());
        } catch (UsernameNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
        }

        Map<String, String> claims = jwtTokenProvider.createClaims(userDetails);
        String accessToken = jwtTokenProvider.createAccessToken(principal.getName(), claims);
        String refreshToken = jwtTokenProvider.createRefreshToken(principal.getName(), claims);

        return new AuthenticationResponse(accessToken, refreshToken);
    }
}
