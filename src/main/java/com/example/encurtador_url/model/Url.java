package com.example.encurtador_url.model;

import jakarta.persistence.*;

@Entity
@Table(name = "URL", indexes = {
        @Index(name = "idx_url_completa", columnList = "url_completa")
})
public class Url {

    @Id
    @Column(name="url_id", length = 10, nullable = false, unique = true)
    private String urlID;

    @Column(name="url_completa", nullable = false)
    private String urlCompleta;

    public Url() {}

    public Url(String urlId, String url) {
        this.urlID = urlId;
        this.urlCompleta = url;
    }

    public String getUrlID() {
        return urlID;
    }

    public String getUrlCompleta() {
        return urlCompleta;
    }

    public void setUrlID(String urlID) {
        this.urlID = urlID;
    }

    public void setUrlCompleta(String urlCompleta) {
        this.urlCompleta = urlCompleta;
    }
}
