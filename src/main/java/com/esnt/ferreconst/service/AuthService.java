package com.esnt.ferreconst.service;

import com.esnt.ferreconst.dto.request.LoginRequestDto;
import com.esnt.ferreconst.dto.request.RefreshRequestDto;
import com.esnt.ferreconst.dto.response.ErrorResponseDto;
import com.esnt.ferreconst.dto.response.AuthResponseDto;
import com.esnt.ferreconst.dto.response.RefreshResponseDto;
import com.esnt.ferreconst.exception.AuthenticationException;
import com.esnt.ferreconst.mapper.AuthMapper;
import com.esnt.ferreconst.model.Auth;
import com.esnt.ferreconst.model.AuthResponse;
import com.esnt.ferreconst.model.TokenResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;
import java.util.logging.Level;

@Service
public class AuthService {

    private static final Logger log = Logger.getLogger(AuthService.class.getName());
    private static final String LOGIN_URL = "https://api.ferreconst.space/auth/login";
    private static final String REFRESH_URL = "https://api.ferreconst.space/auth/refresh";

    private final RestTemplate rest = new RestTemplate();

    @Autowired
    private AuthMapper mapper;

    @Autowired
    private ObjectMapper objectMapper;

    public AuthResponse login(Auth auth) {
        LoginRequestDto request = new LoginRequestDto(auth.getRut(), auth.getUser(), auth.getPassword());
        log.info("Inicio de sesión: " + auth.getUser());
        try {
            AuthResponseDto dto = rest.postForObject(LOGIN_URL, request, AuthResponseDto.class);
            log.info("Inicio de sesión exitoso");
            return mapper.toAuthResponse(dto);
        } catch (HttpStatusCodeException e) {
            String message = e.getStatusText();
            String body = e.getResponseBodyAsString();

            if (body != null && !body.trim().isEmpty()) {
                try {
                    ErrorResponseDto error = objectMapper.readValue(body, ErrorResponseDto.class);
                    if (error.getMessage() != null && !error.getMessage().trim().isEmpty()) {
                        message = error.getMessage();
                    }
                } catch (JsonProcessingException ignored) {
                    log.warning("No se pudo interpretar el body de error de la API");
                }
            }

            log.log(Level.SEVERE, "Error al iniciar sesión: " + message, e);
            throw new AuthenticationException(message, e);
        } catch (Exception e) {
            log.log(Level.SEVERE, "Error al iniciar sesión", e);
            throw new AuthenticationException(e.getMessage(), e);
        }
    }

    public TokenResponse refresh(String refreshToken) {
        RefreshRequestDto request = new RefreshRequestDto(refreshToken);
        log.info("Refrescando token");
        try {
            RefreshResponseDto dto = rest.postForObject(REFRESH_URL, request, RefreshResponseDto.class);
            log.info("Token refrescado exitosamente");
            return mapper.toTokenResponse(dto);
        } catch (HttpStatusCodeException e) {
            String message = e.getStatusText();
            String body = e.getResponseBodyAsString();

            if (body != null && !body.trim().isEmpty()) {
                try {
                    ErrorResponseDto error = objectMapper.readValue(body, ErrorResponseDto.class);
                    if (error.getMessage() != null && !error.getMessage().trim().isEmpty()) {
                        message = error.getMessage();
                    }
                } catch (JsonProcessingException ignored) {
                    log.warning("No se pudo interpretar el body de error de la API");
                }
            }

            log.log(Level.SEVERE, "Error al refrescar token: " + message, e);
            throw new RuntimeException(message, e);
        } catch (Exception e) {
            log.log(Level.SEVERE, "Error al refrescar token", e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
