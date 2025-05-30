package com.entrega.entrega.domain.service;

import com.entrega.entrega.domain.repository.ComercianteRepository;
import com.entrega.entrega.presentation.dto.MunicipalityDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComercianteMunicipalityService {
    private final ComercianteRepository comercianteRepository;

    public ComercianteMunicipalityService(ComercianteRepository comercianteRepository) {
        this.comercianteRepository = comercianteRepository;
    }

    public List<MunicipalityDTO> getUniqueMunicipalities() {
        return comercianteRepository.findDistinctMunicipios()
                .stream()
                .map(municipio -> new MunicipalityDTO(municipio))
                .collect(Collectors.toList());
    }
} 