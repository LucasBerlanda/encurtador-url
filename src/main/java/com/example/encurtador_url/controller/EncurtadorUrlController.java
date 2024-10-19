package com.example.encurtador_url.controller;

import com.example.encurtador_url.record.controller.UrlDTO;
import com.example.encurtador_url.service.EncurtadorService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EncurtadorUrlController {

    private final EncurtadorService service;

    public EncurtadorUrlController(EncurtadorService service) {
        this.service = service;
    }

    @PostMapping("/encurtar-url")
    public ResponseEntity<UrlDTO> encurtarUrl(@RequestBody UrlDTO urlDTO, HttpServletRequest request){
        var url = service.encurtarUrlESalvar(urlDTO, request);

        return ResponseEntity.ok(new UrlDTO(url));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Void> redirectUrl(@PathVariable(name = "id") String urlId) {
        return ResponseEntity.status(HttpStatus.FOUND).headers(service.getUrlRedirect(urlId)).build();
    }
}
