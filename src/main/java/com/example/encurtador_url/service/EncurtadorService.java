package com.example.encurtador_url.service;

import com.example.encurtador_url.exception.UrlNotFoundException;
import com.example.encurtador_url.model.Url;
import com.example.encurtador_url.record.controller.UrlDTO;
import com.example.encurtador_url.repository.UrlRepository;
import com.example.encurtador_url.utils.UrlUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EncurtadorService {

    private final UrlRepository repository;

    public EncurtadorService(UrlRepository repository) {
        this.repository = repository;
    }

    public String encurtarUrlESalvar(UrlDTO urlDTO, HttpServletRequest request) {
        Optional<Url> urlBanco = repository.findByUrlCompleta(urlDTO.url());

        if(urlBanco.isPresent()){
            return UrlUtils.convertUrlToEncurted(request, urlBanco.get().getUrlID());
        }
        String urlId = createUrl();

        repository.save(new Url(urlId, urlDTO.url()));

        return UrlUtils.convertUrlToEncurted(request, urlId);
    }


    public HttpHeaders getUrlRedirect(String urlId)  {
        Optional<Url> url = repository.findById(urlId);

        if(url.isEmpty()){
            throw new UrlNotFoundException("URL não encontrada");
        }

        return UrlUtils.getHeaders(url);
    }


    private String createUrl() {
        String urlId = null;
        do {
            urlId = RandomStringUtils.randomAlphanumeric(5, 10);
        }while (repository.existsById(urlId));

        return urlId;
    }
}
