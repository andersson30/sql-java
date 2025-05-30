package com.example.entrega.controller;

import com.example.entrega.service.ComercianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comerciantes")
public class ComercianteController {

    @Autowired
    private ComercianteService comercianteService;

    @GetMapping("/exportar-activos")
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<Resource> exportarComerciantesActivos() {
        Resource file = comercianteService.generarCSVComerciantesActivos();
        
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("text/csv"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"comerciantes_activos.csv\"")
                .body(file);
    }
} 