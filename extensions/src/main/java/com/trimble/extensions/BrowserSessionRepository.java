package com.example.iframeauthapi.repository;

import com.example.iframeauthapi.model.BrowserSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrowserSessionRepository extends JpaRepository<BrowserSession, String> {
}
