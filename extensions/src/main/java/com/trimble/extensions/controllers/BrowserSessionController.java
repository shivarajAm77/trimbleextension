package com.trimble.extensions.controller;

import com.trimble.extensions.controller.BrowserSession;
import com.trimble.extensions.BrowserSessionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class BrowserSessionController {

    private final BrowserSessionRepository repository;

    public BrowserSessionController(BrowserSessionRepository repository) {
        this.repository = repository;
    }

    // Store browserId
    @PostMapping("/store-browser")
    public ResponseEntity<?> storeBrowser(@RequestBody BrowserSession browserSession) {
        if (browserSession.getBrowserId() == null || browserSession.getBrowserId().isEmpty()) {
            return ResponseEntity.badRequest().body("{\"error\":\"browserId is required\"}");
        }
        repository.save(browserSession);
        return ResponseEntity.ok("{\"success\":true}");
    }

    // Check browserId
    @GetMapping("/check-browser/{browserId}")
    public ResponseEntity<?> checkBrowser(@PathVariable String browserId) {
        boolean exists = repository.existsById(browserId);
        return ResponseEntity.ok("{\"authenticated\":" + exists + "}");
    }
}
