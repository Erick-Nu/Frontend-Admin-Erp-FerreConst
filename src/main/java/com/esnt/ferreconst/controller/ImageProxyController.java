package com.esnt.ferreconst.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/proxy")
public class ImageProxyController {

    private static final String ALLOWED_ORIGIN = "https://api.ferreconst.space/uploads/";

    private final RestTemplate rest = new RestTemplate();

    @GetMapping("/image")
    public void proxyImage(@RequestParam String url, HttpServletResponse response) throws IOException {
        if (url == null || url.trim().isEmpty() || !url.startsWith(ALLOWED_ORIGIN)) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "URL no permitida");
            return;
        }

        try {
            ResponseEntity<byte[]> res = rest.exchange(url, HttpMethod.GET, null, byte[].class);

            HttpHeaders headers = res.getHeaders();
            if (headers.getContentType() != null) {
                response.setContentType(headers.getContentType().toString());
            }
            response.setHeader("Cache-Control", "public, max-age=86400");

            byte[] body = res.getBody();
            if (body != null) {
                response.getOutputStream().write(body);
                response.getOutputStream().flush();
            } else {
                response.sendError(HttpServletResponse.SC_BAD_GATEWAY, "Respuesta vacía de la API");
            }
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_BAD_GATEWAY, "No se pudo obtener la imagen");
        }
    }
}
