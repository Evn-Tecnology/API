package com.TecnologyGroup.Event_Tecnology.model.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailUserRequestDTO {

    @Pattern(
            regexp = "^(https?:\\/\\/)?(www\\.)?linkedin\\.com\\/.*$",
            message = "El URL de LinkedIn debe ser válido y empezar con 'https://www.linkedin.com/'"
    )
    private String urlLinkedIn;

    @Size(min = 1, max = 100, message = "La edad debe estar entre 1 y 100")
    @Min(value = 0, message = "La edad no puede ser negativa")
    private int edad;

    @Size(min = 5, message = "El género debe tener al menos 5 caracteres")
    private String genero;

    @Size(min = 2, message = "El país debe tener al menos 2 caracteres")
    private String pais;

    private List<String> interesesTecnologicos;
    private List<String> habilidades;
}