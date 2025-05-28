package com.entrega.entrega.presentation.controller;

import com.entrega.entrega.domain.model.Municipality;
import com.entrega.entrega.domain.service.MunicipalityService;
import com.entrega.entrega.presentation.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/municipios")
public class MunicipalityController {
    private final MunicipalityService municipalityService;

    public MunicipalityController(MunicipalityService municipalityService) {
        this.municipalityService = municipalityService;
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<List<Municipality>>> getAllMunicipalities() {
        List<Municipality> municipalities = municipalityService.getAllMunicipalities();
        return ResponseEntity.ok(ApiResponse.success(municipalities));
    }
} 