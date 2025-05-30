package com.entrega.entrega.domain.service;

import com.entrega.entrega.domain.entity.Comerciante;
import com.entrega.entrega.domain.repository.ComercianteRepository;
import com.entrega.entrega.presentation.dto.MunicipalityCreateDTO;
import com.entrega.entrega.presentation.dto.MunicipalityDTO;
import com.entrega.entrega.presentation.dto.MunicipalityUpdateDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
                .map(municipio -> new MunicipalityDTO(null, municipio))
                .collect(Collectors.toList());
    }

    @Transactional
    public MunicipalityDTO createMunicipality(MunicipalityCreateDTO createDTO) {
        // Verificar si ya existe un comerciante con ese municipio
        if (comercianteRepository.existsByMunicipio(createDTO.getNombre())) {
            throw new IllegalArgumentException("Ya existe un comerciante registrado en este municipio");
        }

        // Crear un nuevo comerciante con el municipio
        Comerciante comerciante = new Comerciante();
        comerciante.setMunicipio(createDTO.getNombre());
        comerciante.setNombreRazonSocial("Municipio " + createDTO.getNombre());
        comerciante.setEstado("Activo");
        comerciante.setFechaRegistro(java.time.LocalDate.now());

        Comerciante saved = comercianteRepository.save(comerciante);
        return new MunicipalityDTO(saved.getId(), saved.getMunicipio());
    }

    @Transactional
    public MunicipalityDTO updateMunicipality(Long id, MunicipalityUpdateDTO updateDTO) {
        Comerciante comerciante = comercianteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Municipio no encontrado"));

        // Verificar si el nuevo nombre ya existe en otro registro
        if (!comerciante.getMunicipio().equals(updateDTO.getNombre()) &&
            comercianteRepository.existsByMunicipio(updateDTO.getNombre())) {
            throw new IllegalArgumentException("Ya existe un comerciante registrado en este municipio");
        }

        comerciante.setMunicipio(updateDTO.getNombre());
        Comerciante updated = comercianteRepository.save(comerciante);
        return new MunicipalityDTO(updated.getId(), updated.getMunicipio());
    }

    @Transactional
    public void deleteMunicipality(Long id) {
        if (!comercianteRepository.existsById(id)) {
            throw new EntityNotFoundException("Municipio no encontrado");
        }
        comercianteRepository.deleteById(id);
    }
} 