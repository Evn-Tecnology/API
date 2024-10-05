package com.TecnologyGroup.Event_Tecnology.model.dto;

import com.TecnologyGroup.Event_Tecnology.model.enums.EstadoResena;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResenaResponseDTO {

    private Integer id;
    private Integer userId;
    private String userNombre;
    private Integer eventId;
    private String eventNombre;
    private String comentario;
    private int puntuacion;
    private LocalDateTime fechaPublicacion;
    private EstadoResena estado;

}
