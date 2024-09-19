package com.TecnologyGroup.Event_Tecnology.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HabilidadRequestDTO {
    @NotBlank(message = "El nombre de la habilidad no puede ser vacío")
    @Size(min = 2, message = "El nombre de la habilidad debe tener al menos 2 caracteres")
    private String habilidadNombre;
}