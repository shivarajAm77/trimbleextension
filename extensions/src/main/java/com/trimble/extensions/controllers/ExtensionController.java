package com.trimble.extensions.controllers;

import java.io.IOException;
import java.nio.file.Files;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trimble-extension")
public class ExtensionController {
	 // --- Serve manifest.json ---
    @GetMapping("/manifest.json")
    public ResponseEntity<String> getManifest() throws IOException {
        String manifest = """
                {
                  "id": "virtuele-custom-extension",
                  "name": "Virtuele Extension",
                  "description": "Custom Trimble Connect extension with redirect API",
                  "iconUrl": "https://trimbleextension.onrender.com/trimble-extension/icon.png",
                  "version": "1.0.0",
                  "entryPoint": {
                    "web": {
                      "html": "https://trimbleextension.onrender.com/trimble-extension/index.html",
                      "js": "https://trimbleextension.onrender.com/trimble-extension/index.js"
                    }
                  },
                  "permissions": ["workspace:read"],
                  "configCommand": {
                    "title": "Virtuele Settings",
                    "command": "configure"
                  }
                }
                """;

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(manifest);
    }

    // --- Serve index.html ---
    @GetMapping("/index.html")
    public ResponseEntity<byte[]> getHtml() throws IOException {
        ClassPathResource htmlFile = new ClassPathResource("static/trimble-extension/index.html");
        byte[] bytes = Files.readAllBytes(htmlFile.getFile().toPath());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_HTML_VALUE)
                .body(bytes);
    }

	    // --- Serve index.js ---
	    @GetMapping("/index.js")
	    public ResponseEntity<byte[]> getJs() throws IOException {
	        ClassPathResource jsFile = new ClassPathResource("static/trimble-extension/index.js");
	        byte[] bytes = Files.readAllBytes(jsFile.getFile().toPath());
	        return ResponseEntity.ok()
	                .header(HttpHeaders.CONTENT_TYPE, "application/javascript")
	                .body(bytes);
	    }
    @GetMapping("/icon.png")
    public ResponseEntity<byte[]> getIcon() throws IOException {
    	 ClassPathResource imageFile = new ClassPathResource("static/trimble-extension/icon.png");
    	    byte[] bytes = StreamUtils.copyToByteArray(imageFile.getInputStream());
    	    return ResponseEntity.ok()
    	            .header(HttpHeaders.CONTENT_TYPE, MediaType.IMAGE_PNG_VALUE)
    	            .body(bytes);
    }


    // --- Redirect API endpoint ---
    @GetMapping("/api/redirect")
    public ResponseEntity<Void> redirectToVirtuele(@RequestParam String projectId) {
        String destination = "https://clbdev.virtuele.us/dashboard?source=trimble&projectId=" + projectId;
        return ResponseEntity.status(302).header(HttpHeaders.LOCATION, destination).build();
    }
}
