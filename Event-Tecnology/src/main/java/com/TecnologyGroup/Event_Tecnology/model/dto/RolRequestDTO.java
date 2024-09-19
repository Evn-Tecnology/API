package com.TecnologyGroup.Event_Tecnology.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolRequestDTO {
    @NotBlank(message = "El nombre del rol no puede ser vacío")
    @Size(min = 2, message = "El nombre del rol debe tener al menos 2 caracteres")
    private String nombreRol;
}