package com.esnt.ferreconst.service;

import com.esnt.ferreconst.dto.request.configuration.CreateConfigurationRequestDto;
import com.esnt.ferreconst.dto.response.ErrorResponseDto;
import com.esnt.ferreconst.dto.response.configuration.ConfigurationResponseDto;
import com.esnt.ferreconst.mapper.ConfigurationMapper;
import com.esnt.ferreconst.model.AuthResponse;
import com.esnt.ferreconst.model.Configuration;
import com.esnt.ferreconst.model.TokenResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ConfigurationService {

    private static final Logger log = Logger.getLogger(ConfigurationService.class.getName());
    private static final String CONFIGURATION_URL = "https://api.ferreconst.space/configs";

    private final RestTemplate rest = new RestTemplate();

    @Autowired
    private ConfigurationMapper mapper;

    @Autowired
    private AuthService authService;

    @Autowired
    private ObjectMapper objectMapper;

    public Configuration create(AuthResponse auth, String companyId, String key, String value) {
        CreateConfigurationRequestDto request = new CreateConfigurationRequestDto();
        request.setCfemid(companyId);
        request.setCfclave(key);
        request.setCfvalor(value);

        try {
            log.info("Creando configuración: " + key);
            ConfigurationResponseDto dto;
            try {
                dto = sendCreateRequest(auth, request);
            } catch (HttpClientErrorException.Unauthorized e) {
                TokenResponse tokens = authService.refresh(auth.getRefreshToken());
                auth.setAccessToken(tokens.getAccessToken());
                auth.setRefreshToken(tokens.getRefreshToken());
                dto = sendCreateRequest(auth, request);
            }
            return mapper.toConfiguration(dto);
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

            log.log(Level.SEVERE, "Error al crear configuración: " + message, e);
            throw new RuntimeException(message, e);
        } catch (Exception e) {
            String message = e.getMessage() != null ? e.getMessage() : "Error al crear configuración";
            log.log(Level.SEVERE, "Error al crear configuración: " + message, e);
            throw new RuntimeException(message, e);
        }
    }

    public List<Configuration> findByCompany(AuthResponse auth, String companyId) {
        try {
            log.info("Consultando configuraciones de empresa: " + companyId);
            ConfigurationResponseDto[] dtos;
            try {
                dtos = sendFindByCompanyRequest(auth, companyId);
            } catch (HttpClientErrorException.Unauthorized e) {
                TokenResponse tokens = authService.refresh(auth.getRefreshToken());
                auth.setAccessToken(tokens.getAccessToken());
                auth.setRefreshToken(tokens.getRefreshToken());
                dtos = sendFindByCompanyRequest(auth, companyId);
            }
            return mapper.toConfigurations(dtos);
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

            log.log(Level.SEVERE, "Error al consultar configuraciones: " + message, e);
            throw new RuntimeException(message, e);
        } catch (Exception e) {
            String message = e.getMessage() != null ? e.getMessage() : "Error al consultar configuraciones";
            log.log(Level.SEVERE, "Error al consultar configuraciones: " + message, e);
            throw new RuntimeException(message, e);
        }
    }

    private ConfigurationResponseDto sendCreateRequest(AuthResponse auth, CreateConfigurationRequestDto request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(auth.getAccessToken());

        HttpEntity<CreateConfigurationRequestDto> entity = new HttpEntity<>(request, headers);
        ResponseEntity<ConfigurationResponseDto> response = rest.exchange(
                CONFIGURATION_URL, HttpMethod.POST, entity, ConfigurationResponseDto.class);
        return response.getBody();
    }

    private ConfigurationResponseDto[] sendFindByCompanyRequest(AuthResponse auth, String companyId) {
        String url = UriComponentsBuilder.fromHttpUrl(CONFIGURATION_URL)
                .queryParam("companyId", companyId)
                .build()
                .encode()
                .toUriString();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(auth.getAccessToken());
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        ResponseEntity<ConfigurationResponseDto[]> response = rest.exchange(
                url, HttpMethod.GET, entity, ConfigurationResponseDto[].class);
        return response.getBody();
    }
}
