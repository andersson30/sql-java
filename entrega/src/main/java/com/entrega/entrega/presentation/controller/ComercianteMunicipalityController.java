package com.entrega.entrega.presentation.controller;

import com.entrega.entrega.domain.service.ComercianteMunicipalityService;
import com.entrega.entrega.presentation.dto.ApiResponse;
import com.entrega.entrega.presentation.dto.MunicipalityDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comerciantes/municipios")
public class ComercianteMunicipalityController {
    private final ComercianteMunicipalityService comercianteMunicipalityService;

    public ComercianteMunicipalityController(ComercianteMunicipalityService comercianteMunicipalityService) {
        this.comercianteMunicipalityService = comercianteMunicipalityService;
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<List<MunicipalityDTO>>> getUniqueMunicipalities() {
        List<MunicipalityDTO> municipalities = comercianteMunicipalityService.getUniqueMunicipalities();
        return ResponseEntity.ok(ApiResponse.success(municipalities));
    }
} 