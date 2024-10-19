package com.example.encurtador_url.repository;

import com.example.encurtador_url.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UrlRepository extends JpaRepository<Url, String> {
    Optional<Url> findByUrlCompleta(String url);
}
