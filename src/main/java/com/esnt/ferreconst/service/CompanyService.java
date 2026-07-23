package com.esnt.ferreconst.service;

import com.esnt.ferreconst.dto.request.company.CreateCompanyRequestDto;
import com.esnt.ferreconst.dto.response.company.CompanyPageResponseDto;
import com.esnt.ferreconst.dto.response.company.CompanyResponseDto;
import com.esnt.ferreconst.dto.response.ErrorResponseDto;
import com.esnt.ferreconst.mapper.CompanyMapper;
import com.esnt.ferreconst.model.AuthResponse;
import com.esnt.ferreconst.model.company.CompanyPage;
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

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class CompanyService {

    private static final Logger log = Logger.getLogger(CompanyService.class.getName());
    private static final String COMPANY_URL = "https://api.ferreconst.space/companies";
    private static final int DEFAULT_PAGE_SIZE = 10;

    private final RestTemplate rest = new RestTemplate();

    @Autowired
    private CompanyMapper mapper;

    @Autowired
    private AuthService authService;

    @Autowired
    private ObjectMapper objectMapper;

    public void create(AuthResponse auth, String ruc, String businessName, String email, String code) {
        CreateCompanyRequestDto request = new CreateCompanyRequestDto();
        request.setEmruc(ruc);
        request.setEmrznsocial(businessName);
        request.setEmcorreo(email);
        request.setEmcodigo(code);

        try {
            log.info("Creando empresa: " + businessName);
            CompanyResponseDto dto;
            try {
                dto = sendCreateRequest(auth, request);
            } catch (HttpClientErrorException.Unauthorized e) {
                TokenResponse tokens = authService.refresh(auth.getRefreshToken());
                auth.setAccessToken(tokens.getAccessToken());
                auth.setRefreshToken(tokens.getRefreshToken());
                dto = sendCreateRequest(auth, request);
            }
            log.info("Empresa creada exitosamente: " + dto.getRznsocial());
            mapper.toCompany(dto);
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

            log.log(Level.SEVERE, "Error al crear empresa: " + message, e);
            throw new RuntimeException(message, e);
        } catch (Exception e) {
            String message = e.getMessage() != null ? e.getMessage() : "Error al crear empresa";
            log.log(Level.SEVERE, "Error al crear empresa: " + message, e);
            throw new RuntimeException(message, e);
        }
    }

    private CompanyResponseDto sendCreateRequest(AuthResponse auth, CreateCompanyRequestDto request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(auth.getAccessToken());

        HttpEntity<CreateCompanyRequestDto> entity = new HttpEntity<>(request, headers);
        ResponseEntity<CompanyResponseDto> response = rest.exchange(COMPANY_URL, HttpMethod.POST, entity, CompanyResponseDto.class);
        return response.getBody();
    }

    public CompanyPage search(AuthResponse auth, String search, int page) {
        int requestedPage = page < 1 ? 1 : page;

        try {
            log.info("Buscando empresas");
            CompanyPageResponseDto dto;
            try {
                dto = sendSearchRequest(auth, search, requestedPage);
            } catch (HttpClientErrorException.Unauthorized e) {
                TokenResponse tokens = authService.refresh(auth.getRefreshToken());
                auth.setAccessToken(tokens.getAccessToken());
                auth.setRefreshToken(tokens.getRefreshToken());
                dto = sendSearchRequest(auth, search, requestedPage);
            }
            return mapper.toCompanyPage(dto);
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

            log.log(Level.SEVERE, "Error al buscar empresas: " + message, e);
            throw new RuntimeException(message, e);
        } catch (Exception e) {
            String message = e.getMessage() != null ? e.getMessage() : "Error al buscar empresas";
            log.log(Level.SEVERE, "Error al buscar empresas: " + message, e);
            throw new RuntimeException(message, e);
        }
    }

    private CompanyPageResponseDto sendSearchRequest(AuthResponse auth, String search, int page) {
        UriComponentsBuilder url = UriComponentsBuilder.fromHttpUrl(COMPANY_URL)
                .queryParam("page", page)
                .queryParam("pageSize", DEFAULT_PAGE_SIZE);
        if (search != null && !search.trim().isEmpty()) {
            url.queryParam("search", search.trim());
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(auth.getAccessToken());
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        ResponseEntity<CompanyPageResponseDto> response = rest.exchange(
                url.build().encode().toUriString(), HttpMethod.GET, entity, CompanyPageResponseDto.class);
        return response.getBody();
    }

}
