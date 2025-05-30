package com.entrega.entrega.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MunicipalityUpdateDTO {
    @NotBlank(message = "El nombre del municipio es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre del municipio debe tener entre 2 y 100 caracteres")
    private String nombre;
} 