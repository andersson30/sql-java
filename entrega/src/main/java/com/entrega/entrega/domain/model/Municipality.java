package com.entrega.entrega.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "municipios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Municipality {
    @Id
    private String codigo;
    private String nombre;
    private String departamento;
} 