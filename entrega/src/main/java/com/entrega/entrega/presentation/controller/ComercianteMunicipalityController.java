package com.entrega.entrega.presentation.controller;

import com.entrega.entrega.domain.service.ComercianteMunicipalityService;
import com.entrega.entrega.presentation.dto.ApiResponse;
import com.entrega.entrega.presentation.dto.MunicipalityCreateDTO;
import com.entrega.entrega.presentation.dto.MunicipalityDTO;
import com.entrega.entrega.presentation.dto.MunicipalityUpdateDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<ApiResponse<MunicipalityDTO>> createMunicipality(
            @Valid @RequestBody MunicipalityCreateDTO createDTO) {
        MunicipalityDTO municipality = comercianteMunicipalityService.createMunicipality(createDTO);
        return ResponseEntity.ok(ApiResponse.success(municipality));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<ApiResponse<MunicipalityDTO>> updateMunicipality(
            @PathVariable Long id,
            @Valid @RequestBody MunicipalityUpdateDTO updateDTO) {
        MunicipalityDTO municipality = comercianteMunicipalityService.updateMunicipality(id, updateDTO);
        return ResponseEntity.ok(ApiResponse.success(municipality));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<ApiResponse<Void>> deleteMunicipality(@PathVariable Long id) {
        comercianteMunicipalityService.deleteMunicipality(id);
        return ResponseEntity.ok(ApiResponse.success(null));
    }
} 