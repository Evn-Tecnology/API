package com.TecnologyGroup.Event_Tecnology.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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

    @NotNull(message = "La edad es obligatoria")
    private int edad;

    @NotBlank(message = "El genero no puede ser vacío")
    @Size(min = 5, message = "El nombre debe tener al menos 5 caracteres")
    private String genero;

    @NotBlank(message = "El pais no puede ser vacío")
    @Size(min = 2, message = "El nombre debe tener al menos 2 caracteres")
    private String pais;

    private List<String> interesesTecnologicos;
    private List<String> habilidades;
}