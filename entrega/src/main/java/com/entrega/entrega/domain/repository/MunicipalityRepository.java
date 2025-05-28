package com.entrega.entrega.domain.repository;

import com.entrega.entrega.domain.model.Municipality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MunicipalityRepository extends JpaRepository<Municipality, String> {
    List<Municipality> findAllByOrderByNombreAsc();
} 