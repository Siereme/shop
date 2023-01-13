package com.shop.authenticationserver.service;

import com.shop.authenticationserver.config.JwtTokenProvider;
import com.shop.authenticationserver.dto.AuthenticationResponse;
import com.shop.authenticationserver.dto.UserDTO;
import com.shop.authenticationserver.exception.JwtAuthenticationException;
import com.shop.authenticationserver.exception.UserValidationException;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
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

    public AuthenticationResponse login(String email, String password) {
        UserDetails userDetails;
        try {
            userDetails = userDetailsManager.loadUserByUsername(email);
        } catch (UsernameNotFoundException e) {
            throw new UserValidationException();
        }

        userValidation.validateLogin(userDetails, email, password);

        Map<String, String> claims = jwtTokenProvider.createClaims(userDetails);
        String accessToken = jwtTokenProvider.createAccessToken(email, claims);
        String refreshToken = jwtTokenProvider.createRefreshToken(email, claims);

        UserDTO userDTO = UserDTO.builder()
                .email(userDetails.getUsername())
                .password(userDetails.getPassword())
                .build();
        return new AuthenticationResponse(accessToken, refreshToken, userDTO);
    }

    public AuthenticationResponse loginUpdate(Map<String, String> user) throws JwtAuthenticationException {
        UserDTO userDTO = webClientBuilder.build()
                .put().uri(ServiceUrl.CUSTOMER_UPDATE)
                .body(BodyInserters.fromValue(user))
                .retrieve().bodyToMono(UserDTO.class).block();

        UserDetails userDetails = Optional.ofNullable(userDTO)
                .map(UserDetailsImpl::fromUser)
                .orElseThrow(() ->
                        new JwtAuthenticationException("User update exception", HttpStatus.BAD_REQUEST));
        try {
            userDetailsManager.deleteUser(user.get("lastEmail"));
            userDetailsManager.createUser(userDetails);
        } catch (UsernameNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User update error");
        }

        Map<String, String> claims = jwtTokenProvider.createClaims(userDetails);
        String accessToken = jwtTokenProvider.createAccessToken(userDetails.getUsername(), claims);
        String refreshToken = jwtTokenProvider.createRefreshToken(userDetails.getUsername(), claims);

        return new AuthenticationResponse(accessToken, refreshToken, userDTO);
    }

    public AuthenticationResponse loginAnonymous() throws JwtAuthenticationException {
        UserDTO userDTO = webClientBuilder.build()
                .post().uri(ServiceUrl.CUSTOMER_ADD_ANONYMOUS)
                .retrieve().bodyToMono(UserDTO.class).block();

        UserDetails userDetails = Optional.ofNullable(userDTO)
                .map(UserDetailsImpl::fromUser)
                .orElseThrow(() ->
                        new JwtAuthenticationException("Error creating an anonymous user", HttpStatus.BAD_REQUEST));
        userDetailsManager.createUser(userDetails);

        Map<String, String> claims = jwtTokenProvider.createClaims(userDetails);
        String accessToken = jwtTokenProvider.createAccessToken(userDTO.getEmail(), claims);
        String refreshToken = jwtTokenProvider.createRefreshToken(userDTO.getEmail(), claims);

        return new AuthenticationResponse(accessToken, refreshToken, userDTO);
    }

    public AuthenticationResponse registration(Map<String, String> user) throws JwtAuthenticationException {
        UserDTO userDTO = webClientBuilder.build()
                .post().uri(ServiceUrl.CUSTOMER_ADD)
                .body(BodyInserters.fromValue(user))
                .retrieve()
                .onStatus(HttpStatus::isError,
                        response -> response.bodyToMono(UserValidationException.class))
                .bodyToMono(UserDTO.class)
                .block();

        UserDetails userDetails = Optional.ofNullable(userDTO)
                .map(UserDetailsImpl::fromUser)
                .orElseThrow(() ->
                        new JwtAuthenticationException("User creation error", HttpStatus.UNAUTHORIZED));
        try {
            userDetailsManager.createUser(userDetails);
        } catch (UsernameNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User creation error");
        }

        Map<String, String> claims = jwtTokenProvider.createClaims(userDetails);
        String accessToken = jwtTokenProvider.createAccessToken(userDetails.getUsername(), claims);
        String refreshToken = jwtTokenProvider.createRefreshToken(userDetails.getUsername(), claims);

        return new AuthenticationResponse(accessToken, refreshToken, userDTO);
    }

    public AuthenticationResponse refreshAuthentication(Jwt jwt, Principal principal) {
        if (!jwtTokenProvider.validateRefreshToken(jwt)) {
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

        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(userDetails.getUsername());
        userDTO.setPassword(userDetails.getPassword());
        userDTO.setPermissionsByAuthorities(userDetails.getAuthorities());

        return new AuthenticationResponse(accessToken, refreshToken, userDTO);
    }

}
