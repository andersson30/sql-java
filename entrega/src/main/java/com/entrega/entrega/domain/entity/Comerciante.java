package com.entrega.entrega.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "COMERCIANTE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comerciante {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_comerciante")
    @SequenceGenerator(name = "seq_comerciante", sequenceName = "seq_comerciante", allocationSize = 1)
    @Column(name = "id_comerciante")
    private Long id;

    @Column(name = "nombre_razon_social", nullable = false, length = 200)
    private String nombreRazonSocial;

    @Column(name = "municipio", nullable = false, length = 100)
    private String municipio;

    @Column(name = "telefono", length = 20)
    private String telefono;

    @Column(name = "correo_electronico", length = 100)
    private String correoElectronico;

    @Column(name = "fecha_registro", nullable = false)
    private LocalDate fechaRegistro;

    @Column(name = "estado", nullable = false, length = 10)
    private String estado;

    @Column(name = "fecha_actualizacion")
    private LocalDate fechaActualizacion;

    @Column(name = "usuario_actualizacion", length = 100)
    private String usuarioActualizacion;
} 