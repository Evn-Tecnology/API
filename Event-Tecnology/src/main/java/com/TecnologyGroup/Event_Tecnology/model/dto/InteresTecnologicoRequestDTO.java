package com.TecnologyGroup.Event_Tecnology.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class InteresTecnologicoRequestDTO {
    @NotBlank(message = "El nombre del interés tecnológico no puede ser vacío")
    @Size(min = 2, message = "El nombre del interés tecnológico debe tener al menos 2 caracteres")
    private String interesTecnologicoNombre;
}