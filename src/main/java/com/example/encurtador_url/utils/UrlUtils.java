package com.example.encurtador_url.utils;

import com.example.encurtador_url.model.Url;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;

import java.net.URI;
import java.util.Optional;

public class UrlUtils {

    public static String convertUrlToEncurted(HttpServletRequest request, String urlId) {
        var urlRequest = request.getRequestURL().toString();
        var urlPath = request.getRequestURI();
        return urlRequest.replace(urlPath, "/".concat(urlId));
    }

    public static HttpHeaders getHeaders(Optional<Url> url) {
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(url.get().getUrlCompleta()));
        return headers;
    }
}
