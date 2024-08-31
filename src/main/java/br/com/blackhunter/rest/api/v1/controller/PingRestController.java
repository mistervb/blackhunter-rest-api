package br.com.blackhunter.rest.api.v1.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/ping")
public class PingRestController {
    @GetMapping
    public ResponseEntity<String> doPong() {
        return ResponseEntity.ok("Pong!");
    }
}
