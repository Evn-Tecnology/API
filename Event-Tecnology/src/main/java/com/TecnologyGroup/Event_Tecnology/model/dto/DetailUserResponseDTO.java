package com.TecnologyGroup.Event_Tecnology.model.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailUserResponseDTO {
    private String urlLinkedIn;
    private int edad;
    private String genero;
    private String pais;
    private List<String> interesesTecnologicos;
    private List<String> habilidades;
}