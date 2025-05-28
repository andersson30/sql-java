package com.entrega.entrega.domain.service;

import com.entrega.entrega.domain.model.Municipality;
import com.entrega.entrega.domain.repository.MunicipalityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MunicipalityService {
    private final MunicipalityRepository municipalityRepository;

    public MunicipalityService(MunicipalityRepository municipalityRepository) {
        this.municipalityRepository = municipalityRepository;
    }

    public List<Municipality> getAllMunicipalities() {
        return municipalityRepository.findAllByOrderByNombreAsc();
    }
} 