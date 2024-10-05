package com.TecnologyGroup.Event_Tecnology.model.dto;

import com.TecnologyGroup.Event_Tecnology.model.enums.EstadoInscripcion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InscripcionResponseDTO {

    private Integer id;
    private Integer eventId;
    private String eventNombre;
    private Integer userId;
    private String userNombre;
    private LocalDateTime fechaInscripcion;
    private EstadoInscripcion estado;
    private boolean asistenciaConfirmada;
    private boolean esGratuito;

}
