package com.entrega.entrega.domain.repository;

import com.entrega.entrega.domain.entity.Comerciante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComercianteRepository extends JpaRepository<Comerciante, Long> {
    @Query("SELECT DISTINCT c.municipio FROM Comerciante c ORDER BY c.municipio")
    List<String> findDistinctMunicipios();
} 