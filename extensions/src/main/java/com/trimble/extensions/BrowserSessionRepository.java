package com.trimble.extensions;

import com.trimble.extensions.controllers.BrowserSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrowserSessionRepository extends JpaRepository<BrowserSession, String> {
}
