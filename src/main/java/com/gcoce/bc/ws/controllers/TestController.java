package com.gcoce.bc.ws.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Content.";
    }
    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public String userAccess() {
        return "User Content.";
    }

    @GetMapping("/beneficio")
    @PreAuthorize("hasRole('BENEFICIO')")
    public String beneficioAccess() {
        return "Beneficio Content.";
    }

    @GetMapping("/agricultor")
    @PreAuthorize("hasRole('AGRICULTOR')")
    public String agricultorAccess() {
        return "Agricultor Content.";
    }

    @GetMapping("/peso-cabal")
    @PreAuthorize("hasRole('PESO_CABAL')")
    public String pesoCabalAccess() {
        return "Peso Cabal Content.";
    }
}
