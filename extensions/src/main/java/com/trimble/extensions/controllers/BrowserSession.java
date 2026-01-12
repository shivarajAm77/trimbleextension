package com.example.iframeauthapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class BrowserSession {

    @Id
    private String browserId;

    public BrowserSession() {}

    public BrowserSession(String browserId) {
        this.browserId = browserId;
    }

    public String getBrowserId() {
        return browserId;
    }

    public void setBrowserId(String browserId) {
        this.browserId = browserId;
    }
}
